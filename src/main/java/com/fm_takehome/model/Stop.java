package com.fm_takehome.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {
	public StopAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(StopAttributes attributes) {
		this.attributes = attributes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	StopAttributes attributes;
	String id;
	String type;
	
	//StopRelationships relationships;
	//Self links;
}
