package com.sjpowa.webapi.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	// We have done a Versioning Example
	// In this case we have done three classes
	// because our client wants sometimes Name and LastName together
	// and in sometimes He/She wants it split
	
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Pippo Rossi");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Marley"));
	}
	
	// ===================================================================
	// REQUEST PARAMETER VERSIONING
	// PASSING PARAMETER VALUE IN THE URL
	
	// localhost:8080/person/param?version=1
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Pippo Rossi");
	}
	
	// localhost:8080/person/param?version=2
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Marley"));
	}
	
	// ===================================================================
	// HEADER VERSIONING
	// PASSING PARAMETER IN THE HEADER
	
	// localhost:8080/person/header
	// Header
	// Key = X-API-VERSION=1
	// Value = 1
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Pippo Rossi");
	}
	
	// localhost:8080/person/header
	// Header
	// Key = X-API-VERSION
	// Value = 2
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Marley"));
	}
	
	// ===================================================================
	// PRODUCES is another HEADER PARAMETER
	// also called Content Negotiation or Accept Versioning
	// Accept -> something
	// application/companyName-v1+json ---> is a MIME Type
	// https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types
	
	// in the value 'application' and '+json' are needed
	
	// localhost:8080/person/produces
	// Key = Accept
	// value = application/nameOfYourApplicationVersion+json
	@GetMapping(value = "/person/produces", produces = "application/companyName-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Pippo Rossi");
	}
	
	// localhost:8080/person/produces
	// Key = Accept
	// value = application/nameOfYourApplicationVersion+json
	@GetMapping(value = "/person/produces", produces = "application/companyName-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Marley"));
	}
	
}
