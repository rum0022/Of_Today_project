package com.project.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.todo.Entity.TodoContentEntity;
import com.project.todo.Entity.TodoDayEntity;
import com.project.todo.bo.TodoBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/todo")
@Controller
public class TodoController {

	@Autowired
	private TodoBO todoBO;
	
	@GetMapping("/todo-list-view")
	public String todoListView(Model model, HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<TodoDayEntity> dayList = todoBO.getTodoDayByUserId(userId);
		List<TodoContentEntity> contentList = todoBO.getTodoContentByuserId(userId);
		
		model.addAttribute("dayList", "dayList");
		model.addAttribute("contentList", "contentList");
		model.addAttribute("viewName", "todo/todoList");
		return "template/layout";
	}
}
