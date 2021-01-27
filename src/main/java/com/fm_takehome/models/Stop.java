package com.fm_takehome.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {
	
	@Id
    @GeneratedValue
    private Long stopId;
	
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

	String id;
	
	String type;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="routeId", nullable=true)
//	Route route;
	
	//StopRelationships relationships;
	//Self links;
}
