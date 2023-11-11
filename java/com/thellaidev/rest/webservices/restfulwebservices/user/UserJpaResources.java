package com.thellaidev.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.thellaidev.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.thellaidev.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.persistence.PostRemove;
import jakarta.validation.Valid;

@RestController
public class UserJpaResources {
	

	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJpaResources(PostRepository postRepository, UserRepository repo) {
		this.postRepository = postRepository;
		this.userRepository = repo;
	}
	
	@GetMapping( path = "/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping( path = "/jpa/users/{id}")
	public EntityModel<User> retrieveUser( @PathVariable int id ) {
		Optional<User> user = userRepository.findById(id);
		if( user.isEmpty() ){
			throw new UserNotFoundException("No User found with this id : " + id );
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers() );
		entityModel.add(link.withRel("all-Users"));
		
		// Adding another link : 
		//WebMvcLinkBuilder link_2 = linkTo(methodOn(this.getClass() ).retrieveAllUsers());
		entityModel.add( link.withRel("vanakam da Mapla"));
		return entityModel;
	}
	
	@DeleteMapping( path = "/jpa/users/{id}")
	public void deleteUser( @PathVariable int id ) {
		userRepository.deleteById(id);
	}
	
	@GetMapping( path = "/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser( @PathVariable int id ) {
		Optional<User> user = userRepository.findById(id);
		if( user.isEmpty() ){
			throw new UserNotFoundException(" There is no user with id : " + id );
		}
		return user.get().getPost();
	}
	
	@GetMapping( path = "/jpa/users/{id}/posts/{postId}")
	public Post retrieveSpecificPostsFromUser( @PathVariable int postId, @PathVariable int id ) {
		
		Optional<User> user = userRepository.findById(id);
		if( user.isEmpty() ){
			throw new UserNotFoundException(" There is no user with id : " + id );
		}
		
		Optional<Post> post = postRepository.findById(postId);
		if( post.isEmpty() ){
			throw new PostNotFoundException("There is no Post under user_id " + id +" with post_id " + postId );
		}
		return post.get();
	}
	
	
	@PostMapping( path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user ) { // @valid -> validata this RequestBody :
		
		User savedUser = userRepository.save( user );
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest() // "/jpa/users" -> Fetching the currentRequest path : 
						.path("/{id}") // "/jpa/users/{id}" -> Appending "/{id}" at end : 
						.buildAndExpand(savedUser .getId())// "/jpa/users/1" -> replacing user id value @{id}
						.toUri();
				
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping( path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser( @PathVariable int id, @Valid @RequestBody Post post ) {
		Optional<User> user = userRepository.findById(id);
		if( user.isEmpty() ){
			throw new UserNotFoundException("id : " + id );
		}
		post.setUser(user.get());
		Post savedPost = postRepository.save( post );		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest() // "/jpa/users/{id}/posts" -> Fetching the currentRequest path : 
						.path("/{id}") // "/jpa/users/{id}/posts/{id}" -> Appending "/{id" at end : 
						.buildAndExpand(savedPost .getId())// "/jpa/users/{id}/posts/ 1" -> replacing post id value @{id}
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
