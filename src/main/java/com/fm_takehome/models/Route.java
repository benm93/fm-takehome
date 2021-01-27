package com.fm_takehome.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
	
	@Id
    @GeneratedValue
    private Long routeId;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	Self links;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	
	Relationship relationships;
	String type;
	//Integer stopCount
}
