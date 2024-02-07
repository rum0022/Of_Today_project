package com.project.memo.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.FileManagerService;
import com.project.memo.domain.Memo;
import com.project.memo.mapper.MemoMapper;

@Service
public class MemoBO {

	@Autowired
	private MemoMapper memoMapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	// insert
	public void addMemo(int userId, String loginId, 
			String subject, String content, MultipartFile file) {
		
		String imagePath = null;
		if (file != null) {
			imagePath = fileManagerService.saveFile(loginId, file);
		}
		
		memoMapper.insertMemo(userId, subject, content, imagePath);
	}
	
	// select
	public List<Memo> getMemoByuserId(int userId) { // Integer은 가져왔을때 실패하면 null가능 , int는 null이 안되므로 error남 
		return memoMapper.selectMemoByuserId(userId);
	}
}
