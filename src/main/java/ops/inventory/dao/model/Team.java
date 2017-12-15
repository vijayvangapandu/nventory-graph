package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Team")
public class Team {

	@GraphId
	private Long id;
	private String name;

	@Relationship(type = "OWNES")
	private Set<Application> applications = new HashSet<>();

	public Team() {
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

	public void setId(long id) {
		this.id = id;
	}
	
	
	public Set<Application> getApplications() {
		return applications;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void owns(Application application) {
		applications.add(application);
		application.getTeams().add(this);
	}
}
