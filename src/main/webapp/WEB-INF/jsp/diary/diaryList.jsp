<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center mt-5">
	
	<div class="contents-box">
		<h1 class="text-center">Diary</h1>
		<div class="write-box border rounded m-3">
            <input type="text" id="decidedDay" placeholder="날짜를 입력해주세요" class="w-100">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
				<%--file 태그를 숨겨두고 이미지를 클릭하면 file태그를 클릭한 것과 같은 효과 --%>
				<input type="file" id="file" accept=".jpg, .jpeg, .gif, .png" class="d-none">
				
				<%-- 공개 비공개 --%>	
					<div class="form-check form-check-inline ml-2">
    					<input class="form-check-input" type="checkbox" name="openYn" id="openYn">
    					<label class="form-check-label">비밀글 설정</label>
					</div>
				
				<%--이미지에 마우스를 올리면 마우스 커서가 변하도록 적용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
				
				<%-- 업로드 된 임시 이미지 파일 이름 나타내는 곳 --%>	
					<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div> <%--// 글쓰기 영역 끝 --%>
		
		
		<%-- 일기 영역 --%>
		<div class="diary-box my-5">
			<c:forEach items="${diaryPageViewList}" var="diaryPageView">
			<%-- 비공개일때 --%>
			<c:if test="${diaryPageView.diary.openYn}">
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<div class="d-flex">
					<img src="/static/img/close.jpg" width=25>
					
					<span class="font-weight-bold ml-2">${diaryPageView.user.loginId}</span>
					</div>
					<%-- 날짜 --%>
				    <span class="font-weight-bold">${diaryPageView.diary.decidedDay}</span>
					<%--(더보기 ... 버튼)--%>
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-diary-id="${diary.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>	
				
				<%-- 이미지 --%>
				<c:if test="${diaryPageView.diary.imagePath eq false}">
				<div class="card-img d-flex justify-content-center">
					<img src="${diaryPageView.diary.imagePath}" class="w-50" alt="본문 이미지">
				</div>
				</c:if>
				<%-- 글 --%>
				<div class="card-post m-3">
					<span>${diaryPageView.diary.content}</span>
				</div>	
			 </div>  <%--// 카드1 끝 --%>
			 </c:if>	
			 
			 <%-- 모두공개일때 --%>
			 <c:if test="${diaryPageView.diary.openYn eq false}">
				<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<div class="d-flex">
						<span class="font-weight-bold ml-2">${diaryPageView.user.loginId}</span>
					</div>
					<%-- 날짜 --%>
				    <span class="font-weight-bold">${diaryPageView.diary.decidedDay}</span>
					<%--(더보기 ... 버튼)--%>
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-diary-id="${diaryPageView.diary.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>	
				
				<%-- 이미지 --%>
				<c:if test="${diaryPageView.diary.imagePath eq false}">
					<div class="card-img d-flex justify-content-center">
						<img src="${diaryPageView.diary.imagePath}" class="w-50" alt="본문 이미지">
					</div>
				</c:if>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span>${diaryPageView.diary.content}</span>
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
					<c:forEach items="${diaryPageView.commentList}" var="comment">
						<div class="card-comment m-1">
							<span class="font-weight-bold">${diaryPageView.user.loginId}</span>
							<span>${diaryPageView.commentList.content}</span>
								
								<%-- 댓글 삭제 버튼 --%>
								
									<a href="#" class="comment-del-btn">
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
									</a>
								
							</div>
						</c:forEach>	
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-user-id="${diaryPageView.user.userId}" data-diary-id="${diaryPageView.diary.id}">게시</button> 
					</div>
				</div> <%--// 댓글 목록 끝 --%>				
				</div>  <%--// 공개끝 --%>
			 	</c:if>
			</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>

<script>
	$(document).ready(function() {
		
		// 날짜입력
		$('#decidedDay').datepicker({
			dateFormat:"yy-mm-dd"   // 2023-02-09
            , changeMonth:true     // 월을 선택(셀렉트)
            , changeYear:true      // 년을 선택(셀렉트)
            , showAnim:"clip" 
		});
		
		// 파일 이미지 클릭
		$("#fileUploadBtn").on("click", function(e) {
			e.preventDefault();
			$("#file").click();
		});
		
		// 사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드 된 파일명 노출
		$("#file").on("change", function(e) {
			// alert("이미지선택");
			let file = e.target.files[0];
			if (file == null) {
				$("#file").val("");
				$("#fileName").text("");
				return;
			}
			
			let fileName = e.target.files[0].name;
			console.log(fileName);
			
			// 확장자 유효성 체크
			let ext = fileName.split(".").pop().toLowerCase();
			// alert(ext);
			
			if (ext != "jpg" && ext != "jpeg" && ext != "gif" && ext != "png") {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$("#file").val(""); // 파일태그 파일 제거 (보이지 않지만 업로드 될 수 있으므로 주의)
				$("#fileName").text(""); // 보여지는 파일명 비우기
				return;
			}
			
			// 유효성 통과한 이미지의 경우 파일명 노출
			$("#fileName").text(fileName);
		});
		
		// 글쓰기
		$("#writeBtn").on("click", function() {
			// alert("게시");
			
			let decidedDay = $("#decidedDay").val();
			let content = $("#writeTextArea").val();
			let openYn = $("input:checkbox[id='openYn']").is(":checked");
			let fileName = $("#file").val();
			
			if (!decidedDay) {
				alert("날짜를 입력해주세요");
				return;
			}
			if (!content) {
				alert("내용을 입력해주세요");
				return;
			}
			
			// 파일이 업로드 된경우에만 확장자 체크 검사하고, 업로드 안됐으면 그냥 저장
			if (fileName) {
				// alert("파일이 있다.");
				//C:\fakepath\girl-8435329_640.png
				// 확장자만 뽑은 후 소문자로 변경해서 검사한다. 
				let extension = fileName.split(".").pop().toLowerCase();
				// alert(extension);
				
				if ($.inArray(extension, ['jpg', 'png', 'gif', 'jpeg']) == -1) { //확장자가 이미지가 아닐경우
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$("#file").val(""); // 파일명을 비운다.
					return;
				}
			}
			// 폼태그 구성하고 이미지
			let formData = new FormData();
			formData.append("decidedDay", decidedDay);
			formData.append("content", content);
			formData.append("openYn", openYn);
			formData.append("file", $("#file")[0].files[0]);
			
			$.ajax({
				type:"POST"
				, url:"/diary/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				, success:function(data) {
					if (data.code == 200) {
						// 글목록 화면으로 이동
						alert("글이 저장되었습니다.");
						location.reload();
					} else if (data.code == 500) {// 비로그인 일 때
						location.href = "/user/sign-in-view";
					} else {
						alert("data.error_message");
					}
				}
				, error: function(e) {
					alert("글을 저장하는데 실패 했습니다.");
				}
			});
		});
		
		// 공개 댓글쓰기
		$(".comment-btn").on("click", function() {
			// alert("게시");
			
			let userId = $(this).data("user-id");
			// alert(userId);
			let diaryId = $(this).data("diary-id");
			// alert(diaryId);
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
	});	
</script>