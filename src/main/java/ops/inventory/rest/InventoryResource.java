package ops.inventory.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ops.inventory.dao.model.Application;
import ops.inventory.dao.model.Server;
import ops.inventory.dao.model.Team;
import ops.inventory.service.ApplicationService;
import ops.inventory.service.DiskSpaceService;
import ops.inventory.service.HardwareResourceResponse;
import ops.inventory.service.InventoryService;
import ops.inventory.service.MemoryService;
import ops.inventory.service.ProcessorService;
import ops.inventory.service.ServerService;
import ops.inventory.service.TeamApplicationsResourcesResponse;
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
	@Path("/teams")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Team> getAllTeams() {
		logger.info("Fetching all teams");
		return inventoryService.getAllTeams();
	}

	@GET
	@Path("/teams/{teamName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Team getTeamByName(@PathParam("teamName")  String teamName) {
		logger.info("Fetching team info with name {}" , teamName);
		return inventoryService.getTeam(teamName);
	}
	
	@GET
	@Path("/teams/{teamName}/applications")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Application> getApplicationsByTeamName(@PathParam("teamName")  String teamName) {
		logger.info("Fetching applications by teamName {}" , teamName);
		return inventoryService.getAllApplicationsByTeam(teamName);
	}
	
	@GET
	@Path("/teams/applications/{appName}/servers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Server> getServersByAppName(@PathParam("appName")  String appName, @QueryParam("prodOnly") boolean prodOnly) {
		logger.info("Fetching Servers by appName {}" , appName);
		return inventoryService.getAllServersByApplication(appName, prodOnly);
	}
	
	@GET
	@Path("/applications/{appName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Application getApplicationByName(@PathParam("appName")  String appName) {
		logger.info("Fetching application info with name {}" , appName);
		return inventoryService.getApplication(appName);
	}
	
	@POST
	@Path("/servers/file/{fileName}")
	@Produces(MediaType.APPLICATION_JSON)
	public void loadInventoryFromFile(@PathParam("fileName") String fileName) throws Exception {
		inventoryService.loadDataFromFile(fileName);
	}
	
	@GET
	@Path("/teams/{teamName}/applications/resources")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamApplicationsResourcesResponse getServersResourcesByTeam(@PathParam("teamName")  String teamName, @QueryParam("prodOnly") boolean prodOnly) {
		logger.info("Fetching Servers resources by teamName {}" , teamName);
		return inventoryService.getTeamAppResources(teamName);
	}
	
	@POST
	@Path("/applications/{appName}/costByServer/{cost}")
	@Produces(MediaType.APPLICATION_JSON)
	public int updateServerCost(@PathParam("appName")  String appName, @PathParam("cost")  float cost) {
		logger.info("Updating cost for all servers in given application with name {} and server cost [}" , appName, cost);
		int updatedCount =  inventoryService.updateApplicationWitCost(appName, cost);
		logger.info("Total updated records count {}", updatedCount);
		return updatedCount;
	}
	
	@GET
	@Path("/hardware")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<HardwareResourceResponse> getHardwareProfiles(@QueryParam("appName")  String appName, @QueryParam("teamName") String teamName) {
		logger.info("Fetching Hardware profiels for team {} and application {}" , teamName, appName);
		return inventoryService.getAllUniqueHardwareProfiles(teamName, appName);
	}
	
}
