package com.project.diary.domain;

import java.util.List;

import com.project.comment.domain.CommentView;
import com.project.diary.entity.DiaryEntity;
import com.project.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DiaryPageView {

	private UserEntity user;
	
	private List<CommentView> commentList;
	
	private DiaryEntity diary;
}
