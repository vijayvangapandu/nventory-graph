package ops.inventory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ops.inventory.dao.model.movie.Movie;
import ops.inventory.dao.model.movie.Person;
import ops.inventory.service.MovieService;
import ops.inventory.service.PersonService;

/**
 * @author Mark Angrish
 */
@RestController("/")
public class MovieController {

	final MovieService movieService;
	final PersonService personService;

	@Autowired
	public MovieController(MovieService movieService, PersonService personService) {
		this.movieService = movieService;
		this.personService = personService;
	}

	@RequestMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return movieService.graph(limit == null ? 100 : limit);
	}
	
	@RequestMapping("/save")
	public Movie saveMovie(@RequestParam(value = "mtitle",required = false) String title) {
		return saveMovieWithTitle(title);
	}
	
	private Movie saveMovieWithTitle(String title) {
		Movie forrest = new Movie(title, 2005);
		//forrest = movieService.saveMovie(forrest);
        
        //Person tomHanks = new Person("Vijay");
		Person vijay = personService.findByName("Vijay");
		if(vijay == null) {
	        vijay = new Person("Vijay");

		}
		
		vijay.playedIn(forrest, "title");
		vijay = personService.savePerson(vijay);
        
        return forrest;
        //Actor foundTomHanks = findActorByProperty("name", tomHanks.getName()).iterator().next();
        
	}
}
