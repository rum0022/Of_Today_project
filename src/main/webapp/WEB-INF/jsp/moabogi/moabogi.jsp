<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center mt-5">
	<div class="contents-box">
		<h1 class="text-center">Moabogi</h1>
		<div class="diary-box my-5">
			<c:forEach items="${diaryPageViewList}" var="pageView">
			
			 <%-- 모두공개일때 --%>
			 <c:if test="${pageView.diary.openYn eq false}">
				<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<div class="d-flex">
						<span class="font-weight-bold ml-2">${pageView.user.loginId}</span>
					</div>
					<%-- 날짜 --%>
				    <span class="font-weight-bold"><fmt:formatDate value="${pageView.diary.decidedDay}" pattern="yyyy년 MM월 dd일" /></span>
					<%--(더보기 ... 버튼)--%>
					<c:if test="${userId eq pageView.diary.userId}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-diary-id="${pageView.diary.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
					<c:if test="${userId ne pageView.diary.userId}">
					<div></div>
					</c:if>
				</div>	
				
				<%-- 이미지 --%>
				<c:if test="${pageView.diary.imagePath eq false}">
					<div class="card-img d-flex justify-content-center">
						<img src="${pageView.diary.imagePath}" class="w-50" alt="본문 이미지">
					</div>
				</c:if>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span>${pageView.diary.content}</span>
				</div>
				
				<%-- 공감 --%>
				<div class="card-like m-3">
				<c:if test="${card.filledLike eq false}">
					<a href="#" class="like-btn" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
					</a>
				</c:if>	
				
				<c:if test="${card.filledLike eq true}">
					<a href="#" class="like-btn" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18" height="18" alt="filled heart">
					</a>
				</c:if>	
					공감 ${card.likeCount}개
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<c:forEach items="${pageView.commentList}" var="commentView">
						<div class="card-comment m-1">
							<span class="font-weight-bold">${commentView.user.loginId}</span>
							<span>${commentView.comment.content}</span>
								
							<%-- 댓글 삭제 버튼 --%>
							<a href="#" class="comment-del-btn">
								<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
							</a>
						</div>
					</c:forEach>	
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-user-id="${pageView.user.id}" data-diary-id="${pageView.diary.id}">게시</button> 
					</div>
				</div> <%--// 댓글 목록 끝 --%>				
				</div>  <%--// 공개끝 --%>
			 	</c:if>
			</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>