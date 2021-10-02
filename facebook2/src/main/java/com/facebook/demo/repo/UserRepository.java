package com.facebook.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.facebook.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	@Query("select u from User u where u.userEmail = :userEmail ")
	public User getUserByUserName(@Param("userEmail") String userEmail);
}
