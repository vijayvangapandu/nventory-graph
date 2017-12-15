package ops.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ops.inventory.dao.ApplicationRepository;
import ops.inventory.dao.DiskSpaceRepository;
import ops.inventory.dao.MemoryRepository;
import ops.inventory.dao.ModelRepository;
import ops.inventory.dao.OperatingSystemRepository;
import ops.inventory.dao.ProcessorRepository;
import ops.inventory.dao.ServerRepository;
import ops.inventory.dao.TeamRepository;
import ops.inventory.dao.model.Application;
import ops.inventory.dao.model.DiskSpace;
import ops.inventory.dao.model.Hardware;
import ops.inventory.dao.model.Memory;
import ops.inventory.dao.model.Model;
import ops.inventory.dao.model.OperatingSystem;
import ops.inventory.dao.model.Processor;
import ops.inventory.dao.model.Server;
import ops.inventory.dao.model.Team;
import ops.inventory.rest.ServerSaveRequest;

@Service
public class InventoryService {

	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);
	
	@Autowired
	ApplicationRepository appRepository;
	@Autowired
	DiskSpaceRepository diskSpaceRepository;
	@Autowired
	MemoryRepository memoryRepository;
	@Autowired
	ProcessorRepository processorRepository;
	@Autowired
	ServerRepository serverRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	OperatingSystemRepository osRepository;
	@Autowired
	ModelRepository modelRepository;

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private InventoryRecordsFileReader reader;
	
	
	public List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<>();
		Iterable<Team> ite = teamRepository.findAll();
		if(ite != null) {
			ite.iterator().forEachRemaining(a-> {
				teams.add(a);
			});
		}
		return teams;
	}
	
	public List<Application> getAllApplicationsByTeam(String teamName) {
		Team team = teamRepository.findByName(teamName);
		if(team != null) {
			Set<Application> applicationsSet = team.getApplications();
			return new ArrayList(applicationsSet);
		}
		return ListUtils.EMPTY_LIST;
	}
	
	public List<Server> getAllServersByApplication(String applicationName, boolean onlyProd) {
		
		List<Server> servers =  serverRepository.getServersForApplication(applicationName);
		
		if(servers != null) {
			servers.stream().forEach(a -> {
				//List<AllocatedCpu> cpus = a.getAllocatedCPU();
				Hardware hardware = a.getHardware();
				if(hardware != null) {
					logger.info("Allocated CPU {}", hardware.getCpuCores());
				}
			});
		}
		return servers;
	}
	
	public TeamApplicationsResourcesResponse getTeamAppResources(String teamName) {
		TeamApplicationsResourcesResponse response = new TeamApplicationsResourcesResponse();
		List<Application> applications = getAllApplicationsByTeam(teamName);
		List<ApplicationResourcesResponse> applicationResources = new ArrayList<ApplicationResourcesResponse>();
		
		for(Application app: applications) {
			List<ServerResourcesResponse> serverResources = buildServerResourcesResponse(app);
			ApplicationResourcesResponse appResourceResponse = new ApplicationResourcesResponse();
			appResourceResponse.setApplicationName(app.getName());
			appResourceResponse.setServerResources(serverResources);
			applicationResources.add(appResourceResponse);
		}
		response.setApplicationResources(applicationResources);
		response.setTeamName(teamName);
		
		return calculateAggregates(response);
	}
	private  List<ServerResourcesResponse> buildServerResourcesResponse(Application app ) {
		List<Server> servers = getAllServersByApplication(app.getName(), false);
		return servers.stream().map( a -> mapToServerResponse(a)).collect(Collectors.toList());
	}
	
	private TeamApplicationsResourcesResponse calculateAggregates(TeamApplicationsResourcesResponse response) {
		List<ApplicationResourcesResponse> appResourceRes = response.getApplicationResources();
		appResourceRes = appResourceRes.stream().map(a -> aggregateHardware(a)).map(a -> calculateAggregates(a, response)).collect(Collectors.toList());
		response.setApplicationResources(appResourceRes);
		return response;
	}
	
	private ApplicationResourcesResponse aggregateHardware(ApplicationResourcesResponse arr) {
		
		List<ServerResourcesResponse> serverRes = arr.getServerResources();
		
		serverRes.stream().forEach(a -> {
			arr.addCPUCores(a.getTotalCores());
			arr.addDiskSpace(a.getDiskSpaceInGB());
			arr.addMemory(a.getMemoryInGB());
		} );
		
		return arr;
		
	}
	
   private ApplicationResourcesResponse calculateAggregates(ApplicationResourcesResponse arr, TeamApplicationsResourcesResponse response) {
		response.addCPUCores(arr.getTotalCores());
		response.addDiskSpace(arr.getTotalDiskSpaceInGB());
		response.addMemory(arr.getTotalMemoryInGB());
		return arr;
	}
	
	
	
	
	private ServerResourcesResponse mapToServerResponse(Server s) {
		ServerResourcesResponse response = new ServerResourcesResponse();
		response.setDatacenter(s.getDataCenter());
		Hardware hardware = s.getHardware();
		response.setDiskSpaceInGB(hardware.getDiskSpaceInGB());
		response.setEnvironment(s.getEnvironment());
		response.setIpaddress(s.getIpAddress());
		response.setMemoryInGB(hardware.getMemoryInGB());
		OperatingSystem os = s.getOperatingSystem();
		if(os != null) {
			response.setOperatingSystem(os.getName());
		}
		//response.setOperatingSystem(s.getOperatingSystem().getName());
		response.setServerName(s.getName());
		response.setTotalCores(hardware.getCpuCores());
		return response;
	}
	public Server getServer(String name) {
		return serverRepository.findByName(name);
	}

	public Application getApplication(String name) {
		return appRepository.findByName(name);
	}

	public Team getTeam(String name) {
		return teamRepository.findByName(name);
	}

	public Memory getMemory(String name) {
		return memoryRepository.findByName(name);
	}

	public DiskSpace getDiskSpace(String name) {
		return diskSpaceRepository.findByName(name);
	}

	public Processor getProcessor(String name) {
		return processorRepository.findByName(name);
	}

	public OperatingSystem getOperatingSystem(String name) {
		return osRepository.findByName(name);
	}

	public Model getModel(String name) {
		return modelRepository.findByName(name);
	}
	
	public Server saveServer(ServerSaveRequest serverSaveRequest) {
		Server server = getServer(serverSaveRequest.getServerName());
		
		if(server == null) {
			server = new Server(serverSaveRequest.getServerName());
		}
		server.setEnvironment(getEnvironment(serverSaveRequest));
		server.setGateway(serverSaveRequest.getGateway());
		server.setIpAddress(serverSaveRequest.getIpAddress());
		if(StringUtils.isNotBlank(serverSaveRequest.getDataCenter())) {
			server.setDataCenter(serverSaveRequest.getDataCenter());
		}
		
		Hardware hardware = server.getHardware();
		if(hardware == null) {
			hardware = new Hardware();
		}
		
		hardware.setCpuCores(Float.valueOf(serverSaveRequest.getCpuCores()).intValue());
		hardware.setDiskSpaceInGB(serverSaveRequest.getDiskSpaceInGB());
		//hardware.setDiskType(diskType);
		hardware.setMemoryInGB(serverSaveRequest.getMemoryInGB());
		hardware.setName("HARDWARE"+"-"+server.getName());
		//hardware.setNumberOfDisks(numberOfDisks);
		server.allocatedHardware(hardware);
		server.setModel(serverSaveRequest.getModel());
		
		OperatingSystem os = server.getOperatingSystem();
		
		if(os == null) {
			os = new OperatingSystem();
		}
		
		os.setName(serverSaveRequest.getOperatingSystem());
		server.installedOperatingSystem(os);
		
		Application app = getApplication(serverSaveRequest.getApplicationName());
		if(app == null) {
			app = new Application(serverSaveRequest.getApplicationName(), getEnvironment(serverSaveRequest));
		}
		
		Team team = getTeam(serverSaveRequest.getTeamName());
		if(team == null) {
			team = new Team(serverSaveRequest.getTeamName());
		}
		team.owns(app);
		app.addServer(server);
		app = applicationService.saveApplication(app);
		
        return server;
        
	}
	
	private String getEnvironment(ServerSaveRequest request) {
		if(StringUtils.isNotBlank(request.getEnvironment())) {
			return request.getEnvironment();
		}
		return "PROD";
	}
	
	public void loadDataFromFile(String fileName) throws Exception {
		//final String fileName = "/Users/vvangapandu/Desktop/inventory-load-2017-Part1-Copy.xlsx";
		List<ServerSaveRequest> requests = reader.loadDataFromFile(fileName);
		
		for(ServerSaveRequest request:  requests) {
			saveServer(request);
		}
	}

}
