package com.technewsjavaapp.repository;

import com.technewsjavaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//A repository in Java is any class that fulfills the role of a data access object (DAO)—in other words, it contains data retrieval, storage, and search functionality
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //now to add query methods that will probably later be used by my controller
    User findUserByEmail(String email) throws Exception;

}




//autowire note
//The term autowiring refers to scanning a project and instantiating only the objects required for a class or method to run