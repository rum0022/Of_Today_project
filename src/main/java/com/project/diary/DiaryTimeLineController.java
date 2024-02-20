package com.project.diary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.diary.bo.DiaryTimeLineBO;
import com.project.diary.domain.DiaryPageView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/diary")
@Controller
public class DiaryTimeLineController {
	
	@Autowired
	private DiaryTimeLineBO diaryTimeLineBO;
	
	@GetMapping("/diary-list-view")
	public String diaryListView(Model model, HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<DiaryPageView> diaryPageViewList= diaryTimeLineBO.generateDiaryPageView(userId);
		
		model.addAttribute("diaryPageViewList", diaryPageViewList);
		model.addAttribute("viewName", "diary/diaryList");
		return "template/layout";
	}
}
