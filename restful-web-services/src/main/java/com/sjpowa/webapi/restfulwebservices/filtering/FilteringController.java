package com.sjpowa.webapi.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBeans retrieveSomeBean() {
		return new SomeBeans("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBeans> retrieveAllBeans() {
		return Arrays.asList(new SomeBeans("value1", "value2", "value3"),
				new SomeBeans("value11", "value22", "value33")) ;
	}
	
}
