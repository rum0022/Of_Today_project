package com.project.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todo.Entity.TodoContentEntity;

public interface TodoContentRepository extends JpaRepository<TodoContentEntity, Integer>{

	public List<TodoContentEntity> findAllById(int dayId);
	
	public List<TodoContentEntity> findAllByDayId(int dayId);
	
	
	
	
}
