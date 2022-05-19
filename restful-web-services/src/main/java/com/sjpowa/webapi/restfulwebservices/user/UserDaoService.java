package com.sjpowa.webapi.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

// How this can be managed by SPRING?!?
// Well u have to use the annotation @Components
@Component
public class UserDaoService {

	private static List<Users> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static {
		users.add(new Users(1, "Adam", new Date()));
		users.add(new Users(2, "Eve", new Date()));
		users.add(new Users(3, "Jack", new Date()));
	}
	
	// FIND ALL THE USERS
	public List<Users> findAll(){
		return users;
	}
	
	// SAVE AN USER
	public Users save(Users user) {
		if(user.getId() == null)
			user.setId(++usersCount);
		users.add(user);
		
		return user;
	}
	
	// FIND AN USER
	public Users findOne(int id) {
		for(Users user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
			return null;
	}
	
	// DELETE AN USER
	public Users deleteById(int id) {
		Iterator<Users> iterator = users.iterator();
		while (iterator.hasNext()) {
			Users user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
