package com.technewsjavaapp.controllers;

import com.technewsjavaapp.model.Comment;
import com.technewsjavaapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/api/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }


    @GetMapping("/api/comments/{id}")
    public Comment getComment(@PathVariable int id) {
        return commentRepository.getById(id);
    }


    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }


    @PutMapping("/api/updateComment")
    public Comment updateComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }


    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) {
        commentRepository.deleteById(id);
    }
}
