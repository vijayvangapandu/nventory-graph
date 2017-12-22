package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Hardware")
public class Hardware {

	@GraphId
	private Long id;
	private String name;
	private float memoryInGB;
	private int cpuCores;
	private float diskSpaceInGB;
	private String diskType;
	private int numberOfDisks;
	
	@Relationship(type = "ALLOCATED_HARDWARE", direction = "INCOMING")
	private Set<Server> servers = new HashSet<>();
	
	public Hardware() {
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
    public void addServer(Server server) {
		
    	servers.add(server);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<Server> getServers() {
		return servers;
	}

	public void setServers(Set<Server> servers) {
		this.servers = servers;
	}

	public void setDiskType(String diskType) {
		this.diskType = diskType;
	}

	public float getMemoryInGB() {
		return memoryInGB;
	}

	public void setMemoryInGB(float memoryInGB) {
		this.memoryInGB = memoryInGB;
	}

	public int getCpuCores() {
		return cpuCores;
	}

	public void setCpuCores(int cpuCores) {
		this.cpuCores = cpuCores;
	}

	public float getDiskSpaceInGB() {
		return diskSpaceInGB;
	}

	public void setDiskSpaceInGB(float diskSpaceInGB) {
		this.diskSpaceInGB = diskSpaceInGB;
	}

}
