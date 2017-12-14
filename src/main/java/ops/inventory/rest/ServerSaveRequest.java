package ops.inventory.rest;

public class ServerSaveRequest {

	private String serverName;
	private String cpuCores;
	private String memoryInGB;
	private String diskSpaceInGB;
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
	public String getCpuCores() {
		return cpuCores;
	}
	public void setCpuCores(String cpuCores) {
		this.cpuCores = cpuCores;
	}
	public String getMemoryInGB() {
		return memoryInGB;
	}
	public void setMemoryInGB(String memoryInGB) {
		this.memoryInGB = memoryInGB;
	}
	public String getDiskSpaceInGB() {
		return diskSpaceInGB;
	}
	public void setDiskSpaceInGB(String diskSpaceInGB) {
		this.diskSpaceInGB = diskSpaceInGB;
	}
	public String getKernalRelease() {
		return kernalRelease;
	}
	public void setKernalRelease(String kernalRelease) {
		this.kernalRelease = kernalRelease;
	}
	
	
	
}
