package com.project.todo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TodoContentMapper {

	public void updateTodoByCheckboxYn( 
			@Param("contentId") int contentId, 
			@Param("checkboxYn") boolean checkboxYn);
	
	public void deleteTodoByContentId(int contentId);
	
}