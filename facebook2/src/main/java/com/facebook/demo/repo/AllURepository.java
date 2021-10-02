package com.facebook.demo.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facebook.demo.model.User;

public interface AllURepository extends JpaRepository<User,Integer>{
	@Query("from User as u")
	public List<User> findAll();
}
