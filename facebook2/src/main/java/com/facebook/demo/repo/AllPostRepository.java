package com.facebook.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facebook.demo.model.Post;
import com.facebook.demo.model.User;

public interface AllPostRepository extends JpaRepository<Post,Integer>{
	
	@Query("from Post as pa")
	public List<Post>findAll();
//	<Post,Integer>
	
//	@Query("from Post as po join User as us")
//	public List<User>findAll();
}
