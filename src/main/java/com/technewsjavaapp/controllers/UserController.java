package com.technewsjavaapp.controllers;

import com.technewsjavaapp.model.Post;
import com.technewsjavaapp.model.User;
import com.technewsjavaapp.repository.UserRepository;
import com.technewsjavaapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
           List<Post> postList = u.getPosts();
           for (Post p : postList) {
               p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
           }
        }
        return userList;
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        //get single user
        User returnUser = userRepository.getById(id);
        //get posts by that user
        List<Post> postList = returnUser.getPosts();
        for (Post p : postList) {
            p.setVoteCount(voteRepository.countVotesByPostId(id));
        }
        return returnUser;
    }

    //@RequestBody annotation—which will map the body of this request to a transfer object, then deserialize the body onto a Java object for easier use
    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) {
        //Encrypt password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return user;
    }

    //The @PathVariable will allow us to enter the int id into the request URI as a parameter, replacing the /{id} in @PutMapping("/api/users/{id}")
    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User tempUser = userRepository.getById(id);

        if(!tempUser.equals(null)) {
            user.setId(tempUser.getId());
            userRepository.save(user);
        }
        return user;
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

}











//controller note
//When we talk about controllers in Spring Boot Java,
//we're referring to how we'll process requests and
//responses both to and from the API.
//This concept is comparable to routes in Express.js,
//but the execution will differ in Java.
//The controllers that we create will manage the processing flow
//of the API
//-----------//
//part2
//add the CRUD methods that will allow us to
//actually create, read, update, and delete information to and from the database
