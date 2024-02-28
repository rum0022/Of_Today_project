package com.project.todo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TodoMapper {

	public void updateTodoByCheckboxYn(
			@Param("userId") int userId, 
			@Param("contentId") int contentId, 
			@Param("checkboxYn") boolean checkboxYn);
	
	public int deleteTodoByContentId(int contentId);
	
}
