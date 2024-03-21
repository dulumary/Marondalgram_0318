package com.marondal.marondalgram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	// md5 암호화 기능
	// 암호화할 문자열을 전달 받고, 암호화된 결과 문자열을 돌려준다. 
	public static String md5(String message) {
		
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			
			// byte 배열을 16진수 형태의 문자열로 만드는 과정
			for(int i = 0; i < digest.length; i++) {
				// 비트 연산
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return result;
		
	}

}
