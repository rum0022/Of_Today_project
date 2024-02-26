package com.project.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comment.domain.Comment;
import com.project.comment.domain.CommentView;
import com.project.comment.mapper.CommentMapper;
import com.project.user.bo.UserBO;
import com.project.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	// insert
	public void addComment(int diaryId, int userId, String content) {
		 commentMapper.insertComment(diaryId, userId, content);
	}
	
	// select 전체
	public List<Comment> getComment() {
		return commentMapper.selectComment();
	}
	
	// select by diaryId
	public List<CommentView> generateCommentViewListByDiaryId(int diaryId) {
		// 결과 리스트 만들기
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글에 해당하는 댓글 목록 가져오기
		List<Comment> commentList = commentMapper.selectCommentByDiaryId(diaryId);
		
		// 반복문 순회 Comment -> CommentView => 리스트에 넣기
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 1개
			commentView.setComment(comment);
			
			// 댓글쓴이
			UserEntity user = userBO.getUserEntityByUserId(comment.getUserId());
			commentView.setUser(user);
			
			// 결과 리스트에 담기
			commentViewList.add(commentView);	
		}
		return commentViewList;
	}
	
	public void deleteCommentById(int id) {
		commentMapper.deleteCommentById(id);
	}
	
}
