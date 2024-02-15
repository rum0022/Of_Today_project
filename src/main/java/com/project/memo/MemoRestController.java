package com.project.memo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.memo.bo.MemoBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/memo")
@RestController
public class MemoRestController {

	@Autowired
	private MemoBO memoBO;
	
	/**
	 * 메모 insert API
	 * @param subject
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required=false) MultipartFile file,
			HttpSession session) {
		
		// 글쓴이 번호 - session에 있는 userId를 꺼낸다
		int userId = (int)session.getAttribute("userId"); 
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// insert
		memoBO.addMemo(userId, userLoginId, subject, content, file);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		 	result.put("code", 200);
		 	result.put("result", "성공");
		 	
		return result; 	
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("memoId") int memoId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required=false) MultipartFile file,
			HttpSession session) {
		
		// 글쓴이 번호 - session에 있는 userId를 꺼낸다
			int userId = (int)session.getAttribute("userId"); 
			String userLoginId = (String)session.getAttribute("userLoginId");
			
		// update DB
			memoBO.updateMemoById(userId, userLoginId, memoId, subject, content, file);
		// 응답값
			Map<String, Object> result = new HashMap<>();
			result.put("code", 200);
			result.put("result", "성공");
			
			return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("memoId") int memoId,
			HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		// delete db
		memoBO.deleteMemoByMemoIdUserId(memoId, userId);
		
		// 응답값
			Map<String, Object> result = new HashMap<>();
		
			result.put("code", 200);
			result.put("result", "성공");
			return result;
	}
}
