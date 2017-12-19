package ops.inventory.dao.model;

import java.util.ArrayList;
import java.util.List;

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
	private String model;
	private float cost;

	@Relationship(type = "NODE_OF", direction = Relationship.INCOMING)
	private List<Application> applications = new ArrayList<>();

	@Relationship(type = "ALLOCATED_HARDWARE")
	private Hardware hardware;

	@Relationship(type = "INSTALLED_OS")
	private OperatingSystem operatingSystem;

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

	public void addApplication(Application application) {
		applications.add(application);
	}
	
	public void allocatedHardware(Hardware hardware) {
		this.hardware = hardware;
		hardware.addServer(this);
	}
	
	public void installedOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
		operatingSystem.addServer(this);
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
