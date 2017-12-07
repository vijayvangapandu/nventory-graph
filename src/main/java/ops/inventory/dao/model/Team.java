package ops.inventory.dao.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ops.inventory.dao.model.movie.Movie;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Team {

	@GraphId
	private Long id;
	private String name;

	//TODO
	@Relationship(type = "ACTED_IN")
	private List<Movie> movies = new ArrayList<>();

	public Team() {
	}
	
	public void addMovie(Movie movie) {
		movies.add(movie);
	}

	public Team(String name) {

		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Movie> getMovies() {
		return movies;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
