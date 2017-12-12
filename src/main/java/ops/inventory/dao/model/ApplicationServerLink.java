package ops.inventory.dao.model;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "NODE_OF")
public class ApplicationServerLink {

	@GraphId
	private Long id;


	@StartNode
	private Application application;

	@EndNode
	private Server server;

	public ApplicationServerLink() {
	}

	public ApplicationServerLink(Application application, Server server) {
		this.application = application;
		this.server = server;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id=id;
	}
}
