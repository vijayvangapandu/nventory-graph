package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="DiskSpace")
public class DiskSpace {

	@GraphId
	private Long id;
	private String name;
	private String diskType;
	private int numberOfDisks;
	
	@Relationship(type = "ALLOCATED_DISK", direction = Relationship.INCOMING)
	private Set<AllocatedDiskSpace> allocatedDisk = new HashSet<>();
	
	public DiskSpace() {
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
    public void addAllocatedDisk(AllocatedDiskSpace aDiskSpace) {
		
		allocatedDisk.add(aDiskSpace);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AllocatedDiskSpace> getAllocatedDisk() {
		return allocatedDisk;
	}

	public void setAllocatedDisk(Set<AllocatedDiskSpace> allocatedDisk) {
		this.allocatedDisk = allocatedDisk;
	}

	public int getNumberOfDisks() {
		return numberOfDisks;
	}

	public void setNumberOfDisks(int numberOfDisks) {
		this.numberOfDisks = numberOfDisks;
	}

	public String getDiskType() {
		return diskType;
	}

	public void setDiskType(String diskType) {
		this.diskType = diskType;
	}
    
}
