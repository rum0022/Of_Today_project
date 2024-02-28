package com.project.todo.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todo.Entity.TodoContentEntity;
import com.project.todo.Entity.TodoDayEntity;
import com.project.todo.domain.TodoCardView;
import com.project.user.bo.UserBO;
import com.project.user.entity.UserEntity;

@Service
public class TodoTimeLineBO {

	@Autowired
	private TodoBO todoBO;
	
	@Autowired
	private UserBO userBO;
	
	
	public List<TodoCardView> generateTodoCardView(Integer userId) {
		
		List<TodoCardView> todoCardViewList = new ArrayList<>();
		// 날짜목록을 다 가져온다
		List<TodoDayEntity> dayList = todoBO.getTodoDayByUserIdList(userId);
		
		for (TodoDayEntity day : dayList) {
			TodoCardView todoCardView = new TodoCardView();
			
			// 날짜를 todoCardView에 세팅한다
			todoCardView.setDay(day);
			
			// 글쓴이 정보
			UserEntity user = userBO.getUserEntityByUserId(day.getUserId());
			todoCardView.setUser(user);
			
			//내용
			List<TodoContentEntity> contentList = todoBO.getTodoContentByuserId(day.getUserId());
			todoCardView.setContentList(contentList);
			
			todoCardViewList.add(todoCardView);
			
		}
		return todoCardViewList;
	}
}
