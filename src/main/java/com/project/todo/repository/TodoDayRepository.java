package com.project.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todo.Entity.TodoDayEntity;

public interface TodoDayRepository extends JpaRepository<TodoDayEntity, Integer>{
	
	public TodoDayEntity findByTodoDayAndUserId(String todoDay, int userId);
	
	public List<TodoDayEntity> findAllByUserIdOrderByTodoDayDesc(int userId);
	
	public List<TodoDayEntity> findAllByOrderByTodoDayDesc();
	

	
	
}
