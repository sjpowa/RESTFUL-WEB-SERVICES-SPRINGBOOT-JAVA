package com.sjpowa.webapi.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringControllerDynamic {

	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveSomeBeans() {
		SomeBeansDynamic someBeansDynamic = new SomeBeansDynamic("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBeansDynamic);

		mapping.setFilters(filters);

		return mapping;
	}
	
	@GetMapping("/filtering-dynamic-list")
	public MappingJacksonValue retrieveAllBeans() {
		List<SomeBeansDynamic> asList = Arrays.asList(new SomeBeansDynamic("value1", "value2", "value3"),
				new SomeBeansDynamic("value1", "value2", "value3"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(asList);

		mapping.setFilters(filters);
		
		return mapping;
	}
	
	// With SimpleBeanPropertyFilter.filterOutAllExcept we say that we want to show those fields
	// We use a FilterProvider to say from which class and which fields we want to show
	// MappingJacksonValue will take the object values, will use the filter and will return the values we wanted
	
}
