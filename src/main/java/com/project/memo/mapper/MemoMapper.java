package com.project.memo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.project.memo.domain.Memo;

@Mapper
public interface MemoMapper {

	public void insertMemo(
			@Param("userId") int userId, 
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	public List<Memo> selectMemoListByuserId(Integer userId);
	
	public List<Memo> selectMemoListByuserIdOrderByCreatedAt(int userId);
	
	public Memo selectMemoByMemoIdUserId(
			@Param("memoId") int memoId,
			@Param("userId") int userId);
	
	public void updateMemoByMemoId(
			@Param("memoId") int memoId,
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	public int deleteMemoByMemoId(int memoId);
}
