package ops.inventory.dao.model;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "ALLOCATED_DISK")
public class AllocatedDiskSpace {

	@GraphId
	private Long id;

	private String allocatedSpaceInGB;

	@StartNode
	private Server server;

	@EndNode
	private DiskSpace diskSpace;

	public AllocatedDiskSpace() {
	}

	public AllocatedDiskSpace(Server server, DiskSpace diskSpace) {
		this.diskSpace = diskSpace;
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
	

	public String getAllocatedSpaceInGB() {
		return allocatedSpaceInGB;
	}

	public void setAllocatedSpaceInGB(String allocatedSpaceInGB) {
		this.allocatedSpaceInGB = allocatedSpaceInGB;
	}

	public DiskSpace getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(DiskSpace diskSpace) {
		this.diskSpace = diskSpace;
	}
	
	
}
