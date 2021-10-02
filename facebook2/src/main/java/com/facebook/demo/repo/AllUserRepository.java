package com.facebook.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facebook.demo.model.Post;
import com.facebook.demo.model.User;

public interface AllUserRepository extends JpaRepository<User,Integer>{
	@Query("from User as u")
	public List<User>findAll();
	
}
