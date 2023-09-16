package com.monitech.restapi.service;

import java.util.List;

import com.monitech.restapi.model.Post;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Long post_id, Post post);

    Post getPostById(Long post_id);

    Post deletePost(Long post_id);

    List<Post> getAllPosts();
}