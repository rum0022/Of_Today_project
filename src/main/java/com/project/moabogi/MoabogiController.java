package com.project.moabogi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.diary.bo.DiaryTimeLineBO;
import com.project.diary.domain.DiaryPageView;

import jakarta.servlet.http.HttpSession;

@Controller
public class MoabogiController {

	@Autowired
	private DiaryTimeLineBO diaryTimeLineBO;
	
	@GetMapping("/moabogi/moabogi-view")
	public String moabogi(Model model,HttpSession session) {
		
	 Integer userId = (Integer)session.getAttribute("userId");
	 String userLoginId = (String)session.getAttribute("userLoginId");
	 
	 List<DiaryPageView> diaryPageViewList = diaryTimeLineBO.generateDiaryPageView(userId);
	 
		model.addAttribute("diaryPageViewList", diaryPageViewList);
		model.addAttribute("viewName", "moabogi/moabogi");
		return "template/layout";
	}
}
