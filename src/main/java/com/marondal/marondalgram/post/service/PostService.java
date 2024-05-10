package com.marondal.marondalgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	public int addPost(int userId, String contents, MultipartFile imageFile) {
		
		String imagePath = FileManager.saveFile(userId, imageFile);
		
		return postRepository.insertPost(userId, contents, imagePath);
	}
	
	public List<PostDetail> getPostList(Integer id, int loginUserId) {
		
		List<Post> postList = postRepository.selectPostList(id);
		
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
	
	public int deletePost(int userId, int id) {
		
		// 로그인한 사용자가 작성한 게시글이 아닌 경우 
		Post post = postRepository.selectPost(id);
		if(userId != post.getUserId()) {
			
			// 삭제 대상이 잘못되었다. 
			logger.error("삭제 대상의 게시물의 작성자가 아닙니다! + " + userId);
			
			return -1;
		}
		
		FileManager.removeFile(post.getImagePath());
		
		// 파일 삭제 완료
		logger.info("파일 삭제 완료");
		
		likeService.deleteLikeByPostId(id);
		// 좋아요 삭제 완료
		logger.info("좋아요 삭제 완료");
		commentService.deleteCommentByPostId(id);
		// 댓글 삭제 완료
		logger.info("댓글 삭제 완료");
	
		return postRepository.deletePost(id);
	}
	
	

}
