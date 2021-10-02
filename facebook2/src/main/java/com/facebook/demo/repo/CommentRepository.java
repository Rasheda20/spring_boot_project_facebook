package com.facebook.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.facebook.demo.model.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer>{
	//@Query("from Post as p where p.user.id =:userId")
	@Query("from Comment as c where c.post.postId =:postId")
	public List<Comment>findCommentByPost(@Param("postId")int postId);
}
