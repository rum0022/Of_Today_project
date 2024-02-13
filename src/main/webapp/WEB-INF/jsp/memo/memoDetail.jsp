<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<div class="d-flex justify-content-center">
	<div class="w-50 mt-5">
		<div>
			<input type="text" id="subject" class="form-control" value="${memo.subject}">
		</div>
		<div class="d-flex justify-content-center">
			<c:if test="${not empty memo.imagePath}">
			<div class="d-flex align-items-center mr-5">
				<img src="${memo.imagePath}" alt="업로드 된 이미지" width="200">
			</div>
			</c:if>		
			<div>
				<textarea id="content" class="form-control mt-3" rows="10" cols="50">${memo.content}</textarea>
			</div>
		</div>
		<div class="d-flex justify-content-end my-3">
			<input type="file" id="file" accept=".jpg, .png, .gif, .jpeg">
		</div>
		
		<div class="d-flex justify-content-between">
			<button type="button" id="deleteBtn" class="btn btn-info">삭제</button>
			<div>
				<a href="/memo/memo-list-view" class="btn btn-dark">목록</a>
				<button type="button" id="updateBtn" class="btn btn-secondary" data-memo-id="${memo.id}">수정</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#updateBtn").on("click", function() {
			// alert("수정");
			// 유효성검사
			let memoId = $(this).data("memo-id");
			let subject = $("#subject").val();
			let content = $("#content").val();
			let fileName = $("#file").val();
			// alert(memoId);
			if (!subject) {
				alert("제목을 입력하세요.");
				return;
			}
			
			if (!content) {
				alert("내용을 입력하세요.");
				return;
			}
			
			if (fileName) {
				let extension = fileName.split(".").pop().toLowerCase();
				if ($.inArray(extension, ['jpg', 'png', 'gif', 'jpeg']) == -1) { //확장자가 이미지가 아닐경우
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$("#file").val(""); // 파일명을 비운다.
					return;
				}
			}
			
			// 이미지를 업로드 할 때는 반드시 form태그로 구성한다. 
			let formData = new FormData();
			formData.append("memoId", memoId);
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]); //파일을 가져오는 문법
			
			// ajax
			$.ajax({
				type:"PUT"
				, url:"/memo/update"
				, data:formData  
				, enctype:"multipart/form-data" 
				, processData:false 
				, contentType:false 
				, success:function(data) {
					if (data.code == 200) {
						alert("메모가 수정되었습니다.");
						location.reload();
					} else {//로직실패
						alert(data.error_message);
					}
				  }
				, error:function(request, status, error) {
					alert("글을 수정하는데 실패했습니다.");
				  }
			});
		});
	});
</script>