package com.monitech.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monitech.restapi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}