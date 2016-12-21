package org.opendev.dto;

public class StepDto {

	private LocalityDto start_location;
	private LocalityDto end_location;

	public StepDto() {
		// TODO Auto-generated constructor stub
	}

	public LocalityDto getStart_location() {
		return start_location;
	}

	public void setStart_location(LocalityDto start_location) {
		this.start_location = start_location;
	}

	public LocalityDto getEnd_location() {
		return end_location;
	}

	public void setEnd_location(LocalityDto end_location) {
		this.end_location = end_location;
	}

	@Override
	public String toString() {
		return "StepDto [start_location=" + start_location + ", end_location=" + end_location + "]";
	}

}
