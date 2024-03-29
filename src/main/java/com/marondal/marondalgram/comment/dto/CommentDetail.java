package com.marondal.marondalgram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDetail {
	
	private int id;
	private String contents;
	
	private int userId;
	private String userLoginId;

}
