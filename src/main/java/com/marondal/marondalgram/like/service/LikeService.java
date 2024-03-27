package com.marondal.marondalgram.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.like.domain.Like;
import com.marondal.marondalgram.like.repository.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;
	
	public Like addLike(int userId, int postId) {
		
		Like like = Like.builder()
						.userId(userId)
						.postId(postId)
						.build();
		
		return likeRepository.save(like);
	}

}
