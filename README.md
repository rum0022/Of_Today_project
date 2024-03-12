# Of Today (일기, 메모, 할 일) 프로젝트
Spring Boot + JSP을 이용한 일정관리 사이트 
## 💻프로젝트 소개
하루의 시작 및 마무리를 위한 일정관리 웹프로젝트입니다.
## ⏰개발기간
2024.01.29 ~ 2024.03.06
## ⚙개발환경
- java 17
- 백엔드 : SpringBoot 3.1.2, gradle, jpa(Spring Data JPA)
- 프론트엔드 : CSS, JavaScript, JSP, bootStrap, JQuery
- DB : MySql, mybatis
- 형상관리 : github
## 📌주요기능  
로그인
- DB값 검증
- 로그인시 session(세션) 생성

회원가입
- 아이디 중복확인

메인페이지
- Fullcalendar 연동 (API)

메모장
- 글 작성, 읽기, 수정, 삭제 - CRUD

일기장
- 글작성(비밀글가능, 공개글은 모아보기에서 SNS로 커뮤니티활용), 삭제
- 공개글 - 댓글작성, 삭제, 공감, 공감수 가능

ToDo
- Fullcalendar와 내용 연동

사진
- 메모장, 일기장에 Insert된 이미지파일을 한 곳에서 확인가능

