package ops.inventory.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import ops.inventory.dao.model.Application;
import ops.inventory.dao.model.DiskSpace;
import ops.inventory.dao.model.Memory;
import ops.inventory.dao.model.Processor;
import ops.inventory.dao.model.Server;
import ops.inventory.dao.model.Team;
import ops.inventory.service.ApplicationService;
import ops.inventory.service.DiskSpaceService;
import ops.inventory.service.InventoryService;
import ops.inventory.service.MemoryService;
import ops.inventory.service.ProcessorService;
import ops.inventory.service.ServerService;
import ops.inventory.service.TeamService;

@Path("/v1")
@Component
public class InventoryResource {

	public static final Logger logger = LoggerFactory.getLogger(InventoryResource.class);

	
	final ServerService serverService;
	final ApplicationService applicationService;
	final TeamService teamService;
	final MemoryService memoryService;
	final ProcessorService processorService;
	final DiskSpaceService diskSpaceService;
	
	final InventoryService inventoryService;

	@Autowired
	public InventoryResource(ServerService serverService, ApplicationService applicationService, TeamService teamService, MemoryService memoryService,
			ProcessorService processorService, DiskSpaceService diskSpaceService, InventoryService inventoryService) {
		this.serverService = serverService;
		this.applicationService = applicationService;
		this.teamService = teamService;
		this.memoryService = memoryService;
		this.processorService = processorService;
		this.diskSpaceService = diskSpaceService;
		this.inventoryService = inventoryService;
	}

	@GET
	@Path("/server/graph")
	@Produces(MediaType.APPLICATION_JSON)
	public GraphResult graph(@RequestParam(value = "limit",required = false) Integer limit) {
		System.out.println("Requesting for graph...");
		Map<String, Object> map =  serverService.graph(limit == null ? 100 : limit);
		GraphResult result = new GraphResult();
		if(map != null) {
			System.out.println("Empty map...");
			result.setResult(map);
		}
		
		return result;
	}
	
	@GET
	@Path("/server/save")
	@Produces(MediaType.APPLICATION_JSON)
	public void saveServer(@QueryParam("sname") String sname, @QueryParam("aname") String aname) throws Exception {
		//return saveServerWithName(buildServerSaveRequest(sname, aname));
		final String fileName = "/Users/vvangapandu/Desktop/inventory-load-2017-Part1-Copy.xlsx";
		inventoryService.loadDataFromFile();
				//;saveServerWithName(sname, aname);
	}
	
	private Server saveServerWithName(ServerSaveRequest serverSaveRequest) {
		Server server = serverService.findByName(serverSaveRequest.getServerName());
		
		if(server == null) {
			server = new Server(serverSaveRequest.getServerName());
		}
		
		Memory memory = memoryService.findByName("Memory");
		if(memory == null) {
			memory = new Memory();
			memory.setName("Memory");
		}
		server.allocatedMemory(memory, serverSaveRequest.getMemoryInGB());
		
		Processor processor = processorService.findByName("Processor");
		if(processor == null) {
			processor = new Processor();
			processor.setName("Processor");
		}
		server.allocatedCpu(processor, serverSaveRequest.getCpuCores());
		
		DiskSpace diskSpace = diskSpaceService.findByName("DiskSpace");
		if(diskSpace == null) {
			diskSpace = new DiskSpace();
			diskSpace.setName("DiskSpace");
		}
		server.allocatedDiskSpaces(diskSpace, serverSaveRequest.getDiskSpaceInGB());
		
		Application app = applicationService.findByName(serverSaveRequest.getApplicationName());
		if(app == null) {
			app = new Application(serverSaveRequest.getApplicationName(), getEnvironment(serverSaveRequest));
		}
		
		Team team = teamService.findByName(serverSaveRequest.getTeamName());
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
	
	private ServerSaveRequest buildServerSaveRequest(String serverName, String applicationName) {
		ServerSaveRequest request = new ServerSaveRequest();
		
		request.setApplicationName(applicationName);
		request.setServerName(serverName);
		request.setCpuCores("2");
		request.setDiskSpaceInGB("32");
		request.setMemoryInGB("8");
		request.setTeamName("Singles");
		
		return request;
	}
	
	
	
}
