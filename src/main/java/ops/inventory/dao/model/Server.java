package ops.inventory.dao.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity(label = "Server")
public class Server {

	@GraphId
	private Long id;
	private String name;
	private String environment;
	private String ipAddress;
	private String gateway;
	private String dataCenter;
	private String kernalVersion;

	@Relationship(type = "NODE_OF", direction = Relationship.INCOMING)
	private List<ApplicationServerLink> appServerLinks = new ArrayList<>();

	@Relationship(type = "ALLOCATED_MEMORY", direction = Relationship.OUTGOING)
	private List<AllocatedMemory> allocatedMemory = new ArrayList<>();

	@Relationship(type = "ALLOCATED_CPU", direction = Relationship.OUTGOING)
	private List<AllocatedCpu> allocatedCPU = new ArrayList<>();

	@Relationship(type = "ALLOCATED_DISK", direction = Relationship.OUTGOING)
	private List<AllocatedDiskSpace> allocatedDiskSpace = new ArrayList<>();

	@Relationship(type = "MODEL_OF", direction = Relationship.OUTGOING)
	private List<ServerModelLink> models = new ArrayList<>();

	@Relationship(type = "INSTALLED_OS", direction = Relationship.OUTGOING)
	private Set<ServerOSLink> installedOS = new HashSet<>();

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

	public AllocatedMemory allocatedMemory(Memory memory, float memoryInGB) {
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

	public AllocatedDiskSpace allocatedDiskSpaces(DiskSpace diskSpace, float allocatedSpaceInGB) {
		final AllocatedDiskSpace aDiskSpace = new AllocatedDiskSpace(this, diskSpace);
		aDiskSpace.setAllocatedSpaceInGB(allocatedSpaceInGB);
		allocatedDiskSpace.add(aDiskSpace);
		diskSpace.addAllocatedDisk(aDiskSpace);
		return aDiskSpace;
	}

	public ServerModelLink modelOf(Model model, String modelName) {
		final ServerModelLink smLink = new ServerModelLink(this, model, modelName);
		models.add(smLink);
		model.addServerModelLink(smLink);
		return smLink;
	}

	public ServerOSLink installedOS(OperatingSystem os, String osName) {
		final ServerOSLink soLink = new ServerOSLink(this, os, osName);
		installedOS.add(soLink);
		os.addInstalledOS(soLink);
		return soLink;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public List<ServerModelLink> getModels() {
		return models;
	}

	public void setModels(List<ServerModelLink> models) {
		this.models = models;
	}

	public Set<ServerOSLink> getInstalledOS() {
		return installedOS;
	}

	public void setInstalledOS(Set<ServerOSLink> installedOS) {
		this.installedOS = installedOS;
	}

	public String getDataCenter() {
		return dataCenter;
	}

	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}

	public String getKernalVersion() {
		return kernalVersion;
	}

	public void setKernalVersion(String kernalVersion) {
		this.kernalVersion = kernalVersion;
	}
	

}
