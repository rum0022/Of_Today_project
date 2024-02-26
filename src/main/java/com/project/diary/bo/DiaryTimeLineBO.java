package com.project.diary.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comment.bo.CommentBO;
import com.project.comment.domain.CommentView;
import com.project.diary.domain.DiaryPageView;
import com.project.diary.entity.DiaryEntity;
import com.project.reaction.bo.ReactionBO;
import com.project.user.bo.UserBO;
import com.project.user.entity.UserEntity;

@Service
public class DiaryTimeLineBO {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private DiaryBO diaryBO;
	
	@Autowired
	private ReactionBO reactionBO;
	
	
	
	// 모아보기에서 글 뿌리기
	public List<DiaryPageView> generateDiaryPageView(Integer userId) {
		
		List<DiaryPageView> diaryPageViewList = new ArrayList<>();
		
		// 글목록을 다 가져온다
		List<DiaryEntity> diaryList = diaryBO.getDiaryList();
		
		// 글목록 반복문 순회
		for (DiaryEntity diary : diaryList) {
			DiaryPageView diaryPageView = new DiaryPageView();
			
			// 글을 diaryPageView에 세팅한다
			diaryPageView.setDiary(diary);
			
			// 글쓴이 정보
			UserEntity user = userBO.getUserEntityByUserId(diary.getUserId());
			diaryPageView.setUser(user);
			
			// 댓글
			List<CommentView> commentList = commentBO.generateCommentViewListByDiaryId(diary.getId());
			diaryPageView.setCommentList(commentList);
			
			// 좋아요
			int reactionCount = reactionBO.getReactionCountByDiaryId(diary.getId());
			diaryPageView.setReactionCount(reactionCount);
			
			// 로그인된 사람이 좋아요를 했는지 여부 (비로그인 사용자 고려)
			//private boolean filledLike;
			boolean filledReaction = reactionBO.getReactionCountByDiaryIdUserId(diary.getId(), userId);
			diaryPageView.setFilledReaction(filledReaction);
						
			
			diaryPageViewList.add(diaryPageView);
		}

		return diaryPageViewList;
	}
	
	// 일기에서 내가 쓴 글 리스트로 뿌리기
	public List<DiaryPageView> generateDiaryPageViewByUserId(Integer userId) {
		
		List<DiaryPageView> diaryPageViewList = new ArrayList<>();
		
		// 내가 쓴 글목록을 다 가져온다
		List<DiaryEntity> diaryList = diaryBO.getDiaryListByUserId(userId);
		
		// 글목록 반복문 순회
		for (DiaryEntity diary : diaryList) {
			DiaryPageView diaryPageView = new DiaryPageView();
			
			// 글을 diaryPageView에 세팅한다
			diaryPageView.setDiary(diary);
			
			// 글쓴이 정보
			UserEntity user = userBO.getUserEntityByUserId(diary.getUserId());
			diaryPageView.setUser(user);
			
			// 댓글
			List<CommentView> commentList = commentBO.generateCommentViewListByDiaryId(diary.getId());
			diaryPageView.setCommentList(commentList);
			
			// 좋아요
			int reactionCount = reactionBO.getReactionCountByDiaryId(diary.getId());
			diaryPageView.setReactionCount(reactionCount);
			
			boolean filledReaction = reactionBO.getReactionCountByDiaryIdUserId(diary.getId(), userId);
			diaryPageView.setFilledReaction(filledReaction);
			
			diaryPageViewList.add(diaryPageView);
		}

		return diaryPageViewList;
	}
}
