package com.project.memo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.memo.domain.Memo;

@Mapper
public interface MemoMapper {

	public void insertMemo(
			@Param("userId") int userId, 
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	public List<Memo> selectMemoByuserId(Integer userId);
}
