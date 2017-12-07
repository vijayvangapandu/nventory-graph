package ops.inventory.dao.model;


import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Hardware {

	@GraphId
	private Long id;
	private String ram;
	private String disk;
	private String cpu;

	
	
	//@Relationship(type = "PART_OF_APPLICATION")
	//private Application application;

	public Hardware() {
	}
	
	public Long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
}
