package com.restful.service.User;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
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
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserJPARepository userJPARepository;
	
	@Autowired
	private PostJPARepository postJPARepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return userJPARepository.findAll();
	}
	//hateoas implemented in here 
	//all users . SERVER_PATH +
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id)
	{
		Optional<User> user = userJPARepository.findById(id);
		if (!user.isPresent())
		
			throw new UserNotFoundException("id-"+ id);
			Resource<User> resource = new Resource<User>(user.get());
			//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			//resource.add(linkTo.withRel("all-users"));
			return resource;		
		
	}
	
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		/*if (service.saveUser(user)!= null)
		return "successfully added";
		else 
			return "not added";*/
		User saved = userJPARepository.save(user);
		//status 
		//create the URI
		//path is used to append ; toUri give the url
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		
			 userJPARepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/post")
	public List<Post> getUserPosts(@PathVariable int id)
	{
		Optional<User> user = userJPARepository.findById(id);
		
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User id not Present");
		}
	 return user.get().getPost();
	}
	
	@PostMapping("/jpa/users/{id}/post")
	public HttpStatus createUserPosts(@RequestBody Post post, @PathVariable int id)
	{
		Optional<User> userOptional = userJPARepository.findById(id);
		
		if(!userOptional.isPresent())
		{
			throw new UserNotFoundException("User id not Present");
		}
		
		User user1 = userOptional.get();
		post.setUser(user1);
		
		postJPARepository.save(post);
		
		return HttpStatus.OK;
		
	}
}
