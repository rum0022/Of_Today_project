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
	
	// 좋아요 개수
	private int reactionCount; // 없으면 0
		
	// 로그인 된 사람이 좋아요를 누른지 여부
	private boolean filledReaction;  //is로 네임을 시작하면 게터가 is로 시작함
}
