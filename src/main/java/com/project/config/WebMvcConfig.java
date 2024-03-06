package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.common.FileManagerService;
import com.project.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private PermissionInterceptor intorceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(intorceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/static/**", "/error", "/user/sign-out")
		;
	}
	
	// 웹이미지 path와 서버에 업로드 된 실제 이미지와 매핑 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**")  // 웹의 이미지 패스: http://localhost/images/aaaa_1705483558718/winter-8425500_640.jpg
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);  //맥은 슬래시 두개 윈도우는 3개!, 파일의 실제위치
	}
}
