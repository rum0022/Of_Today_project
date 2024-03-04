<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <h1 class="text-center mt-5">To Do List</h1>

<div class="d-flex justify-content-center">  
	
	<div class="contents-box">
	    <input type="text" id="todoDay" placeholder="ex) 2023-01-01" class="w-30 mb-2"> 
	    
	  <%-- 게시 --%>
	  
	    <div class="d-flex align-items-center">
	    	<div class="input-group">
        		<input type="text" id="content" class="form-control">
        		
        		<div class="input-group-append">
        			<button id="saveBtn" class="btn btn-light" type="button">게시</button>
				</div>
			</div>
		</div> <%-- 글쓰기영역 끝 --%>
		

		<div class="diary-box my-5"> 
			 	<%-- 날짜 --%>
			 	
				<c:forEach items="${todoCardViewList}" var="todoCard">
					<c:if test="${!empty todoCard.contentList}">
						<div class="mt-3 d-flex justify-content-center">
							<span class="font-weight-bold">${todoCard.day.todoDay}</span>
						</div> 
					</c:if>
				<%-- 글 --%>
					<c:forEach items="${todoCard.contentList}" var="contentView">
						<c:if test="${contentView.dayId eq todoCard.day.id}">
							<c:if test="${contentView.checkboxYn eq true}">
							<div class="m-3 d-flex align-items-center">
								<input type="checkbox" id="checkboxYn" name="checkboxYn" data-content-id="${contentView.id}" checked> 
								<div id="checkBoxY" class="ml-2 col-10"><mark>${contentView.content}</mark></div>
								<button id="deleteBtn" class="content-del-btn btn btn-danger" type="button" data-content-id="${contentView.id}">삭제</button>
							</div>
							</c:if>
							
							<c:if test="${contentView.checkboxYn eq false}">
							<div class="m-3 d-flex align-items-center">
								<input type="checkbox" id="checkboxYn" name="checkboxYn" data-content-id="${contentView.id}"> 
								<div id="checkBoxF" class="ml-2 col-10">${contentView.content}</div>
								<button id="deleteBtn" class="content-del-btn btn btn-danger" type="button" data-content-id="${contentView.id}">삭제</button>
							</div>
							</c:if>
						</c:if>
						</c:forEach>
					</c:forEach>
			 </div>  
		</div>	
	</div>  
	
<script>
	$(document).ready(function() {
		
		// 날짜입력
		$('#todoDay').datepicker({
			dateFormat:"yy-mm-dd"   
            , changeMonth:true     
            , changeYear:true      
            , showAnim:"clip" 
		});
			
		$("#saveBtn").on("click", function() {
				
			let todoDay = $("#todoDay").val();
			let content = $("#content").val().trim();
				// alert(content);
			let checkboxYn = $("input:checkbox[id='checkboxYn_id']").is(":checked"); // 여기서는 false안해줘도되나..
				// alert(checkboxYn);
				
				if (!content) {
					alert("할 일을 입력해주세요");
					return;
				}
				
				$.ajax({
					type:"POST"
					, url:"/todo/create" // 날짜만
					, data:{"todoDay":todoDay, "content":content, "checkboxYn":checkboxYn}
					, success:function(data) {
						if (data.code == 200) {
							alert("글이 저장되었습니다.");
							location.reload();
						} else if(data.code == 500) {
							alert("data.error_message");
							location.href = "/user/sigh-in-view";
						}
					}
					, error: function(request, status, error) {
						alert("글쓰기 실패했습니다."); 
					}
				});
			});
		
		// 체크박스 클릭하면 완료(true)로 변환
		$("input[type='checkbox']").on('change', function() {
			
			// alert("체크");
			let contentId = $(this).data("content-id");
			// alert(contentId);
			let checkboxYn = $("input:checkbox[id='checkboxYn']").is(":checked");
			// alert(checkboxYn);
			
			$.ajax({
				type:"PUT"
					, url:"/todo/update" 
					, data:{"contentId":contentId, "checkboxYn":checkboxYn}
					, success:function(data) {
						if (data.code == 200) {
							alert("일정체크");
							location.reload();
						} else if(data.code == 500) {
							alert("data.error_message");
							location.href = "/user/sigh-in-view";
						}
					}
					, error: function(request, status, error) {
						alert("일정확인을 실패했습니다."); 
					}
			});
		});
		
		// 삭제하기
		$(".content-del-btn").on("click", function(e) {
			//alert("삭제");
			e.preventDefault();
		
			let contentId = $(this).data("content-id");
			// alert(contentId);
		
			// ajax
			 $.ajax({ // delete는 포스트방식으로
				type:"DELETE"
				, url:"/todo/delete"
				, data:{"contentId":contentId}
			 
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
	});
</script>