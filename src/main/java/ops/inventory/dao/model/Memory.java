package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Memory")
public class Memory {

	@GraphId
	private Long id;
	private String name;
	
	@Relationship(type = "ALLOCATED_MEMORY", direction = Relationship.INCOMING)
	private Set<AllocatedMemory> allocatedMemory = new HashSet<>();
	
	
	public Memory() {
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
    public void addAllocatedMemory(AllocatedMemory aMemory) {
		
		allocatedMemory.add(aMemory);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AllocatedMemory> getAllocatedMemory() {
		return allocatedMemory;
	}

	public void setAllocatedMemory(Set<AllocatedMemory> allocatedMemory) {
		this.allocatedMemory = allocatedMemory;
	}
    
}
