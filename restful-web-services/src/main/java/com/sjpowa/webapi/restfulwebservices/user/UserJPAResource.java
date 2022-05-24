package com.sjpowa.webapi.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	// With @Autowired Annotation we can use/inject the methods from these classes/interfaces

	// So we take all the data from the UserDaoService with its methods
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// GET /users
	// Retrieve All Users
	@GetMapping(path = "/jpa/users")
	public List<Users> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	// GET /user/{id}
	// Retrieve User(int id)
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Users> retrieveUser(@PathVariable int id) {
		// Optional means that, the id searched is null or not null
		// this user will return a proper object
		Optional<Users> user = userRepository.findById(id);
		
		if(!user.isPresent()) // instead of user == null we use this one
			throw new UserNotFoundException("id-" + id);
			
		//"all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<Users> resource = EntityModel.of(user.get());
											// we have to get() the user if it's !null
		
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
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
			Users savedUser = userRepository.save(user);
			
			// Now we want to return a STATUS OF CREATED
			// This means that when we create a new User, with a POST
			// u see that there is NO RESPONSE in the HEADER(Postman)
			
			// SO WHEN WE DO A CREATION WE WANT TO GIVE A FEEDBACK
			// WITH A Status of Created
			
			//CREATED
			// users/4
			
			// How do I create the location of the resource
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
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<Users> userOptional = userRepository.findById(id); // // I take the detail of the user
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts(); // get the all the Posts of an User
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
			Optional<Users> userOptional = userRepository.findById(id); // I want to know which user has to create a post
			
			if(!userOptional.isPresent()) {
				throw new UserNotFoundException("id-" + id);
			}
			
			// I have the user variable right now //
			// So we got the user //
			Users users = userOptional.get();
			
			// Now I have to map the User to the Post //
			// We have set the user into the post //
			post.setUsers(users);
			
			// save method from PostRepository - save the post created in the @RequestBody //
			// We are saving the post to the db //
			postRepository.save(post);
			
			// Returning the location of where is the created post!
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest() // /user
				.path("/{id}")	// /user/{id} [append to the previous ' /user '
				.buildAndExpand(post.getId()).toUri();
										// now we have to append the id of the Post
			
			return ResponseEntity.created(location).build();
			
	}
	
	@DeleteMapping("/jpa/posts/{id}")
	public void deletePost(@PathVariable int id) {
		postRepository.deleteById(id);
	}
	
}
