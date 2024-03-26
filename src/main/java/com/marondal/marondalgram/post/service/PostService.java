package com.marondal.marondalgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.common.FileManager;
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
	
	public int addPost(int userId, String contents, MultipartFile imageFile) {
		
		String imagePath = FileManager.saveFile(userId, imageFile);
		
		return postRepository.insertPost(userId, contents, imagePath);
	}
	
	public List<PostDetail> getPostList() {
		List<Post> postList = postRepository.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			
			User user = userService.getUser(post.getUserId());
			
			PostDetail postDetail = new PostDetail();	
			postDetail.setId(post.getId());
			postDetail.setContents(post.getContents());
			postDetail.setImagePath(post.getImagePath());
			postDetail.setUserId(post.getUserId());
			postDetail.setUserLoginId(user.getLoginId());
			
			postDetailList.add(postDetail);
			
		}
		return postDetailList;
		
		
	}

}
