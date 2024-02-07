package com.project.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

	// 실제 업로드 된 이미지가 저장될 경로 (서버 주소)
	public static final String FILE_UPLOAD_PATH ="C:\\Users\\PC2212\\Desktop\\choiareum\\7_project\\workspace\\images/";
	
	// input : file원본 , userLoginId<-폴더명을 짓기 위함  output:이미지경로
	public String saveFile(String loginId, MultipartFile file) {
		// 폴더 (디렉토리) 생성
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);
		if (directory.mkdir() == false) { // 폴더 생성 실패 시 이미지 경로는 null로 리턴
			return null;
		}
		
		// 파일 업로드
		try {
			byte[] bytes = file.getBytes();
			// ** 한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기
			Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // 이미지 업로드 실패시 null 리턴
		}
		
		// 파일 업로드가 성공했으면 이미지 주소 패스 리턴
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
}
