package com.sjpowa.webapi.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// This is our First Bean to Store User Details
@Entity
@ApiModel(description = "All the details about the user.") // adding customized documentation on our Swagger Doc.
public class Users {

	// When one uses IDENTITY strategy, then, database can automatically assign a next value.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// message = ... -> we see our msg after a POST of an User
	// with less of 2 Char in the name
	// in the Details
	@Size(min = 2, message = "The name should have at least 2 characters!")
	@ApiModelProperty(notes="The name should have at least 2 characters!") // adding customized documentation on our Swagger Doc.
	private String name;
	
	// @Past is a CONSTRAINT that gives u the rule to never go forward ur ACTUAL DATE
	@Past
	@ApiModelProperty(notes="The Birthday cannot be set after the Current-Date!")
	private Date birthDate;
	
	// an User can create a List of Post //
	// Which is the field that owns the relationship?
	// mappedBy = " name of the field in Post of User"
	// in Post Class we have called our user field as Users users
	// that's why mappedBy = "users"
	// We just generate getters and setters of this List<Post>, no CTOR or toString
	// NO CTOR because we don't have to generate a Post from this class!!!
	@OneToMany(mappedBy = "users")
	private List<Post> posts;
	
	// EMPTY CTOR
	// We add the EMPTY CTOR because when we do a POST (INSERT of AN USER)
	// without the id, cuz it's autoincremented, we need the EMPTY CTOR
	// so the id can be generated
	// and
	// we don't have the error
	// a CTOR with 3 pamareter (with the id in it too),
	// can't satisfy the request!!!
	// Remember, the machine is not stupid, but we are!
	protected Users() {
		
	}

	// CTOR
	public Users(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	// toString METHOD
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
