package com.thellaidev.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"fied_3", "fied_2"}) 
// you can use this as an alternative to @JsonIgnore. 
// The main difference between them is @JsonIgnore is field level property & @JsonIgnoreProperties 
// is a class level property.
public class DummyBean {
	@JsonIgnore
	private String fied_1;
	
	
	private String fied_2;
	private String fied_3;
	public DummyBean(String fied_1, String fied_2, String fied_3) {
		super();
		this.fied_1 = fied_1;
		this.fied_2 = fied_2;
		this.fied_3 = fied_3;
	}
	public String getFied_1() {
		return fied_1;
	}
	public void setFied_1(String fied_1) {
		this.fied_1 = fied_1;
	}
	public String getFied_2() {
		return fied_2;
	}
	public void setFied_2(String fied_2) {
		this.fied_2 = fied_2;
	}
	public String getFied_3() {
		return fied_3;
	}
	public void setFied_3(String fied_3) {
		this.fied_3 = fied_3;
	}
	
	
	

}
