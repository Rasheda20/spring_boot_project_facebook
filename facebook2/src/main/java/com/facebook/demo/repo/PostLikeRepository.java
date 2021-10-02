package com.facebook.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facebook.demo.model.Post;

public interface PostLikeRepository extends JpaRepository<Post,Integer>{

}
