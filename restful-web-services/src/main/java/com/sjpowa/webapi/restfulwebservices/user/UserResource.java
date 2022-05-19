package com.sjpowa.webapi.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// THIS IS OUR CONTROLLER
// IN FACT SOMEONE CAN CALL IT UserController
@RestController
public class UserResource {

	// So we take all the data from the UserDaoService
	// and autowire it here
	@Autowired
	private UserDaoService service;
	
	// GET /users
	// Retrieve All Users
	@GetMapping(path = "/users")
	public List<Users> retrieveAllUsers() {
		return service.findAll();
	}
	
	// GET /user/{id}
	// Retrieve User(int id)
	@GetMapping("/users/{id}")
	public EntityModel<Users> retrieveUser(@PathVariable int id) {
		Users user = service.findOne(id);
		
		if(user == null)
			throw new UserNotFoundException("id-" + id);
			
		//"all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<Users> resource = EntityModel.of(user);
		
		// now i want to add a link to my resource
		// how we do it?
		// ControllerLinkBuilder: will allow us to create links from methods
		// BEFORE DOING IT,
		// WE DO A STATIC IMPORT OF ALL THE STATIC METHODS INSIDE THE CONTROLLER
		WebMvcLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		// so now that we have the user,
		// we have the link from the method
		// how can we add the link to the user after our search?
		resource.add(linkTo.withRel("all-users"));
		
		// HATEOAS
			
		return resource;
	}
	
	// POST Request
	// Down here we just do a POST Request with Postman
	// and we are able to save the user
	// in fact, then, if u do a GET Request on retrieveAll
	// u should be able to see the INSERT done!
//	@PostMapping("/users")
//	public void createUser02(@RequestBody User user) {
//		service.save(user);
//	}
	
	// INPUT - details of user
	// OUTPUT - CREATED & Return the created URI
	// If we do our POST insert without a FIELD
	// like the ID that we have SET as AUTOINCREMENT,
	// We need to CREATE an EMPTY CTOR in our User Class
	// otherwise our only CTOR with all FIELD cannot
	// satisfy our POST Request
	// ========================================================================
	// @Valid - We have copied in the pom.xml file
	// the dependency to use the validation
	// so now we go in User.class to set some Rules/Validations
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
			Users savedUser = service.save(user);
			
			// Now we want to return a STATUS OF CREATED
			// This means that when we create a new User, with a POST
			// u see that there is NO RESPONSE in the HEADER(Postman)
			
			// SO WHEN WE DO A CREATION WE WANT TO GIVE A FEEDBACK
			// WITH A Status of Created
			
			//CREATED
			// users/4
			
			// How do I create the location of the resorce
			// which was created
			// How do I create the URI
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest() // /user
				.path("/{id}")	// /user/{id} [append to the previous ' /user '
				.buildAndExpand(savedUser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
			
			// if returns 201 Created
			// means that the Resources are correctly created
			// and
			// we are also returning the Location of the created user
			// in a Header
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Users user = service.deleteById(id);
		
			if(user == null)
				throw new UserNotFoundException("id-" + id);
			
	}
	
}
