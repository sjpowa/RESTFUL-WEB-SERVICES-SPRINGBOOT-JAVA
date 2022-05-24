package com.sjpowa.webapi.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	// When one uses IDENTITY strategy, then, database can automatically assign a next value.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	// When we call a Post -> automatically Post tries to fetch the user
	// and when we call an Users -> automatically Users try to fetch a post
	// To avoid that we use:
	// fetch=FetchType.LAZY => it means that unless you call it, it will not try and fetch the details
	// @JsonIgnore for the users because when we want to see all the Posts of an User
	// on localhost:8080/jpa/users/1 /posts
	// we just want to see only the description of the Post and the id of the user
	// IMPORTANT = will be a conflict when the app will try to get the posts from Users Class
	// and get at the same time the user from the Post Class.. -> that's why we hide the user from Post Class
	@ManyToOne(fetch=FetchType.LAZY) // One User can do more Posts
	@JsonIgnore
	private Users users;

	// EMPTY CTOR
	public Post() {
		
	}
	
	// CTOR
	public Post(String description) {
		super();
		this.description = description;
	}

	// GETTERS and SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
		
}
