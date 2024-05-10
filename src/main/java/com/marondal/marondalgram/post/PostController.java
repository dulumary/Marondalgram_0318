package com.marondal.marondalgram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marondal.marondalgram.post.dto.PostDetail;
import com.marondal.marondalgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/timeline-view")
	public String memoList(
			@RequestParam(value="id", required=false) Integer id
			, Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<PostDetail> postList = postService.getPostList(id, userId);
		
		model.addAttribute("postList", postList);
		
		return "post/timeline";
	}

}
