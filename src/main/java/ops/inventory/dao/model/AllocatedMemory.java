package ops.inventory.dao.model;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "ALLOCATED_MEMORY")
public class AllocatedMemory {

	@GraphId
	private Long id;

	private float memoryInGB;

	@StartNode
	private Server server;

	@EndNode
	private Memory memory;

	public AllocatedMemory() {
	}

	public AllocatedMemory(Server server, Memory memory) {
		this.memory = memory;
		this.server = server;
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

	public float getMemoryInGB() {
		return memoryInGB;
	}

	public void setMemoryInGB(float memoryInGB) {
		this.memoryInGB = memoryInGB;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	
}
