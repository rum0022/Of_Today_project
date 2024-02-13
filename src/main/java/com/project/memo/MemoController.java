package com.project.memo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.memo.bo.MemoBO;
import com.project.memo.domain.Memo;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/memo")
@Controller
public class MemoController {

	@Autowired
	private MemoBO memoBO;
	
	/**
	 * 메모목록 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/memo-list-view")
	public String memoListView(Model model, HttpSession session) {
	
	 	Integer userId = (Integer)session.getAttribute("userId");
	 	// 로그인이 되어있어야 하므로 권한검사도 하기(로그인여부조회)
	 	if (userId == null) {
	 		return "redirect:/user/sign-in-view";
	 	}
		List<Memo> memoList = memoBO.getMemoListByuserId(userId);
		
		model.addAttribute("memoList", memoList);
		model.addAttribute("viewName", "memo/memoList");
		return "template/layout";
	}
	
	/**
	 * 메모작성화면
	 * @param model
	 * @return
	 */
	@GetMapping("/memo-create-view")
	public String memoCreateView(Model model) {
		model.addAttribute("viewName", "memo/memoCreate");
		return "template/layout";
	}
	
	@GetMapping("/memo-detail-view")
	public String memoDetailView(
			@RequestParam("memoId") int memoId,
			HttpSession session,
			Model model) {
		
		int userId = (int)session.getAttribute("userId");
		
		Memo memo = memoBO.getMemoByMemoIdUserId(memoId, userId);
		
		model.addAttribute("memo", memo);
		model.addAttribute("viewName", "memo/memoDetail");
		return "template/layout";
	}
}
