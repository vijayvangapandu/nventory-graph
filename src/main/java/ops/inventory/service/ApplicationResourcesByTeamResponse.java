package ops.inventory.service;

import java.util.ArrayList;
import java.util.List;

public class ApplicationResourcesByTeamResponse {

	private double totalMemoryInGB;
	private double totalDiskSpaceInGB;
	private int totalCores;
	private float totalCost;
	
	private List<TeamApplicationsResourcesResponse> applicationResources=new ArrayList<>();

	public double getTotalMemoryInGB() {
		return totalMemoryInGB;
	}
	
	public double getTotalDiskSpaceInGB() {
		return totalDiskSpaceInGB;
	}
	
	public int getTotalCores() {
		return totalCores;
	}
	
	public float getTotalCost() {
		return totalCost;
	}
	
	public List<TeamApplicationsResourcesResponse> getApplicationResources() {
		return applicationResources;
	}
	

	public void addTotalMemoryInGB(double totalMemoryInGB) {
		this.totalMemoryInGB += totalMemoryInGB;
	}

	

	public void addTotalDiskSpaceInGB(double totalDiskSpaceInGB) {
		this.totalDiskSpaceInGB += totalDiskSpaceInGB;
	}

	

	public void addTotalCores(int totalCores) {
		this.totalCores += totalCores;
	}

	

	public void addTotalCost(float totalCost) {
		this.totalCost += totalCost;
	}


	public void addApplicationResources(TeamApplicationsResourcesResponse applicationResource) {
		this.applicationResources.add(applicationResource);
	}

	

}
