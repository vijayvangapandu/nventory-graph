package ops.inventory.dao.model;


import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Server")
public class Server {

	@GraphId
	private Long id;
	private String name;
	private String environment;
	private String os;

	@Relationship(type = "NODE_OF", direction = Relationship.INCOMING)
	private List<ApplicationServerLink> appServerLinks = new ArrayList<>();
	
	@Relationship(type = "ALLOCATED_MEMORY", direction = Relationship.OUTGOING)
	private List<AllocatedMemory> allocatedMemory = new ArrayList<>();
	
	@Relationship(type = "ALLOCATED_CPU", direction = Relationship.OUTGOING)
	private List<AllocatedCpu> allocatedCPU = new ArrayList<>();
	
	@Relationship(type = "ALLOCATED_DISK", direction = Relationship.OUTGOING)
	private List<AllocatedDiskSpace> allocatedDiskSpace = new ArrayList<>();

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
	
	public void addApplicationServerLink(ApplicationServerLink link) {
		appServerLinks.add(link);
	}
	
	public AllocatedMemory allocatedMemory(Memory memory, long memoryInGB) {
        final AllocatedMemory aMemory = new AllocatedMemory(this, memory);
        aMemory.setMemoryInGB(memoryInGB);
        allocatedMemory.add(aMemory);
        memory.addAllocatedMemory(aMemory);
        return aMemory;
    }
	
	public AllocatedCpu allocatedCpu(Processor processor, int numberOfCores) {
        final AllocatedCpu aCpu = new AllocatedCpu(this, processor);
        aCpu.setNumberOfCores(numberOfCores);
        allocatedCPU.add(aCpu);
        processor.addAllocatedProcessor(aCpu);
        return aCpu;
    }
	
	public AllocatedDiskSpace allocatedDiskSpaces(DiskSpace diskSpace, long allocatedSpaceInGB) {
        final AllocatedDiskSpace aDiskSpace = new AllocatedDiskSpace(this, diskSpace);
        aDiskSpace.setAllocatedSpaceInGB(allocatedSpaceInGB);
        allocatedDiskSpace.add(aDiskSpace);
        diskSpace.addAllocatedDisk(aDiskSpace);
        return aDiskSpace;
    }


	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public List<ApplicationServerLink> getAppServerLinks() {
		return appServerLinks;
	}

	public void setAppServerLinks(List<ApplicationServerLink> appServerLinks) {
		this.appServerLinks = appServerLinks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AllocatedMemory> getAllocatedMemory() {
		return allocatedMemory;
	}

	public void setAllocatedMemory(List<AllocatedMemory> allocatedMemory) {
		this.allocatedMemory = allocatedMemory;
	}

	public List<AllocatedCpu> getAllocatedCPU() {
		return allocatedCPU;
	}

	public void setAllocatedCPU(List<AllocatedCpu> allocatedCPU) {
		this.allocatedCPU = allocatedCPU;
	}

	public List<AllocatedDiskSpace> getAllocatedDiskSpace() {
		return allocatedDiskSpace;
	}

	public void setAllocatedDiskSpace(List<AllocatedDiskSpace> allocatedDiskSpace) {
		this.allocatedDiskSpace = allocatedDiskSpace;
	}
	
}
