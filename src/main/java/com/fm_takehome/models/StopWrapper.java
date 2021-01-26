package com.fm_takehome.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopWrapper {
	void setData(List<Stop> d) {
		data = d;
	}
	
	public List<Stop> getData() {
		return data;
	}
	
	List<Stop> data;
}
