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
	
	// 특정 개시글의 좋아요 개수 돌려주는 기능
	public int getLikeCount(int postId) {
		
		return likeRepository.countByPostId(postId);
		
	}
	
	// 특정 사용자가 특정 게시글에 좋아요 했는지 여부 
	public boolean isLikeByUserIdAndPostId(int userId, int postId) {
		
	}

}
