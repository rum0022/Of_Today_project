package com.project.diary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryMapper {

	public List<Map<String, Object>> selectDiary();
}
