package com.project.picture.domain;

import com.project.diary.domain.DiaryPageView;
import com.project.memo.domain.Memo;
import com.project.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Picture {

	private UserEntity user;
	
	private Memo memo;
	
	private DiaryPageView diary;
}
