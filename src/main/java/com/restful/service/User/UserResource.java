package com.restful.service.User;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.Resource;

import com.restful.service.Exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return service.findAll();
	}
	//hateoas implemented in here 
	//all users . SERVER_PATH +
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id)
	{
		User user = service.findUser(id);
		if (service.findUser(id).getId()==null)
		
			throw new UserNotFoundException("id-"+ id);
			Resource<User> resource = new Resource<User>(user);
			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			resource.add(linkTo.withRel("all-users"));
			return resource;		
		
	}
	
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		/*if (service.saveUser(user)!= null)
		return "successfully added";
		else 
			return "not added";*/
		User saved = service.saveUser(user);
		//status 
		//create the URI
		//path is used to append ; toUri give the url
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id)
	{
		if (service.deleteUser(id)==null)
		{
			throw new UserNotFoundException("User not present, id ="+ id);
		}
		else 
			return service.deleteUser(id);
	}
}
