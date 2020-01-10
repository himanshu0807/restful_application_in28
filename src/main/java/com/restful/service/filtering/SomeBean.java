package com.restful.service.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	
	private String feild1;
	private String feild12;
//	@JsonIgnore
	private String feild3;
public SomeBean(String feild1, String feild12, String feild3) {
	super();
	this.feild1 = feild1;
	this.feild12 = feild12;
	this.feild3 = feild3;
}
public String getFeild1() {
	return feild1;
}
public void setFeild1(String feild1) {
	this.feild1 = feild1;
}
public String getFeild12() {
	return feild12;
}
public void setFeild12(String feild12) {
	this.feild12 = feild12;
}
public String getFeild3() {
	return feild3;
}
public void setFeild3(String feild3) {
	this.feild3 = feild3;
}
	
	
}
