package com.project.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todo.Entity.TodoDayEntity;

public interface TodoDayRepository extends JpaRepository<TodoDayEntity, Integer>{

}
