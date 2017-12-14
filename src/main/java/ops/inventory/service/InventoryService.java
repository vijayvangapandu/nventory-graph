package ops.inventory.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import ops.inventory.dao.model.Memory;
import ops.inventory.dao.model.Model;
import ops.inventory.dao.model.OperatingSystem;
import ops.inventory.dao.model.Processor;
import ops.inventory.dao.model.Server;
import ops.inventory.dao.model.Team;
import ops.inventory.rest.ServerSaveRequest;

@Service
public class InventoryService {

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
		
		Memory memory = getMemory("Memory");
		if(memory == null) {
			memory = new Memory();
		}
		memory.setName("Memory");
		server.allocatedMemory(memory, serverSaveRequest.getMemoryInGB());
		
		Processor processor = getProcessor("Processor");
		if(processor == null) {
			processor = new Processor();
		}
		processor.setName("Processor");
		server.allocatedCpu(processor, serverSaveRequest.getCpuCores());
		
		DiskSpace diskSpace = getDiskSpace("DiskSpace");
		if(diskSpace == null) {
			diskSpace = new DiskSpace();
		}
		diskSpace.setName("DiskSpace");
		server.allocatedDiskSpaces(diskSpace, serverSaveRequest.getDiskSpaceInGB());
		
		OperatingSystem os = osRepository.findByName("OperatingSystem");
		if(os == null) {
			os = new OperatingSystem();
		}
		os.setName("OperatingSystem");
		server.installedOS(os, serverSaveRequest.getOperatingSystem());
		
		Model model = modelRepository.findByName("Model");
		if(model == null) {
			model = new Model();
			model.setName("Model");
		}
		server.modelOf(model, serverSaveRequest.getModel());
		
		Application app = getApplication(serverSaveRequest.getApplicationName());
		if(app == null) {
			app = new Application(serverSaveRequest.getApplicationName(), getEnvironment(serverSaveRequest));
		}
		
		Team team = getTeam(serverSaveRequest.getTeamName());
		if(team == null) {
			team = new Team(serverSaveRequest.getTeamName());
		}
		team.owns(app);
		app.nodeOf(server);
		app = applicationService.saveApplication(app);
		
        return server;
        
	}
	
	private String getEnvironment(ServerSaveRequest request) {
		if(StringUtils.isNotBlank(request.getEnvironment())) {
			return request.getEnvironment();
		}
		return "PROD";
	}
	
	public void loadDataFromFile() throws Exception {
		final String fileName = "/Users/vvangapandu/Desktop/inventory-load-2017-Part1-Copy.xlsx";
		List<ServerSaveRequest> requests = reader.loadDataFromFile(fileName);
		
		for(ServerSaveRequest request:  requests) {
			saveServer(request);
		}
	}

}
