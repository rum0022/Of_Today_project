<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex">
	<c:forEach items="${pictureList}" var="picture">
		<c:forEach items="${picture.diaryList}" var="diaryView">
			<img src="${diaryView.imagePath}" class="w-30" alt="본문 이미지">
		</c:forEach>
		<c:forEach items="${picture.memoList}" var="memoView">
			<img src="${memoView.imagePath}" class="w-30" alt="본문 이미지">
		</c:forEach>
	</c:forEach>
</div>