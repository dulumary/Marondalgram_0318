package com.marondal.marondalgram.post.dto;

public class PostDetail {
	
	// 게시글 정보
	// 작성자 정보 
	private int id;
	private String contents;
	private String imagePath;
	
	private int userId;
	private String userLoginId;
	
	private int likeCount;
	
	// 로그인한 사용자의 좋아요 여부
	
	// 댓글 목록
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	

}
