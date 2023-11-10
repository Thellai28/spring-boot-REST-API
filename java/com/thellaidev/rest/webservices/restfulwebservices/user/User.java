package com.thellaidev.rest.webservices.restfulwebservices.user;
import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
	private Integer id;
	
	// while validating, error message will be thrown when the length of the name is less than 2 : 
		@Size( min = 2, message = "The length of the string should be miniumum of 2 characters")
		private String name;
		
		// Error message will be thrown, if the Future data is entered : 
		@Past( message = " the date entered should be in the past")
		private LocalDate birthdate;
	
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
}
