package com.thellaidev.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain customFilterChain( HttpSecurity http ) throws Exception {
		
		http.authorizeHttpRequests( // Every request will be authenticated ( user & password )
				auth-> auth.anyRequest().authenticated()
				);
		
		// if a web page is not authenticated, a web page will be shown : 
		http.httpBasic(withDefaults());
		
		http.csrf().disable();
		
		return http.build();
	}

}
