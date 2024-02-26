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

	// day
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
	
	// content
	@Autowired
	private TodoContentRepository todoContentRepository;

	public TodoContentEntity addTodoContent(int userId, String todoDay,
			String content, boolean checkboxYn) {
		
		return todoContentRepository.save(TodoContentEntity.builder()
				.userId(userId)
				.todoDay(todoDay)
				.content(content)
				.checkBoxYn(checkboxYn)
				.build());
	}
	
	public List<TodoContentEntity> getTodoContentByDayId(int dayId) {
		return todoContentRepository.findAllByDayId(dayId);
	}
	
	public List<TodoContentEntity> getTodoContentByuserId(int userId) {
		return todoContentRepository.findAllById(userId);
	}
	
	public void createTodo(int userId, String todoDay,
			String content, boolean checkboxYn) {
		
		TodoDayEntity dayEntity = todoDayRepository.findByTodoDay(todoDay);
		// 그룹테이블인 그 날짜가 있는지
		
			if (dayEntity == null) {
				// 없으면 add를 컨텐트로 날짜랑 컨텐드 add 두개 다하고
				todoDayRepository.save(TodoDayEntity.builder()
						.userId(userId)
						.todoDay(todoDay)
						.build());
				
				// 날짜 저장되고 넘어갈때 dayid 받아주기
				todoContentRepository.save(TodoContentEntity.builder()
						.dayId(dayEntity.getId())
						.userId(userId)
						.todoDay(todoDay)
						.content(content)
						.checkBoxYn(checkboxYn)
						.build());
			}
			
			// 만약 있으면 id 가져올수 있음 -dayId
			// 이걸로 add를 contentId에 하면됨.
			if (dayEntity != null) {
				todoContentRepository.save(TodoContentEntity.builder()
						.userId(userId)
						.todoDay(todoDay)
						.dayId(dayEntity.getId())
						.content(content)
						.checkBoxYn(checkboxYn)
						.build());
			}
		
	}
}
