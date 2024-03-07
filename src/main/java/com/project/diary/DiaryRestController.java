package com.project.diary;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.diary.bo.DiaryBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/diary")
@RestController
public class DiaryRestController {

	@Autowired
	private DiaryBO diaryBO;
	
	/**
	 * 다이어리 게시 API
	 * @param content
	 * @param decidedDay
	 * @param openYn
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content") String content,
			@RequestParam("decidedDay") @DateTimeFormat(pattern = "yyyy-MM-dd") Date decidedDay,
			@RequestParam("openYn") boolean openYn,
			@RequestParam(value = "file", required=false) MultipartFile file,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		// insert DB
		diaryBO.addDiary(userId, userLoginId, content, decidedDay, openYn, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		
 		return result;
	}
	
	/**
	 * 다이어리 delete
	 * @param diaryId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("diaryId") int diaryId,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		// delete DB
		diaryBO.deleteDiaryByDiaryIdUserId(diaryId, userId);
		
		result.put("code", 200);
		result.put("result", "성공");
		
 		return result;
	}
}
