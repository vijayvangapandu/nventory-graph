package ops.inventory.dao.model;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Server {

	@GraphId
	private Long id;
	private String name;
	private String environment;
	private String ram;
	private String disk;
	private String cpu;
	private String os;

	//TODO
	@Relationship(type = "PART_OF_APPLICATION")
	private Application application;
	
	//@Relationship(type = "PART_OF_APPLICATION")
	//private Application application;

	public Server() {
	}
	
	public Server(String name) {

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
}
