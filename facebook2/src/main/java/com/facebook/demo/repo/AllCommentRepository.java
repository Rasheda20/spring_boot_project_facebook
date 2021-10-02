package com.facebook.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facebook.demo.model.Comment;



public interface AllCommentRepository extends JpaRepository<Comment,Integer>{
	@Query("from Comment as c")
	public List<Comment>findAll();
}
