package com.project.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.comment.domain.Comment;

@Mapper
public interface CommentMapper {

	public void insertComment(
			@Param("diaryId") int diaryId, 
			@Param("userId") int userId, 
			@Param("content") String content);
	
	public List<Comment> selectComment();
	
	public List<Comment> selectCommentByDiaryId(int diaryId);
	
	public void deleteCommentById(int id);
}
