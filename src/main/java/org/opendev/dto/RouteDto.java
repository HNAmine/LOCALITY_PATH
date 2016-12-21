package org.opendev.dto;

import java.util.List;

public class RouteDto {

	List<LegDto> legs;

	public RouteDto() {
		// TODO Auto-generated constructor stub
	}

	public List<LegDto> getLegs() {
		return legs;
	}

	public void setLegs(List<LegDto> legs) {
		this.legs = legs;
	}

	@Override
	public String toString() {
		return "RouteDto [legs=" + legs + "]";
	}

}
