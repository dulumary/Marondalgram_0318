package com.marondal.marondalgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public final static String FILE_UPLOAD_PATH = "D:\\dulumaryT\\web\\20231122\\springProject\\upload\\marondalgram";
	
	public static String saveFile(int userId, MultipartFile file) {
		
		// 같은 이름의 파일 처리 
		// 디렉토리(폴더)를 만들어서 파일 저장 
		// 로그인한 사용자 userId를 디렉토리 이름에 추가 
		// 현재시간 정보를 디렉토리 이름에 추가 
		// UNIX TIME : 1970년 1월 1일 부터 흐른 시간을 milli second (1/1000) 로 표현한 시간
		// ex) 3_523975832
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장 
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			// 파일저장 실패
			return null;
		}
		
		// 저장된 파일을 클라이언트에서 접근할 수 있는 url 문자열을 리턴
		// 파일 저장 경로 : D:\\dulumaryT\\web\\231122\\springProject\\upload\\marondalgram/3_23195209/test.png
		// url 규칙 : /images/3_23195209/test.png
		return "/images" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	public static boolean removeFile(String filePath) {  // /images/2_1711355716057/steak-3640560_640.jpg
		
		if(filePath == null) {
			return false;
		}
		
		//   D:\dulumaryT\web\231122\springProject\ upload\memo\2_1711355716057\steak-3640560_640.jpg
		// 삭제 대상 경로 
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(fullFilePath);
		
		// 파일이 존재하는지
		if(Files.exists(path)) {
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
			}
			
		}
		
//   D:\dulumaryT\web\231122\springProject\ upload\memo\2_1711355716057
		
		Path dirPath = path.getParent();
		
		if(Files.exists(dirPath)) {
			
			try {
				Files.delete(dirPath);
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
			}
			
		}
		
		
		return true;
		
	}

}
