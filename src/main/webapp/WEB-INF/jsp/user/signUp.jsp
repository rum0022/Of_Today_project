<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h2 class="m-4 font-weight-bold">가입정보입력</h2>
		<form id="signUpForm" method="post" action="/user/sign-up">
			<div class="sign-up-subject">아이디</div>
			<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
			<div class="d-flex my-2">
				<input type="text" id="loginId" name="loginId" class="form-control col-8" placeholder="ID를 입력해주세요">
				<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button>
			</div>
			
			<%-- 아이디 체크 결과 --%>
			<div class="ml-3 mb-3">
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
			</div>
			
			<span class="sign-up-subject">비밀번호</span>
			<div class="my-2">
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">비밀번호 확인</span>
			<div class="my-2">
				<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">이름</span>
			<div class="my-2">
				<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요">
			</div>

			<span class="sign-up-subject">이메일</span>
			<div class="my-2">
				<input type="text" id="email" name="email" class="form-control" placeholder="이메일을 입력하세요">
			</div>
			
			<div class="d-flex justify-content-center m-3">
				<button type="submit" id="signUpBtn" class="btn btn-info">가입하기</button>
				<%--submit을 button으로 바꾸면 form깨짐 --%>
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		//2. 아이디 중복확인
		$('#loginIdCheckBtn').on('click', function() {
			//alert("중복확인");
			
			// 경고 문구 초기화
			$('#idCheckLength').addClass("d-none");
			$('#idCheckDuplicated').addClass("d-none");
			$('#idCheckOk').addClass("d-none");
			
			let loginId = $("#loginId").val().trim();
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass("d-none");
				return;
			}
			
			$.ajax({
				url:"/user/is-duplicated-id"
				, data:{"loginId":loginId}
				, success:function(data) {
					if (data.code == 200) {
						if (data.is_duplicated_id) {
							$("#idCheckDuplicated").removeClass("d-none");
						} else {
							$("#idCheckOk").addClass("d-none");
						}
					} else {
						alert(data.error_message);
					}
				}
				, error(request, status, error) {
					alert("중복확인에 실패하였습니다.");
				}
			});
		});
		
		
		//1. 가입하기를 눌렀을 때
		$('#signUpBtn').on('submit', function(e) {
			e.preventDefault();
			//alert("회원가입");
			
			//유효성검사
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if (!loginId) {
				alert("아이디를 입력하세요.");
				return false;
			}
			if (!password || !confirmPassword) {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			if (!name) {
				alert("이름을 입력하세요")
				return false;
			}
			if (!email) {
				alert("이메일을 입력하세요.")
				return false;
			}
			
			
		});
	});
</script>