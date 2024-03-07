package com.project.reaction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.reaction.bo.ReactionBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class ReactionRestController {
	
	@Autowired
	private ReactionBO reactionBO;

	@RequestMapping("/reaction/{diaryId}") // 겟이랑 포스트 다 가능이므로
	public Map<String, Object> reactionToggle(
			@PathVariable(name = "diaryId") int diaryId,
			HttpSession session) { 
			
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
			
		if (userId == null) {
			result.put("code", 300);
			result.put("error_message", "로그인이 되지 않은 사용자 입니다.");
			return result;
		}
		// BO 호출 -> reactionToggle
		reactionBO.reactionToggle(diaryId, userId);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}

}
