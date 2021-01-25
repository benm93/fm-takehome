package com.fm_takehome.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopWrapper {
	void setData(List<Stop> d) {
		data = d;
	}
	
	List<Stop> getData(List<Stop> d) {
		return data;
	}
	
	List<Stop> data;
}
