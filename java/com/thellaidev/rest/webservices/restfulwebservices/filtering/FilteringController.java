package com.thellaidev.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping( path = "/filtering" )
	public DummyBean filtering() {
		return new DummyBean( "value1", "Value_2", "Value_3" );
	}
	
	@GetMapping( path = "/filtering-list" )
	public List<DummyBean> filteringList() {
		return Arrays.asList( new DummyBean( "value1", "Value_2", "Value_3" ),
				new DummyBean( "value1", "Value_2", "Value_3" ));
	}

}
