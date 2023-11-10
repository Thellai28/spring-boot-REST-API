package com.thellaidev.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	private MessageSource messageSource; // Used to get the messages from stored messages i.e : messages.properties
	public HelloWorldController( MessageSource messageSource ) {
		this. messageSource = messageSource;
	}
	
	
	@GetMapping( path = "/hello-world"  )
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping( path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean( " Hello world bruh, pongal saptia nee?" );
	}
	
	@GetMapping( path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathvariable( @PathVariable String name ) {
		return new HelloWorldBean( "hello world " + name + " bruh.");
	}
	
	@GetMapping( path = "/hello-world-internationalized"  )
	public String helloWorldInternationalized() {
		Locale local = LocaleContextHolder.getLocale(); // This gets the locale values from the request header
		return messageSource.getMessage("superWomanDa", null, 
				"Sorry can't translate this in your language" , local );
		
	}
}
