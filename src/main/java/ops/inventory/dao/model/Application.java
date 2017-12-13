package ops.inventory.dao.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Application")
public class Application {

	@GraphId
	private Long id;
	private String name;
	private String environment;

	@Relationship(type = "OWNS", direction = "INCOMING")
	private Set<Team> teams = new HashSet<>();
	
	@Relationship(type = "NODE_OF", direction = Relationship.OUTGOING)
	private List<ApplicationServerLink> appServerLinks = new ArrayList<>();
	
	public ApplicationServerLink nodeOf(Server server) {
        final ApplicationServerLink link = new ApplicationServerLink(this, server);
        appServerLinks.add(link);
        server.addApplicationServerLink(link);
        return link;
    }

	public Application() {
		
	}
	public Application(String name, String environment) {

		this.name = name;
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

	public String getEnvironment() {
		return environment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ApplicationServerLink> getAppServerLinks() {
		return appServerLinks;
	}

	public void setAppServerLinks(List<ApplicationServerLink> appServerLinks) {
		this.appServerLinks = appServerLinks;
	}
	
	//@Relationship(type = "OWNS", direction = "INCOMING")
	public Set<Team> getTeams() {
		return teams;
	}
	
}
