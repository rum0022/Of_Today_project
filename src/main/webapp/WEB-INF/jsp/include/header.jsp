<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<div class="h-100 header d-flex justify-content-between">
	<%-- logo --%>
	<div class="logo d-flex justify-content-between align-items-center">
		<h1 class="text-green ml-3">OF TODAY</h1>
	</div>
	<div class="logo d-flex justify-content-between align-items-end">
	<%-- 로그인 시 --%>
		<span>님 안녕하세요</span>
		<a href = "/user/sign-out" class="ml-3">로그아웃</a>
	</div>
</div>
