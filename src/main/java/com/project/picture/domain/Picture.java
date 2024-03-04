package com.project.picture.domain;

import java.util.List;

import com.project.diary.entity.DiaryEntity;
import com.project.memo.domain.Memo;
import com.project.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Picture {

	private UserEntity user;
	
	private List<Memo> memoList;
	
	private List<DiaryEntity> diaryList;
}
