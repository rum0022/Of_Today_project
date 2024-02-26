package com.project.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/todo")
@Controller
public class TodoController {

	
	
	@GetMapping("/todo-list-view")
	public String todoListView(Model model, HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		model.addAttribute("viewName", "todo/todoList");
		return "template/layout";
	}
}
