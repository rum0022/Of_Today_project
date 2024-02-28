package com.project.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.todo.bo.TodoTimeLineBO;
import com.project.todo.domain.TodoCardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/todo")
@Controller
public class TodoController {

	@Autowired
	private TodoTimeLineBO todoTimeLineBO;
	
	@GetMapping("/todo-list-view")
	public String todoListView(Model model, HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		List<TodoCardView> todoCardViewList = todoTimeLineBO.generateTodoCardView(userId);
		
		model.addAttribute("todoCardViewList", todoCardViewList);
		model.addAttribute("viewName", "todo/todoList");
		return "template/layout";
	}
}
