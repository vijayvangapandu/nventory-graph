package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Processor")
public class Processor {

	@GraphId
	private Long id;
	private String name;
	
	@Relationship(type = "ALLOCATED_CPU", direction = Relationship.INCOMING)
	private Set<AllocatedCpu> allocatedCPU = new HashSet<>();
	
	
	public Processor() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public void addAllocatedProcessor(AllocatedCpu aProcessor) {
		
    	allocatedCPU.add(aProcessor);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
