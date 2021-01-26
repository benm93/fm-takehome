package com.fm_takehome.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteWrapper {
	
	void setData(List<Route> d) {
		data = d;
	}
	
	public List<Route> getData() {
		return data;
	}
	
	List<Route> data;
}
