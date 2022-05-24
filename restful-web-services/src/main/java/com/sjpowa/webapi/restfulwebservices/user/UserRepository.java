package com.sjpowa.webapi.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// What is the Entity we want to manage?
// JpaRepository<Users, Integer> => The Entity we want to manage is Users
// What type is the Primary Key of Users? It's Integer type
@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

}
