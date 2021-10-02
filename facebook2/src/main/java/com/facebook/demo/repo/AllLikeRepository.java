package com.facebook.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facebook.demo.model.Comment;
import com.facebook.demo.model.Like;

public interface AllLikeRepository extends JpaRepository<Like,Integer>{
	@Query("from Like as l")
	public List<Like>findAll();
	
}
