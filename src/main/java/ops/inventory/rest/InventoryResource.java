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
import ops.inventory.dao.model.Server;
import ops.inventory.service.ApplicationService;
import ops.inventory.service.ServerService;

@Path("/v1")
@Component
public class InventoryResource {

	public static final Logger logger = LoggerFactory.getLogger(InventoryResource.class);

	
	final ServerService serverService;
	final ApplicationService applicationService;

	@Autowired
	public InventoryResource(ServerService serverService, ApplicationService applicationService) {
		this.serverService = serverService;
		this.applicationService = applicationService;
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
		server.setRam("4GB");
		server.setDisk("32GB");
        
		Application app = applicationService.findByName("PAPI");
		if(app == null) {
			app = new Application("PAPI", "PROD");
		}
		
		app.nodeOf(server);
		app = applicationService.saveApplication(app);
        
        return server;
        //Actor foundTomHanks = findActorByProperty("name", tomHanks.getName()).iterator().next();
        
	}
	
}
