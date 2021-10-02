package com.facebook.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facebook.demo.model.Post;



@Repository
public interface PostCommentRepository extends JpaRepository<Post,Integer> {
	@Query("select p from Post p where p.postId = :postId ")
	public Post getPostByPostId(@Param("postId") int pid);
}
