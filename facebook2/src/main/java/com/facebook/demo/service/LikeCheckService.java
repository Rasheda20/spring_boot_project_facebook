package com.facebook.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facebook.demo.model.Like;
import com.facebook.demo.repo.AllLikeRepository;
import com.facebook.demo.repo.LikeUserRepository;

@Service
public class LikeCheckService {

	@Autowired
	private LikeUserRepository likeUserRepository;
	
	
	public Like like(int postId, int likeUserId) {
		Like checkLike = likeUserRepository.findByPostIdAndLikeUserId(postId, likeUserId);
		return checkLike;
		
	}
	
}
