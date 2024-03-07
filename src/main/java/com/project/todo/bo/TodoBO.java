package com.project.todo.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todo.Entity.TodoContentEntity;
import com.project.todo.Entity.TodoDayEntity;
import com.project.todo.mapper.TodoContentMapper;
import com.project.todo.repository.TodoContentRepository;
import com.project.todo.repository.TodoDayRepository;

@Service
public class TodoBO {

	@Autowired
	private TodoContentRepository todoContentRepository;
	
	@Autowired
	private TodoDayRepository todoDayRepository;
	
	@Autowired
	private TodoContentMapper todoContentMapper;
	
	// day insert
	public TodoDayEntity addTodoDay(int userId, String todoDay) {
		
		return todoDayRepository.save(TodoDayEntity.builder()
				.userId(userId)
				.todoDay(todoDay)
				.build());
	}
	
	// TodoDay 전체 다 가져오기
	public List<TodoDayEntity> getTodoDayList() {
		return todoDayRepository.findAllByOrderByTodoDayDesc();
	}
	
	// select
	public List<TodoDayEntity> getTodoDayByUserIdList(int userId) {
		return todoDayRepository.findAllByUserIdOrderByTodoDayDesc(userId);
	}
	
	// select
	public TodoDayEntity getTodoDayAndUserIdByTodoDay(String todoDay, int userId) {
		return todoDayRepository.findByTodoDayAndUserId(todoDay, userId);
	}
	
	// select
	public TodoDayEntity getTodoDayAndUserId(int userId) {
		return todoDayRepository.findByUserId(userId);
	}
	
	// content insert
	public TodoContentEntity addTodoContent(int userId, String todoDay,
			String content, boolean checkboxYn) {
		
		return todoContentRepository.save(TodoContentEntity.builder()
				.userId(userId)
				.todoDay(todoDay)
				.content(content)
				.checkboxYn(checkboxYn)
				.build());
	}
	
	// select
	public List<TodoContentEntity> getTodoContentByuserIdAndDayId(int userId, int dayId) {
		return todoContentRepository.findAllByUserIdAndDayId(userId, dayId);
	}
	
	// select
	public List<TodoContentEntity> getTodoContentByuserId(int userId) {
		return todoContentRepository.findAllByUserId(userId);
	}
	
	// 캘린더에 뿌릴 컨텐츠 select
	public List<Map<String, String>> getTodoCalendar(int userId, String userLoginId) {
		
		
		//List<TodoDayEntity> dayList = todoDayRepository.findAllByUserIdOrderByTodoDayDesc(userId);
		List<TodoContentEntity> contentList = todoContentRepository.findAllByUserId(userId);
		
		List<Map<String, String>> resultList = new ArrayList<>();
		
			for (int i = 0; i < contentList.size(); i++) {
				Map<String, String> result = new HashMap<>();
				result.put("title", contentList.get(i).getContent());
				result.put("start", contentList.get(i).getTodoDay());
				
				resultList.add(result);
			}
		
		return resultList;
	}
	
	// insert
	public void createTodo(int userId, String todoDay,
			String content, boolean checkboxYn) {
		
		TodoDayEntity day = todoDayRepository.findByTodoDayAndUserId(todoDay,userId);
		
			if (day == null) {// 그룹테이블인 그 날짜가 있는지
				day = todoDayRepository.save(TodoDayEntity.builder() // save 하면서 리턴이 바로됨
						.userId(userId)
						.todoDay(todoDay)
						.build());
		
			todoContentRepository.save(TodoContentEntity.builder()
						.userId(userId)
						.dayId(day.getId())
						.todoDay(todoDay)
						.content(content)
						.checkboxYn(checkboxYn)
						.build());
				
			} else {
				todoContentRepository.save(TodoContentEntity.builder()
						.userId(userId)
						.dayId(day.getId())
						.todoDay(todoDay)
						.content(content)
						.checkboxYn(checkboxYn)
						.build());
			}
		}
	// 체크박스 유무 
	public void updateTodoByCheckboxYn(int contentId, boolean checkboxYn) {
		
		todoContentMapper.updateTodoByCheckboxYn(contentId, checkboxYn);
	}
	
	// delete
	public void deleteTodoContentByContentId(int contentId, int userId) {
		todoContentMapper.deleteTodoByContentId(contentId);
	}
}