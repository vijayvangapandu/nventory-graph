package ops.inventory.dao.model;


import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Model {

	@GraphId
	private Long id;
	private  String name;
	private  String model;
	private  String sn;

	@Relationship(type = "SERVER_WITH", direction = Relationship.UNDIRECTED)
	private List<Server> servers;
	
	public Model() {
		
	}
	public Model(String name, String model, String sn) {
		this.name = name;
		this.model = model;
		this.sn=sn;
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

	public void addServer(Server server) {
		this.servers.add(server);
	}

	public String getModel() {
		return model;
	}

	public String getSn() {
		return sn;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
