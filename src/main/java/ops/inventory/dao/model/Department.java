package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Department")
public class Department {

	@GraphId
	private Long id;
	private String name;

	@Relationship(type = "TEAM_OF")
	private Set<Team> teams = new HashSet<>();

	public Department() {
	}
	
	public Department(String name) {

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
	
	
	public Set<Team> getTeams() {
		return teams;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void teamOf(Team team) {
		teams.add(team);
		team.addDepartment(this);
	}
}
