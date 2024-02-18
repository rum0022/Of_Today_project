<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <h1 class="text-center mt-5">To Do List</h1>
<div class="d-flex justify-content-center">  
	<div class="contents-box">
	    <input type="text" id="todoDay" placeholder="ex) 2023-01-01" class="w-30 mb-2  border border-">
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
					<span class="font-weight-bold">2024년 2월 25일</span>
				</div>	
				<%-- 글 --%>
				<div class="m-3 d-flex align-items-center">
					<input type="checkbox" name="open" value="true"> 
					<div class="ml-2 col-10">내 생일 파티</div>
					<button id="deleteBtn" class="btn btn-danger" type="button">삭제</button>
				</div>			
			 </div>  
	</div>
</div>		