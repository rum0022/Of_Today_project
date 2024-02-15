package com.project.diary.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.FileManagerService;
import com.project.diary.entity.DiaryEntity;
import com.project.diary.repository.DiaryRepository;

@Service
public class DiaryBO {

	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private DiaryRepository diaryRepository;
	
	// select
	public List<DiaryEntity> getDiaryList() {
		return diaryRepository.findAllByOrderByIdDesc();
	}
	
	// insert
	public DiaryEntity addDiary(int userId, String userLoginId, String content, 
			Date decidedDay, MultipartFile file) {
		
		String imagePath = null;
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		return diaryRepository.save(DiaryEntity.builder()
				.userId(userId)
				.content(content)
				.decidedDay(decidedDay)
				.imagePath(imagePath)
				.build());
	}
}
