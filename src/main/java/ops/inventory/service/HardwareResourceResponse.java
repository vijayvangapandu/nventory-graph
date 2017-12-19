package ops.inventory.service;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HardwareResourceResponse implements Comparable<HardwareResourceResponse> {

	private int numberOfCores;
	private int memoryInGB;
	private int diskSpaceInGB;

	public int getNumberOfCores() {
		return numberOfCores;
	}

	public void setNumberOfCores(int numberOfCores) {
		this.numberOfCores = numberOfCores;
	}

	public int getMemoryInGB() {
		return memoryInGB;
	}

	public void setMemoryInGB(int memoryInGB) {
		this.memoryInGB = memoryInGB;
	}

	public int getDiskSpaceInGB() {
		return diskSpaceInGB;
	}

	public void setDiskSpaceInGB(int diskSpaceInGB) {
		this.diskSpaceInGB = diskSpaceInGB;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("numberOfCores", this.getNumberOfCores())
				.append("memoryInGB", this.getMemoryInGB()).append("diskSpaceInGB", this.getDiskSpaceInGB()).toString();
	}

	@Override
	public boolean equals(Object target) {
		if (!(target instanceof HardwareResourceResponse)) {
			return false;
		}

		HardwareResourceResponse targetResource = (HardwareResourceResponse) target;

		return new EqualsBuilder().append(this.getDiskSpaceInGB(), targetResource.getDiskSpaceInGB())
				.append(this.getMemoryInGB(), targetResource.getMemoryInGB())
				.append(this.getNumberOfCores(), targetResource.getNumberOfCores()).build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getDiskSpaceInGB()).append(this.getMemoryInGB())
				.append(this.getNumberOfCores()).toHashCode();
	}

	@Override
	public int compareTo(HardwareResourceResponse other) {

		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if (this == other) {
			return EQUAL;
		}
		if ((this.getNumberOfCores() == other.getNumberOfCores()
				&& (this.getDiskSpaceInGB() == other.getDiskSpaceInGB())
				&& (this.getMemoryInGB() == other.getMemoryInGB()))) {
			return EQUAL;
		}

		if (this.getMemoryInGB() < other.getMemoryInGB()) {
			return BEFORE;
		}

		if (this.getMemoryInGB() > other.getMemoryInGB()) {
			return AFTER;
		}
		if (this.getNumberOfCores() < other.getNumberOfCores()) {
			return BEFORE;
		}
		if (this.getNumberOfCores() > other.getNumberOfCores()) {
			return AFTER;
		}

		if (this.getDiskSpaceInGB() < other.getDiskSpaceInGB()) {
			return BEFORE;
		}
		return AFTER;
	}

}
