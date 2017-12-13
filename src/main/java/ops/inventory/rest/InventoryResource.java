package ops.inventory.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import ops.inventory.dao.model.Application;
import ops.inventory.dao.model.Memory;
import ops.inventory.dao.model.Server;
import ops.inventory.dao.model.Team;
import ops.inventory.service.ApplicationService;
import ops.inventory.service.MemoryService;
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

	@Autowired
	public InventoryResource(ServerService serverService, ApplicationService applicationService, TeamService teamService, MemoryService memoryService) {
		this.serverService = serverService;
		this.applicationService = applicationService;
		this.teamService = teamService;
		this.memoryService = memoryService;
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
	public Server saveServer(@QueryParam("sname") String sname) {
		return saveServerWithName(sname);
	}
	
	private Server saveServerWithName(String serverName) {
		Server server = new Server(serverName);
		server.setCpu("2");
		server.setDisk("32GB");
		
		Memory memory = memoryService.findByName("Memory");
		if(memory == null) {
			memory = new Memory();
			memory.setName("Memory");
		}
		server.allocatedMemory(memory, 4);
		
		Application app = applicationService.findByName("PAPI");
		if(app == null) {
			app = new Application("PAPI", "PROD");
		}
		
		Team team = teamService.findByName("Singles");
		if(team == null) {
			team = new Team("Singles");
		}
		team.owns(app);
		app.nodeOf(server);
		app = applicationService.saveApplication(app);
		
		//teamService.saveApplication(team);
        return server;
        //Actor foundTomHanks = findActorByProperty("name", tomHanks.getName()).iterator().next();
        
	}
	
}
