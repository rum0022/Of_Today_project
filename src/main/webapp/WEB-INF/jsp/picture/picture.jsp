<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex">
	<c:forEach items="${pictureList}" var="picture">
	<div>${picture.diary.imagePath}</div>
	</c:forEach>
</div>