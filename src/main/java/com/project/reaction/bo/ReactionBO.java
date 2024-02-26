package com.project.reaction.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reaction.mapper.ReactionMapper;

@Service
public class ReactionBO {

	@Autowired
	private ReactionMapper reactionMapper;
	
	//input:diaryId, userId        output: X
	public void reactionToggle(int diaryId, int userId) {
		
		// like가 있으면
		// -- 삭제
		if (reactionMapper.selectReactionCountByDiaryIdOrUserId(diaryId, null) > 0) {
			reactionMapper.deleteReactionByDiaryIdUserId(diaryId, userId);
		} else {	// 없으면
			// -- 추가	
			reactionMapper.insertReaction(diaryId, userId);
		}
	}	
	
	public int getReactionCountByDiaryId(int diaryId) {
		return reactionMapper.selectReactionCountByDiaryIdOrUserId(diaryId, null);
	}
	
	public int getReactionByDiaryIdUserId(int diaryId, int userId) {
		return reactionMapper.selectReactionCountByDiaryIdOrUserId(diaryId, userId);
	}
	
	// input: postId, userId(null or)          output: boolean
	public boolean getReactionCountByDiaryIdUserId(int diaryId, Integer userId) {
		//비로그인이면 무조건 빈하트 => false
		if (userId == null) {
			return false;
		}
		
		// 로그인이 0보다 크면 (1이면) 채운다, 그렇지 않으면 false
		return reactionMapper.selectReactionCountByDiaryIdOrUserId(diaryId, userId) > 0;
	}
	
	public void deleteReactionByDiaryId(int diaryId) {
		reactionMapper.deleteReactionByDiaryId(diaryId);
	}
}
