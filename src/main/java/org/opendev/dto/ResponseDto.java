package org.opendev.dto;

import java.util.List;

public class ResponseDto {

	List<RouteDto> routes;

	public ResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public List<RouteDto> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteDto> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "ResponseDto [routes=" + routes + "]";
	}

}
