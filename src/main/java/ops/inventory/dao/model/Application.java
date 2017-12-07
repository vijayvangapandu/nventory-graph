package ops.inventory.dao.model;


import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Application {

	@GraphId
	private Long id;
	private final String name;
	private final String environment;
	private final String teamName;

	@Relationship(type = "HAS_SERVERS", direction = Relationship.UNDIRECTED)
	private List<Server> servers = new ArrayList<>();

	public void addServer(Server server) {
		servers.add(server);
	}

	public Application(String name, String team, String environment) {

		this.name = name;
		this.teamName = team;
		this.environment = environment;
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

	public List<Server> getServers() {
		return servers;
	}

	public String getEnvironment() {
		return environment;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
