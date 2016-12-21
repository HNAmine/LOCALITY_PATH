package org.opendev.dto;

import java.util.List;

public class LegDto {

	List<StepDto> steps;

	public LegDto() {
		// TODO Auto-generated constructor stub
	}

	public List<StepDto> getSteps() {
		return steps;
	}

	public void setSteps(List<StepDto> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "LegDto [steps=" + steps + "]";
	}

}
