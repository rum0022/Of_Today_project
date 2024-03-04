package com.project.picture.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.diary.bo.DiaryBO;
import com.project.diary.entity.DiaryEntity;
import com.project.memo.bo.MemoBO;
import com.project.memo.domain.Memo;
import com.project.picture.domain.Picture;

@Service
public class PictureBO {

	@Autowired
	private MemoBO memoBO;
	
	@Autowired
	private DiaryBO diaryBO;
	
	public List<Picture> pictureColleteByUserId(Integer userId) {
		
		List<Picture> pictureList = new ArrayList<>();
		
		// 내가 쓴 일기 목록 다 가져오기
		List<DiaryEntity> diaryList = diaryBO.getDiaryListByUserId(userId);
		
		// 내가 쓴 메모 목록 다 가져오기
		List<Memo> memoList = memoBO.getMemoListByuserIdOrderByCreatedAt(userId);
		
		Picture picture = new Picture();
		
			// 일기 세팅
			picture.setDiaryList(diaryList);
		   // 메모세팅
			picture.setMemoList(memoList);
			 
			pictureList.add(picture);
		
		return pictureList;
	}
}
