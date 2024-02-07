package com.project.memo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.FileManagerService;
import com.project.memo.mapper.MemoMapper;

@Service
public class MemoBO {

	@Autowired
	private MemoMapper memoMapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public void addMemo(int userId, String loginId, 
			String subject, String content, MultipartFile file) {
		
		String imagePath = null;
		if (file != null) {
			imagePath = fileManagerService.saveFile(loginId, file);
		}
		
		memoMapper.insertMemo(userId, subject, content, imagePath);
	}
}
