package ops.inventory.rest;

public class ServerSaveRequest {

	private String serverName;
	private int cpuCores;
	private long memoryInGG;
	private long diskSpaceInGB;
	private String applicationName;
	private String teamName;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getCpuCores() {
		return cpuCores;
	}
	public void setCpuCores(int cpuCores) {
		this.cpuCores = cpuCores;
	}
	public long getMemoryInGG() {
		return memoryInGG;
	}
	public void setMemoryInGG(long memoryInGG) {
		this.memoryInGG = memoryInGG;
	}
	public long getDiskSpaceInGB() {
		return diskSpaceInGB;
	}
	public void setDiskSpaceInGB(long diskSpaceInGB) {
		this.diskSpaceInGB = diskSpaceInGB;
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
	
}
