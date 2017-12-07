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

import ops.inventory.dao.model.movie.Movie;
import ops.inventory.dao.model.movie.Person;
import ops.inventory.service.MovieService;
import ops.inventory.service.PersonService;

@Path("/v1")
@Component
public class MoviesResource {

	public static final Logger logger = LoggerFactory.getLogger(MoviesResource.class);

	
	final MovieService movieService;
	final PersonService personService;

	@Autowired
	public MoviesResource(MovieService movieService,PersonService personService) {
		this.movieService = movieService;
		this.personService = personService;
	}

	@GET
	@Path("/graph")
	@Produces(MediaType.APPLICATION_JSON)
	public GraphResult graph(@RequestParam(value = "limit",required = false) Integer limit) {
		System.out.println("Requesting for graph...");
		Map<String, Object> map =  movieService.graph(limit == null ? 100 : limit);
		GraphResult result = new GraphResult();
		result.setResult(map);
		return result;
	}
	
	@GET
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie saveMovie(@QueryParam("mtitle") String title) {
		return saveMovieWithTitle(title);
	}
	
	private Movie saveMovieWithTitle(String title) {
		Movie forrest = new Movie(title, 2006);
        
		Person vijay = personService.findByName("Vijay");
		if(vijay == null) {
	        vijay = new Person("Vijay");

		}
		
		vijay.playedIn(forrest, title);
		vijay = personService.savePerson(vijay);
        
        return forrest;
        //Actor foundTomHanks = findActorByProperty("name", tomHanks.getName()).iterator().next();
        
	}
	
}
