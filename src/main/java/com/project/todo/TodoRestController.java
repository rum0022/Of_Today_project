package com.project.todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	/**
	 * todo INSERT 구현
	 * @param todoDay
	 * @param content
	 * @param checkboxYn
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> contentCreate(
			@RequestParam("todoDay")  String todoDay,
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

	/**
	 * todo 일정완료 api
	 * @param contentId
	 * @param checkboxYn
	 * @param session
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("contentId") int contentId,
			@RequestParam("checkboxYn") boolean checkboxYn,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		todoBO.updateTodoByCheckboxYn(contentId, checkboxYn);
		
		result.put("code", 200);
		result.put("result", "성공");
		
 		return result;
	}
	
	/**
	 * todo delete
	 * @param contentId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("contentId") int contentId,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		todoBO.deleteTodoContentByContentId(contentId, userId);
		
		result.put("code", 200);
		result.put("result", "성공");
		
 		return result;
		
	}
	
	// calendar db연동 api
	@GetMapping("/todo-calendar")
	public List<Map<String, String>> todoCalendar(HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId");
		
//		List<Map<String, Object>> resultList = new ArrayList<>();
//		
//		Map<String, Object> content = new HashMap<>();
//		
//		content.put("title", "학원가기");
//		content.put("start", "2024-03-25");
//		
//		resultList.add(content);
 		return todoBO.getTodoCalendar(userId, userLoginId);
	}
}