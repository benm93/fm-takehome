package com.fm_takehome.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute {

	@Id
	@GeneratedValue
	private Long attributeId;

	String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getDirection_destinations() {
		return direction_destinations;
	}

	public void setDirection_destinations(List<String> direction_destinations) {
		this.direction_destinations = direction_destinations;
	}

	public List<String> getDirection_names() {
		return direction_names;
	}

	public void setDirection_names(List<String> direction_names) {
		this.direction_names = direction_names;
	}

	public String getFare_class() {
		return fare_class;
	}

	public void setFare_class(String fare_class) {
		this.fare_class = fare_class;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public Integer getSort_order() {
		return sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	public String getText_color() {
		return text_color;
	}

	public void setText_color(String text_color) {
		this.text_color = text_color;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	String description;

	@ElementCollection
	List<String> direction_destinations;

	@ElementCollection
	List<String> direction_names;

	String fare_class;
	String long_name;
	String short_name;
	Integer sort_order;
	String text_color;
	Integer type;
}
