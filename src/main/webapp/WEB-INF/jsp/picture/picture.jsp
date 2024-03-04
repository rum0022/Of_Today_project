<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="picture-parent-box d-flex flex-wrap">
	<c:forEach items="${pictureList}" var="picture">
		<article class="picture-box">
		   <c:forEach items="${picture.diaryList}" var="diaryView">
		   		<c:if test="${diaryView.imagePath ne null}">
		     		<img src="${diaryView.imagePath}" alt="본문 이미지">
		       	</c:if>
		   </c:forEach>
		</article>
		<article class="picture-box">
		   <c:forEach items="${picture.memoList}" var="memoView">
		   		<c:if test="${memoView.imagePath ne null}">
		       		<img src="${memoView.imagePath}" alt="본문 이미지">
		      	</c:if>
		   </c:forEach>
		</article>
	</c:forEach>
 </div>
   		
   		
		