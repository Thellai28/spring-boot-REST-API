package com.thellaidev.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping( path = "/filtering" )
	public MappingJacksonValue filtering() {
		DummyBean dummyBean = new DummyBean("verkadalai","Ellurundaii", "chicken_biriyani", " beef_fry");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue( dummyBean );
		SimpleBeanPropertyFilter fieldsShouldRemain = SimpleBeanPropertyFilter.filterOutAllExcept("fied_2", "fied_3");
		FilterProvider filter = new SimpleFilterProvider().addFilter("someBeanFilters", fieldsShouldRemain);
		mappingJacksonValue.setFilters(filter);

		return mappingJacksonValue;
	}
	
	@GetMapping( path = "/filtering-list" )
	public MappingJacksonValue filteringList() {
		List<DummyBean> beanList = Arrays.asList( new DummyBean( "SoyaBean", "Carrot", "chicken", "mutton" ),
				new DummyBean( "parotta", "Dosa", "idly", "PaaniPuri" ));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanList);
		SimpleBeanPropertyFilter fieldsShouldRemain = SimpleBeanPropertyFilter.filterOutAllExcept("fied_1", "fied_4");
		FilterProvider filter = new SimpleFilterProvider().addFilter("someBeanFilters", fieldsShouldRemain);
		mappingJacksonValue.setFilters(filter);

		return mappingJacksonValue;
	}

}
