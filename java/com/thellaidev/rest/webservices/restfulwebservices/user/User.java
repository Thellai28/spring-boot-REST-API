package com.thellaidev.rest.webservices.restfulwebservices.user;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity(name = "user_details")
public class User {
	protected User() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;

// while validating, error message will be thrown when the length of the name is less than 2 : 
	@Size( min = 2, message = "The length of the string should be miniumum of 2 characters")
	private String name;
	
	// Error message will be thrown, if the Future data is entered : 
	@Past( message = " the date entered should be in the past")
	private LocalDate birthdate;
	
	@OneToMany( mappedBy = "user")// single user can have many posts, so " one to many Relationship "
	@JsonIgnore
	private List<Post> post;

	public User(Integer id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}


	public List<Post> getPost() {
		return post;
	}


	public void setPost(List<Post> post) {
		this.post = post;
	}
}
