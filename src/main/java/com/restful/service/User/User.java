package com.restful.service.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
@Entity
@ApiModel(description= "this is the USER class***")
public class User {
	@Size(min=2, message = "Name should have 2 character")
	private String name;
	@Id
	@GeneratedValue
	private Integer id;	
	@Past
	@ApiModelProperty(notes = "it can not be in past") //swagger anotation change 
	private Date dob;
	
	@OneToMany(mappedBy="user")
	private List<Post> post;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, Integer id, Date dob) {
		super();
		this.name = name;
		this.id = id;
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", dob=" + dob + "]";
	}
	
	

}
