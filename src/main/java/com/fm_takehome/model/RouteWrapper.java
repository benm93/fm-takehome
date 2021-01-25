package com.fm_takehome.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteWrapper {
	
	void setData(List<Route> d) {
		data = d;
	}
	
	List<Route> getData(List<Route> d) {
		return data;
	}
	
	List<Route> data;
}
