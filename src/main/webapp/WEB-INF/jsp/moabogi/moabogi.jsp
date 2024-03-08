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
				<c:if test="${pageView.filledReaction eq false}">
					<a href="#" class="reaction-btn" data-diary-id="${pageView.diary.id}">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
					</a>
				</c:if>	
				
				<c:if test="${pageView.filledReaction eq true}">
					<a href="#" class="reaction-btn" data-diary-id="${pageView.diary.id}">
						<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18" height="18" alt="filled heart">
					</a>
				</c:if>	
					공감 ${pageView.reactionCount}개
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
							<c:if test="${userId eq commentView.comment.userId}">
								<a href="#" class="comment-del-btn" data-comment-id="${commentView.comment.id}">
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
								</a>
							</c:if>	
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

<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%-- modal-sm: 작은 모달창
		modal-dialog-centered: 수직 기준 가운데 위치 --%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
    		<div class="py-3 border-bottom">
    			<a href="#" id="diaryDelete">삭제하기</a>
    		</div>
    		<div class="py-3">
    			<a href="#" data-dismiss="modal">취소하기</a>
    		</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
	
		
		// 공개 댓글쓰기
		$(".comment-btn").on("click", function() {
			// alert("게시");
			
			 let userId = $(this).data("user-id");
			//alert(userId);
			let diaryId = $(this).data("diary-id");
			//alert(diaryId);
			// 댓글내용가져오기 (div로 같이 묶여있고 댓글 게시 버튼을 눌렀을때 이전의 input태그 내용 가져온다는것)
			let content = $(this).prev().val().trim();
			// alert(content);
			
			$.ajax({
				type:"POST"
				, url:"/comment/create"
				, data:{"diaryId":diaryId, "content":content}
				, success:function(data) {
					if (data.code == 200) {
						alert("댓글이 저장되었습니다.");
						location.reload();
					} else if(data.code == 500) {
						alert("data.error_message");
						location.href = "/user/sigh-in-view";
					}
				}
				, error: function(request, status, error) {
					alert("댓글 쓰기 실패했습니다."); 
				}
			});
		});
		
		// 댓글 삭제
		$(".comment-del-btn").on("click", function(e) {
			// alert("댓글삭제");
			e.preventDefault();
			let commentId = $(this).data("comment-id");
			// alert(commentId);
			// ajax
			 $.ajax({ // delete는 포스트방식으로
				type:"DELETE"
				, url:"/comment/delete"
				, data:{"commentId":commentId}
			 
			 	, success:function(data) {
			 		if (data.code == 200) {
			 			// 성공
			 			location.reload(true); 
			 		} else {
			 			// 실패
			 			alert(data.error_message);
			 		}
			 	}
			 	, error:function(request, status, error) {
			 		alert("삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
			 	}
			 });
		});
		
		// 공감클릭이벤트(토글)
		$(".reaction-btn").on("click", function(e) {
			e.preventDefault();
			 //alert("좋아요");
			let diaryId = $(this).data("diary-id"); 
			
			$.ajax({
				url:"/reaction/" + diaryId
				, success:function(data) {
			 		if (data.code == 200) {
			 			// 성공
			 			location.reload(true); 
			 		} else if (data.code == 300) {
			 			// 비로그인
			 			alert(data.error_message);
			 			location.href = "/user/sign-in-view"
			 		}
			 	}
			 	, error:function(request, status, error) {
			 		alert("좋아요를 실패했습니다. 관리자에게 문의해주세요.");
			 	}
			});
		});
		// 모달창띄우기
		$(".more-btn").on("click", function() {
			// alert("더보기");
			let diaryId = $(this).data("diary-id");
			// 1개로 존재하는 모달에 재활용을 위해 data-post-id를 심는다. ...누를때마다 
			$("#modal").data("diary-id", diaryId) // "post-id"에 postId를 세팅할것이다.
			});
		
		// 모달안에있는 삭제하기 클릭 
		$("#modal #diaryDelete").on("click", function(e) {
			e.preventDefault();
			
			let diaryId = $("#modal").data("diary-id");
			//alert(diaryId);
			$.ajax({
				type:"DELETE"
				, url:"/diary/delete"
				, data:{"diaryId":diaryId}
				, success:function(data) {
					if (data.code == 200) {
						location.reload();
					} else {
						alert(data.error_message);
					}
				}
				, error:function(request, status, error) {
			 		alert("삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
			 	}
			});
		});
		
	});
</script>