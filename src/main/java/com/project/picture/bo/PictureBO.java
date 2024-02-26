package com.project.picture.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.diary.bo.DiaryTimeLineBO;
import com.project.diary.domain.DiaryPageView;
import com.project.memo.bo.MemoBO;
import com.project.memo.domain.Memo;
import com.project.picture.domain.Picture;
import com.project.user.bo.UserBO;
import com.project.user.entity.UserEntity;

@Service
public class PictureBO {

	@Autowired
	private MemoBO memoBO;
	
	@Autowired
	private DiaryTimeLineBO diaryTimeLineBO;

	@Autowired
	private UserBO userBO;
	
	public List<Picture> pictureColleteByUserId(Integer userId) {
		
		List<Picture> pictureList = new ArrayList<>();
		
		// 내가 쓴 일기 목록 다 가져오기
		List<DiaryPageView> diaryList = diaryTimeLineBO.generateDiaryPageViewByUserId(userId);
		
		// 내가 쓴 메모 목록 다 가져오기
		List<Memo> memoList = memoBO.getMemoListByuserId(userId);
		
		Picture picture = new Picture();
		
		// 일기 반복문 순회
		for (DiaryPageView diary : diaryList) {
			// 일기 세팅
			picture.setDiary(diary);
		    
		    pictureList.add(picture);
		}
		
		for (Memo memo : memoList) {
			picture.setMemo(memo);
			 // 글쓴이 정보
		    UserEntity user = userBO.getUserEntityByUserId(memo.getId());
		    picture.setUser(user);
			
			pictureList.add(picture);
		}
		return pictureList;
	}
}
