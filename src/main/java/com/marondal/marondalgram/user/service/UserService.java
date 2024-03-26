package com.marondal.marondalgram.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.common.EncryptUtils;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public int addUser(String loginId, String password, String name, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		return userRepository.insertUser(loginId, encryptPassword, name, email);
		
	}
	
	
	public boolean isDuplicateId(String loginId) {
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public User getUserByLoginIdAndPassword(String loginId, String password) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userRepository.selectUserByLoginIdAndPassword(loginId, encryptPassword);
	}
	
	
	public User getUser(int id) {
		return userRepository.selectUser(id);
	}
	

}
