package com.project.todo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.todo.bo.TodoBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/todo")
@RestController
public class TodoRestController {
	
	@Autowired
	private TodoBO todoBO;
	
	@PostMapping("/create")
	public Map<String, Object> contentCreate(
			@RequestParam("todoDay") String todoDay,
			@RequestParam("content") String content,
			@RequestParam("checkboxYn") boolean checkboxYn,
			Model model, 
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		// db
		
		todoBO.createTodo(userId, todoDay, content, checkboxYn);
		
		result.put("code", 200);
		result.put("result", "성공");
		
 		return result;
	}

}
