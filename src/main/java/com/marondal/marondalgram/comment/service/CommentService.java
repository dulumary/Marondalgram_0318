package com.marondal.marondalgram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.dto.CommentDetail;
import com.marondal.marondalgram.comment.repository.CommentRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	public Comment addComment(int userId, int postId, String contents) {
		
		Comment comment = Comment.builder()
								.userId(userId)
								.postId(postId)
								.contents(contents)
								.build();
		
		return commentRepository.save(comment);
	}
	
	// 특정 게시글 댓글 목록 돌려주는 기능
	public List<CommentDetail> getCommentListByPostId(int postId) {
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentDetail> commentDetailList = new ArrayList<>();
		
		for(Comment comment:commentList) {
			
			User user = userService.getUser(comment.getUserId());
			
			CommentDetail commentDetail = CommentDetail.builder()
													.id(comment.getId())
													.contents(comment.getContents())
													.userId(comment.getUserId())
													.userLoginId(user.getLoginId())
													.build();
			
			commentDetailList.add(commentDetail);
		}
		return commentDetailList;
		
	}
	
	
}
