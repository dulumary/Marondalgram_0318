package com.marondal.marondalgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.comment.dto.CommentDetail;
import com.marondal.marondalgram.comment.service.CommentService;
import com.marondal.marondalgram.common.FileManager;
import com.marondal.marondalgram.like.service.LikeService;
import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.dto.PostDetail;
import com.marondal.marondalgram.post.repository.PostRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private CommentService commentService;
	
	public int addPost(int userId, String contents, MultipartFile imageFile) {
		
		String imagePath = FileManager.saveFile(userId, imageFile);
		
		return postRepository.insertPost(userId, contents, imagePath);
	}
	
	public List<PostDetail> getPostList(int loginUserId) {
		List<Post> postList = postRepository.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			
			// 작성자 사용자 정보 
			User user = userService.getUser(post.getUserId());
			// 게시글 좋아요 개수 
			int likeCount = likeService.getLikeCount(post.getId());
			
			// 게시글에 로그인한 사용자가 좋아요를 했는지 여부 
			boolean isLike = likeService.isLikeByUserIdAndPostId(loginUserId, post.getId());
			
			// 게시글별 댓글 목록 
			List<CommentDetail> commentList = commentService.getCommentListByPostId(post.getId());
			
			PostDetail postDetail = new PostDetail();	
			postDetail.setId(post.getId());
			postDetail.setContents(post.getContents());
			postDetail.setImagePath(post.getImagePath());
			postDetail.setUserId(post.getUserId());
			postDetail.setUserLoginId(user.getLoginId());
			postDetail.setLikeCount(likeCount);
			postDetail.setLike(isLike);
			postDetail.setCommentList(commentList);
			postDetailList.add(postDetail);
			
		}
		return postDetailList;
		
		
	}

}
