package com.project.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		
		// 요청 URL Path를 꺼낸다.
		String uri = request.getRequestURI();
		
		log.info("[@@@@@@@@@ prehandle] uri:{}", uri);
		
		// 페이지 권한검사 로직
		// 로그인 여부
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 비로그인 && /post 경로로 접근하는경우 => 로그인 페이지로 이동, 컨트롤러 수행 방지
		if (userId == null && uri.startsWith("/post")) {
			response.sendRedirect("/user/sign-in-view");
			return false; // 반드시 해야함 , 원래 요청에 대하여 컨트롤러 수행 안함.
		}
		
		// 로그인 && /user => 글목록 페이지로 이동, 컨트롤러 수행방지
		if (userId != null && uri.startsWith("/user")) {
			response.sendRedirect("/post/post-list-view");
			return false;  // 반드시 해야함 , 원래 요청에 대하여 컨트롤러 수행 안함.
		}
		
		
		return true; // 컨트롤러 수행
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView mav) {
		// view 객체가 있다는건 아직 jsp가 html로 변환되기 전
		log.info("[###### postHandle]");
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception ex) {
		
		// jsp가 html로 최종 변환된 후
		log.info("[$$$$$$$ afterCompletion]");
	}

}
