package com.project.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/main/main-view")
	public String mainView(Model model) {
		model.addAttribute("viewName", "main/main");
		return "template/layout";
	}
}
