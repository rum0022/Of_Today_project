package com.project.todo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.todo.bo.TodoDayBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/todo")
@RestController
public class TodoDayRestcontroller {
	
	@Autowired
	private TodoDayBO todoDayBO;

	@PostMapping("/calendar")
	public Map<String, Object> calendar(
			@RequestParam("todoDay") @DateTimeFormat(pattern = "yyyy-MM-dd") Date todoDay,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		// insert DB
		todoDayBO.addTodoDay(userId, todoDay);
		
		result.put("code", 200);
		result.put("result", "성공");
		
 		return result;
		
	}
}
