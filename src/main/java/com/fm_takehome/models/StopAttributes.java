package com.fm_takehome.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopAttributes {
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAt_street() {
		return at_street;
	}
	public void setAt_street(String at_street) {
		this.at_street = at_street;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(Integer vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public Integer getWheelchair_boarding() {
		return wheelchair_boarding;
	}
	public void setWheelchair_boarding(Integer wheelchair_boarding) {
		this.wheelchair_boarding = wheelchair_boarding;
	}
	
	@Id
    @GeneratedValue
    private Long id;
	
	String address;
	String at_street;
	String description;
	String municipality;
	String name;
	Integer vehicle_type;
	Integer wheelchair_boarding;
}
