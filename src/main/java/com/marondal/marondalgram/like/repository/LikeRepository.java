package com.marondal.marondalgram.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marondal.marondalgram.like.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	// SELECT count(*) FROM `like` WHERE `postId` = #{};
	public int countByPostId(int postId);
	
	// SELECT count(*) FROM `like` WHERE `userId` = #{} AND `postId` = #{}
	public int countByUserIdAndPostId(int userId, int postId);
	
	// WHERE `userId` = #{} AND `postId` = #{}
	public Optional<Like> findByUserIdAndPostId(int userId, int postId);
	
	// DELETE FROM `like` WHERE `userId` = #{} AND `postId` = #{}
	@Transactional
	public void deleteByUserIdAndPostId(int userId, int postId);
	
	
}
