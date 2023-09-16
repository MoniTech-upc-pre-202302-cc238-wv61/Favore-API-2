package com.monitech.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Post;
import com.monitech.restapi.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/favore/v1")
public class PostController {

    private final PostService postService;

    private void validatePost(Post post) {
        if (post.getBudgetAmount() == null || post.getBudgetAmount() <= 0) {
            throw new ValidationException("Budget amount is required and should be greater than 0");
        }
        if (post.getTitle() == null) {
            throw new ValidationException("Title is required");
        }
        if (post.getUser() == null) {
            throw new ValidationException("User reference is required");
        }
    }

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //URL: http://localhost:8080/api/favore/v1/posts
    //Method: POST
    @Transactional
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        validatePost(post);
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    //URL: http://localhost:8080/api/favore/v1/posts
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    //URL: http://localhost:8080/api/favore/v1/posts/{post_id}
    //Method: GET
    @Transactional
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "post_id") Long post_id) {
        Post post = postService.getPostById(post_id);
        return ResponseEntity.ok(post);
    }

    //URL: http://localhost:8080/api/favore/v1/posts/{post_id}
    //Method: PUT
    @Transactional
    @PutMapping("/posts/{post_id}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "post_id") Long post_id,@RequestBody Post post) {
        validatePost(post);
        Post updatedPost = postService.updatePost(post_id, post);
        return ResponseEntity.ok(updatedPost);
    }

    //URL: http://localhost:8080/api/favore/v1/posts/{post_id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<Post> deletePost(@PathVariable(value = "post_id") Long post_id) {
        Post deletedPost = postService.deletePost(post_id);
        return ResponseEntity.ok(deletedPost);
    }
}
