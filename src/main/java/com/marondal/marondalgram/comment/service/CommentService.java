package com.marondal.marondalgram.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment addComment(int userId, int postId, String contents) {
		
		Comment comment = Comment.builder()
								.userId(userId)
								.postId(postId)
								.contents(contents)
								.build();
		
		return commentRepository.save(comment);
	}
	
	// 특정 게시글의 댓글 목록 돌려주는 기능
	public getCommentListByPostId(int postId) {
		
	}

}
