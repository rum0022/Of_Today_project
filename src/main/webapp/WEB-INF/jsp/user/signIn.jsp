<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center mt-5">
	<div class="login-box">
		<h3 class="mb-4 text-center">로그인</h3>
		<form id="loginForm" action="/user/sign-in" method="post">
			<div class="mb-3">
				<input type="text" class="form-control" id="loginId" name="loginId" placeholder="아이디">
			</div>
	
			<div class="mb-3">
				<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
			</div>
			
			<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
			<input type="submit" id="loginBtn" class="btn btn-block btn-warning" value="로그인">
			<a class="btn btn-block btn-dark" href="/user/sign-up-view">회원가입</a>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		$("#loginForm").on('submit', function(e) {
			e.preventDefault();
			
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			
			if (!loginId) {
				alert("아이디를 입력해주세요.");
				return false;
			}
			
			if (!password) {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			let url = $(this).attr("action");
			//console.log(url);
			let params = $(this).serialize();
			//console.log(params);
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 200) {
					location.href = "/main/main-view";
				} else {
					alert(data.error_message);
				}
			});
		});	
	});
</script>