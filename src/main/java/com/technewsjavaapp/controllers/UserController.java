package com.technewsjavaapp.controllers;

import com.technewsjavaapp.model.Post;
import com.technewsjavaapp.model.User;
import com.technewsjavaapp.repository.UserRepository;
import com.technewsjavaapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
