package com.facebook.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facebook.demo.model.Comment;
import com.facebook.demo.model.Like;

@Repository
public interface LikeUserRepository extends JpaRepository<Like,Integer>{
	Like findByPostIdAndLikeUserId(int postId, int likeUserId);
}
