package com.project.diary.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.comment.bo.CommentBO;
import com.project.common.FileManagerService;
import com.project.diary.entity.DiaryEntity;
import com.project.diary.repository.DiaryRepository;
import com.project.reaction.bo.ReactionBO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaryBO {

	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private DiaryRepository diaryRepository;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private ReactionBO reactionBO;
	
	// select
	public List<DiaryEntity> getDiaryList() {
		return diaryRepository.findAllByOrderByDecidedDayDesc();
	}
	
	// 본인이 쓴 글만 가져오게 select
	public List<DiaryEntity> getDiaryListByUserId(int userId) {
		return diaryRepository.findAllByUserIdOrderByDecidedDayDesc(userId);
	}
	
	// insert
	public DiaryEntity addDiary(int userId, String userLoginId, String content, 
			Date decidedDay, boolean openYn, MultipartFile file) {
		
		String imagePath = null;
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		return diaryRepository.save(DiaryEntity.builder()
				.userId(userId)
				.content(content)
				.decidedDay(decidedDay)
				.openYn(openYn)
				.imagePath(imagePath)
				.build());
	}
	
	// 삭제하기
		public void deleteDiaryByDiaryIdUserId(int diaryId, int userId) {
			
			// 기존글 가져오기
			DiaryEntity diary = diaryRepository.findById(diaryId).orElse(null);
			
			if (diary == null) {
				log.error("[delete post] postId:{}, userId:{}", diaryId, userId);
				return;
			}
			
			// 글삭제
			diaryRepository.delete(diary);
			
			// 이미지 삭제하기
			if (diary.getImagePath() != null) {
			fileManagerService.deleteFile(diary.getImagePath());
			}	
			// 댓글들 삭제
			commentBO.deleteCommentById(diaryId);
			
			// 공감들 삭제
			reactionBO.deleteReactionByDiaryId(diaryId);
		}
}
