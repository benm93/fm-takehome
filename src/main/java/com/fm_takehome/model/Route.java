package com.fm_takehome.model;

import java.util.List;

public class Route {
	Attribute attributes;	
	
	public Attribute getAttributes() {
		return attributes;
	}
	public void setAttributes(Attribute attributes) {
		this.attributes = attributes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Self getLinks() {
		return links;
	}
	public void setLinks(Self links) {
		this.links = links;
	}
	public Relationship getRelationships() {
		return relationships;
	}
	public void setRelationships(Relationship relationships) {
		this.relationships = relationships;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	String id;
	Self links;
	Relationship relationships;
	String type;
}
