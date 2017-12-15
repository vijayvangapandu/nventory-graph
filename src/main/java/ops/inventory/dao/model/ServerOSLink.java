package ops.inventory.dao.model;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "INSTALLED_OS")
public class ServerOSLink {

	@GraphId
	private Long id;

	@StartNode
	private Server server;

	@EndNode
	private OperatingSystem os;
	
	private String osName;

	public ServerOSLink() {
	}

	public ServerOSLink(Server server, OperatingSystem os, String osName) {
		this.os = os;
		this.server = server;
		this.osName = osName;
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

	public OperatingSystem getOs() {
		return os;
	}

	public void setOs(OperatingSystem os) {
		this.os = os;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	
	
}
