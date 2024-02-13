package com.project.memo.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.FileManagerService;
import com.project.memo.domain.Memo;
import com.project.memo.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	// 목록 화면 select
	public List<Memo> getMemoListByuserId(int userId) { // Integer은 가져왔을때 실패하면 null가능 , int는 null이 안되므로 error남 
		return memoMapper.selectMemoListByuserId(userId);
	}
	
	// 상세 화면 select
	public Memo getMemoByMemoIdUserId(int memoId, int userId) {
		return memoMapper.selectMemoByMemoIdUserId(memoId, userId);
	}
	
	// 메모 수정
	public void updateMemoById(int userId, String userLoginId, int memoId,
			String subject, String content, MultipartFile file) {
		
		// 기존 글 가져오기
		Memo memo = memoMapper.selectMemoByMemoIdUserId(memoId, userId);
		
		if (memo == null) { // 드문경우 기존글을 가져왔는데 없는경우
			log.info("[글 수정] post is null. postId:{}, userId:{}", memoId, userId);
			return;
		}
		
		String imagePath = null;
		
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
			
			// 업로드 성공 시 기존 이미지가 있으면 제거
			if (imagePath != null && memo.getImagePath() != null) {
			// 업로드 성공하고 기존 이미지 있으면 서버의 파일제거
				fileManagerService.deleteFile(memo.getImagePath());
		}
		
		memoMapper.updateMemoByMemoId(memoId, subject, content, imagePath);	
	}
  }
}	
