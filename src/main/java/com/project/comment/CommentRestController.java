package com.project.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;

	@PostMapping("/create")
	public Map<String, Object> creat(
			@RequestParam("diaryId") int diaryId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요");
		}
		
		// DB insert
		commentBO.addComment(diaryId, userId, content);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
