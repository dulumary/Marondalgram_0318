package com.marondal.marondalgram.like.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marondal.marondalgram.like.domain.Like;

@SpringBootTest
class LikeServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LikeService likeService;
	// 테스트 케이스 설계 
	// addLike 메소드를 수행한다. - 리턴된 값이 null 이 아니다. 
	// deleteLike 메소드를 수행한다. - 리턴된 값이 null 이 아니다.
	@Test
	public void likeTest() {
		Like like = likeService.addLike(2, 4);
		
		logger.info("좋아요 추가 결과 : " + like.getCreatedAt());
		
		
//		assertNotNull(like);
//
//		int count = likeService.getLikeCount(2);
//		assertEquals(count, 3);
//		
//		like = likeService.deleteLike(2, 4);
//		assertNotNull(like);
	}


}
