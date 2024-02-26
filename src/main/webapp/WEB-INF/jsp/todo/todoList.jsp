<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
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
				<div class="mt-3 d-flex justify-content-center">
					<span class="font-weight-bold">${day.todoDay}</span>
				</div>	
				<%-- 글 --%>
				<c:forEach items="${contentList}" var="content">
				<div class="m-3 d-flex align-items-center">
					<input type="checkbox" name="checkboxYn" value="true"> 
					<div class="ml-2 col-10">${content.content}</div>
					<button id="deleteBtn" class="btn btn-danger" type="button">삭제</button>
				</div>
				</c:forEach>
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
			let checkboxYn = $("input:checkbox[id='checkboxYn']").is(":checked");
				// alert(checkboxYn);
				
				if (!content) {
					alert("할 일을 입력해주세요")
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
		});
</script>