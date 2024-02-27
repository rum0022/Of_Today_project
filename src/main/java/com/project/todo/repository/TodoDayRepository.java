package com.project.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todo.Entity.TodoDayEntity;

public interface TodoDayRepository extends JpaRepository<TodoDayEntity, Integer>{

	public List<TodoDayEntity> findAllByOrderByIdDesc();
	
	public TodoDayEntity findByTodoDay(String todoDay);

	public List<TodoDayEntity> findAllByOrderByTodoDayDesc();
	

	
	
}
