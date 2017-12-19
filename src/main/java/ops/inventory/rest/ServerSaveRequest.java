package ops.inventory.rest;

public class ServerSaveRequest {

	private String serverName;
	private float cpuCores;
	private float memoryInGB;
	private float diskSpaceInGB;
	private String applicationName;
	private String teamName;
	private String operatingSystem;
	private String model;
	private String serialNumber;
	private String environment;
	private String gateway;
	private String ipAddress;
	private String dataCenter;
	private String kernalRelease;
	private float cost;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDataCenter() {
		return dataCenter;
	}
	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}
	
	public String getKernalRelease() {
		return kernalRelease;
	}
	public void setKernalRelease(String kernalRelease) {
		this.kernalRelease = kernalRelease;
	}
	public float getCpuCores() {
		return cpuCores;
	}
	public void setCpuCores(float cpuCores) {
		this.cpuCores = cpuCores;
	}
	public float getMemoryInGB() {
		return memoryInGB;
	}
	public void setMemoryInGB(float memoryInGB) {
		this.memoryInGB = memoryInGB;
	}
	public float getDiskSpaceInGB() {
		return diskSpaceInGB;
	}
	public void setDiskSpaceInGB(float diskSpaceInGB) {
		this.diskSpaceInGB = diskSpaceInGB;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
