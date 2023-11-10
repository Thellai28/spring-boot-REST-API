package com.thellaidev.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResources {
	
	private UserDaoService service;

	public UserResources(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping( path = "/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping( path = "/users/{id}")
	public EntityModel<User> retrieveUser( @PathVariable int id ) {
		User user = service.findOne(id);
		if( user == null ){
			throw new UserNotFoundException("id : " + id );
		}
		
		EntityModel entityModel = EntityModel.of(user);
		// EntityModel will  like a wrapper, that will contain the data that is associated with user
		// and it also holds space to hold all the links, that you have to show along with the actual 
		// data...
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers() );
		// WebMvcLinkBuilder -> denotes the space in the entityModel,where you can store all the 
		// links, in WebMvcLinkBuilder, we have to give the co-ordinates of the methods and its 
		// class, so that the the link to that method will be directly shown as link : 
		entityModel.add(link.withRel("all-Users"));
		// Once we have added the links into the WebMvcLinkBuilder, add it to the entity manager.
		
		// Adding another link : 
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass() ).retrieveAllUsers());
		entityModel.add( link.withRel("vanakam da Mapla"));
		return entityModel;
	}
	
	@DeleteMapping( path = "/users/{id}")
	public void deleteUser( @PathVariable int id ) {
		service.deleteById(id);
	}
	
	
	@PostMapping( path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user ) { // @valid -> validata this RequestBody :
		
		User savedUser = service.save( user );
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser .getId())
						.toUri();
				
		return ResponseEntity.created(location).build();
	}
}
