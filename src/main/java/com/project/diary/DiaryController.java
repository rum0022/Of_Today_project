package com.project.diary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/diary")
@Controller
public class DiaryController {

	@GetMapping("/diary-list-view")
	public String diaryListView(Model model) {
		model.addAttribute("viewName", "diary/diaryList");
		return "template/layout";
	}
}
