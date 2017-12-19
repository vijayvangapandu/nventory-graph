package ops.inventory.service;

import java.util.List;

public class ApplicationResourcesResponse {

	private String applicationName;
	private double totalMemoryInGB;
	private double totalDiskSpaceInGB;
	private int totalCores;
	private float totalCost;
	
	private List<ServerResourcesResponse> serverResources;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public double getTotalMemoryInGB() {
		return totalMemoryInGB;
	}

	public void setTotalMemoryInGB(double totalMemoryInGB) {
		this.totalMemoryInGB = totalMemoryInGB;
	}

	public double getTotalDiskSpaceInGB() {
		return totalDiskSpaceInGB;
	}

	public void setTotalDiskSpaceInGB(double totalDiskSpaceInGB) {
		this.totalDiskSpaceInGB = totalDiskSpaceInGB;
	}

	public int getTotalCores() {
		return totalCores;
	}

	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public List<ServerResourcesResponse> getServerResources() {
		return serverResources;
	}

	public void setServerResources(List<ServerResourcesResponse> serverResources) {
		this.serverResources = serverResources;
	}
	public void addServerResourcesResponse(ServerResourcesResponse resp) {
		serverResources.add(resp);
	}
	
	public double addMemory(double memory) {
		totalMemoryInGB += memory;
		return totalMemoryInGB;
	}
	
	public double addDiskSpace(double diskSpace) {
		totalDiskSpaceInGB += diskSpace;
		return totalDiskSpaceInGB;
	}
	
	public int addCPUCores(int cpuCores) {
		totalCores += cpuCores;
		return totalCores;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	
	public float addCost(double cost) {
		this.totalCost += cost;
		return totalCost;
	}
	
	
}
