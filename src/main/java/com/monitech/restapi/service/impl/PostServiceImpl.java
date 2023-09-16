package com.monitech.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitech.restapi.model.Post;
import com.monitech.restapi.repository.PostRepository;
import com.monitech.restapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long post_id, Post post) {
        Optional<Post> existingPost = postRepository.findById(post_id);

        if (!existingPost.isPresent()) {
            throw new RuntimeException("Post not found with id: " + post_id);
        }

        post.setPost_id(post_id);
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long post_id) {
        return postRepository.findById(post_id).orElse(null);
    }

    @Override
    public Post deletePost(Long post_id) {
        Optional<Post> existingPost = postRepository.findById(post_id);

        if (!existingPost.isPresent()) {
            throw new RuntimeException("Post not found with id: " + post_id);
        }

        postRepository.deleteById(post_id);
        return existingPost.get();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}