package com.project.picture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.picture.bo.PictureBO;
import com.project.picture.domain.Picture;

import jakarta.servlet.http.HttpSession;

@Controller
public class PictureController {
	
	@Autowired
	private PictureBO pictureBO;

	@GetMapping("/picture/picture-view")
	public String pictureView(HttpSession session, Model model) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<Picture> pictureList = pictureBO.pictureColleteByUserId(userId);
		
		model.addAttribute("pictureList", pictureList);
		model.addAttribute("viewName", "picture/picture");
		
		return "template/layout";
	}
}
