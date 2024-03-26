package com.marondal.marondalgram.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.marondal.marondalgram.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);

	public int selectCountByLoginId(@Param("loginId") String loginId);
	
	public User selectUserByLoginIdAndPassword(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	public User selectUser(@Param("id") int id);
}
