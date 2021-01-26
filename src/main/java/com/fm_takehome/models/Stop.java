package com.fm_takehome.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
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
	

    //@JoinColumn(name="loan", referencedColumnName="loan_id")
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	StopAttributes attributes;
	
	@Id
	String id;
	
	String type;
	
	//StopRelationships relationships;
	//Self links;
}
