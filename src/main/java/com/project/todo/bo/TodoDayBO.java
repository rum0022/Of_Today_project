package com.project.todo.bo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todo.Entity.TodoDayEntity;
import com.project.todo.repository.TodoDayRepository;

@Service
public class TodoDayBO {
	
	@Autowired
	private TodoDayRepository todoDayRepository;
	
	public TodoDayEntity addTodoDay(int userId, Date todoDay) {
		
		return todoDayRepository.save(TodoDayEntity.builder()
				.userId(userId)
				.todoDay(todoDay)
				.build());
	}
}
