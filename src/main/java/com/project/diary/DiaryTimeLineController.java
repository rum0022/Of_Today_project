package com.project.diary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.comment.bo.CommentBO;
import com.project.comment.domain.Comment;
import com.project.diary.bo.DiaryBO;
import com.project.diary.entity.DiaryEntity;

@RequestMapping("/diary")
@Controller
public class DiaryTimeLineController {
	
	@Autowired
	private CommentBO commentBO;

	@Autowired
	private DiaryBO diaryBO;
	
	@GetMapping("/diary-list-view")
	public String diaryListView(Model model) {
		
		List<DiaryEntity> diaryList = diaryBO.getDiaryList();
		List<Comment> commentList = commentBO.getComment();
		
		model.addAttribute("diaryList", diaryList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("viewName", "diary/diaryList");
		return "template/layout";
	}
}
