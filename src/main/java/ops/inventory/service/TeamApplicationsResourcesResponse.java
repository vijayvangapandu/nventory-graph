package ops.inventory.service;

import java.util.List;

public class TeamApplicationsResourcesResponse {

	private String teamName;
	private double totalMemoryInGB;
	private double totalDiskSpaceInGB;
	private int totalCores;
	
	private List<ApplicationResourcesResponse> applicationResources;

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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<ApplicationResourcesResponse> getApplicationResources() {
		return applicationResources;
	}

	public void setApplicationResources(List<ApplicationResourcesResponse> applicationResources) {
		this.applicationResources = applicationResources;
	}
	
	public void addApplicationResourcesResponse(ApplicationResourcesResponse resp) {
		applicationResources.add(resp);
	}

}