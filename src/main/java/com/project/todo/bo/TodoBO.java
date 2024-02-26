package com.project.todo.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todo.Entity.TodoContentEntity;
import com.project.todo.Entity.TodoDayEntity;
import com.project.todo.repository.TodoContentRepository;
import com.project.todo.repository.TodoDayRepository;

@Service
public class TodoBO {

	@Autowired
	private TodoContentRepository todoContentRepository;
	
	@Autowired
	private TodoDayRepository todoDayRepository;
	
	public TodoDayEntity addTodoDay(int userId, String todoDay) {
		
		return todoDayRepository.save(TodoDayEntity.builder()
				.userId(userId)
				.todoDay(todoDay)
				.build());
	}
	
	public List<TodoDayEntity> getTodoDayByUserId(int userId) {
		return todoDayRepository.findAllByUserId(userId);
	}
	
	public TodoDayEntity getTodoDayByTodoDay(String todoDay) {
		return todoDayRepository.findByTodoDay(todoDay);
	}
	
	public TodoContentEntity addTodoContent(int userId, String todoDay,
			String content, boolean checkboxYn) {
		
		return todoContentRepository.save(TodoContentEntity.builder()
				.userId(userId)
				.todoDay(todoDay)
				.content(content)
				.checkBoxYn(checkboxYn)
				.build());
	}
	
	public List<TodoContentEntity> getTodoContentByuserId(int userId) {
		return todoContentRepository.findAllByUserId(userId);
	}
	
	public void createTodo(int userId, String todoDay,
			String content, boolean checkboxYn) {
		
		TodoDayEntity day = todoDayRepository.findByTodoDay(todoDay);
		
		
			// 그룹테이블인 그 날짜가 있는지
			if (day == null) {
				// 없으면 add를 날짜랑 컨텐드 add 두개 다하고
				todoDayRepository.save(TodoDayEntity.builder()
						.userId(userId)
						.todoDay(todoDay)
						.build());
				
				// 날짜 저장되고 넘어갈때 dayId 받아주기
				todoContentRepository.save(TodoContentEntity.builder()
						.userId(userId)
						.dayId(day.getId())
						.todoDay(todoDay)
						.content(content)
						.checkBoxYn(checkboxYn)
						.build());
			} else {
				// 만약 있으면 id 가져올수 있음 -dayId
				// 이걸로 add를 contentId에 하면됨.
				todoContentRepository.save(TodoContentEntity.builder()
						.userId(userId)
						.todoDay(todoDay)
						.dayId(day.getId())
						.content(content)
						.checkBoxYn(checkboxYn)
						.build());
			}	
	}
}
