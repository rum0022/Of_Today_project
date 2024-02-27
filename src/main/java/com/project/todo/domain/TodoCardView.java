package com.project.todo.domain;

import java.util.List;

import com.project.todo.Entity.TodoContentEntity;
import com.project.todo.Entity.TodoDayEntity;
import com.project.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TodoCardView {

	private UserEntity user;
	private TodoDayEntity day;
	private List<TodoContentEntity> contentList;
}
