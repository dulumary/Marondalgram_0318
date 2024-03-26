package com.marondal.marondalgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostRestController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/post/create")
	public Map<String, String> createPost(
			@RequestParam("contents") String contents
			, @RequestParam("imageFile") MultipartFile imageFile
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postService.addPost(userId, contents, imageFile);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}

}
