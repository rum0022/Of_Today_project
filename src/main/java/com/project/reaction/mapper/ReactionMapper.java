package com.project.reaction.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReactionMapper {
	
	public int selectReactionCountByDiaryIdOrUserId(
			@Param("diaryId") int diaryId,
			@Param("userId") Integer userId);  // null 허용
	
	public void insertReaction(
			@Param("diaryId") int diaryId,
			@Param("userId") int userId); 
	
	public void deleteReactionByDiaryIdUserId(
			@Param("diaryId") int diaryId,
			@Param("userId") int userId);
	
	public void deleteReactionByDiaryId(int diaryId);

}
