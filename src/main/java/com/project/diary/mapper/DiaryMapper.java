package com.project.diary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface DiaryMapper {

	public List<Map<String, Object>> selectDiary();
	
	public void insertDiary(
			@Param("userId") int userId, 
			@Param("userLoginId") String userLoginId, 
			@Param("content") String content, 
			@Param("decidedDay") String decidedDay, 
			@Param("imagePath") String imagePath);
}
