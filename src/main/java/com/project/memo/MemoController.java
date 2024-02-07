package com.project.memo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/memo")
@Controller
public class MemoController {

	/**
	 * 메모목록 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/memo-list-view")
	public String memoListView(Model model) {
		model.addAttribute("viewName", "memo/memoList");
		return "template/layout";
	}
	
	@GetMapping("/memo-create-view")
	public String memoCreateView(Model model) {
		model.addAttribute("viewName", "memo/memoCreate");
		return "template/layout";
	}
}
