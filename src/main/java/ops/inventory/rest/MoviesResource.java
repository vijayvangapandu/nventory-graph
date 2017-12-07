package ops.inventory.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import ops.inventory.service.MovieService;

@Path("/v1")
@Component
public class MoviesResource {

	public static final Logger logger = LoggerFactory.getLogger(MoviesResource.class);

	/*@POST
	@Path("user/{userId}/photos/{photoId}/presentation-order/{porder}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public void saveMovie(@PathParam("userId") final long userId,
			@PathParam("photoId") final long photoId,
			@PathParam("presentationOrder") int presentationOrder, 
			final @Context HttpHeaders httpHeaders) {

		
	}*/
	
	final MovieService movieService;

	@Autowired
	public MoviesResource(MovieService movieService) {
		this.movieService = movieService;
	}

	@GET
	@Path("/graph")
	@Produces(MediaType.APPLICATION_JSON)
	public GraphResult graph(@RequestParam(value = "limit",required = false) Integer limit) {
		System.out.println("Requesting for graph...");
		Map<String, Object> map =  movieService.graph(limit == null ? 100 : limit);
		System.out.println("Returning results map with size1 ...");
		GraphResult result = new GraphResult();
		result.setResult(map);
		//System.out.println("Returning results map with size2 ..." + map != null? map.size():-1);
		System.out.println("Returning results map with size3");
		return result;
	}
	
}
