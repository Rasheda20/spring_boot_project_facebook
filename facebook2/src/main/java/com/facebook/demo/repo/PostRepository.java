package com.facebook.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.facebook.demo.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	@Query("from Post as p where p.user.id =:userId")
	public List<Post> findPostByUser(@Param("userId")int userId);
	
}
