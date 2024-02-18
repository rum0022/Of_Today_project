<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<div class="d-flex justify-content-center mt-5">
	<h1 class="mr-2">Memo</h1>
	<div class="w-50">
		
		<table class="table">
			<thead>
				<tr>
					<th>NO.</th>
					<th>제목</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${memoList}" var="memo">
				<tr>
					<td>${memo.id}</td>
					<td><a href="/memo/memo-detail-view?memoId=${memo.id}">${memo.subject}</a></td>
					<td><fmt:formatDate value="${memo.createdAt}" pattern="yyyy년 M월 d일"/></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
		<%--글쓰기버튼 --%>
		<div class="d-flex justify-content-end">
			<a href="/memo/memo-create-view" class="btn btn-info">메모쓰기</a>
		</div>
	</div>
</div>