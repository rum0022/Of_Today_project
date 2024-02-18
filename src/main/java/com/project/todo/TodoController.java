package com.project.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todo")
@Controller
public class TodoController {

	@GetMapping("/todo-list-view")
	public String todoListView(Model model) {
		
		model.addAttribute("viewName", "todo/todoList");
		return "template/layout";
	}
}
