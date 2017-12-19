package ops.inventory.service;

public class ServerResourcesResponse {

	private String serverName;
	private double memoryInGB;
	private double diskSpaceInGB;
	private int totalCores;
	private String operatingSystem;
	private String ipaddress;
	private String environment;
	private String datacenter;
	private float cost;
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public double getMemoryInGB() {
		return memoryInGB;
	}
	public void setMemoryInGB(double memoryInGB) {
		this.memoryInGB = memoryInGB;
	}
	public double getDiskSpaceInGB() {
		return diskSpaceInGB;
	}
	public void setDiskSpaceInGB(double diskSpaceInGB) {
		this.diskSpaceInGB = diskSpaceInGB;
	}
	public int getTotalCores() {
		return totalCores;
	}
	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getDatacenter() {
		return datacenter;
	}
	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
