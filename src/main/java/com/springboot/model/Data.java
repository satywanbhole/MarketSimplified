package com.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
@Table(name="data")
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonProperty(value = "date",access = Access.AUTO)
	private String date;
	@JsonProperty(value = "nav",access = Access.AUTO)
	private String 	nav;
	public Integer getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getNav() {
		return nav;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setNav(String nav) {
		this.nav = nav;
	}
	public Data(Integer id, String date, String nav) {
		super();
		this.id = id;
		this.date = date;
		this.nav = nav;
	}
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", date=" + date + ", nav=" + nav + "]";
	}
	
	

}
