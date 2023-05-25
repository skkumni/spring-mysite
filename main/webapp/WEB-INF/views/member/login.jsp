<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<main>
<fieldset>
	<h3>로그인</h3>
	
	<form method="POST">
		<p><input type="text" name="userid" placeholder="아이디" 
				  autocomplete="off" required autofocus ></p>
		<p><input type="password" name="userpw" placeholder="비밀번호" required></p>
		<p><input type="submit" value="로그인"></p>
	</form>
	
	<p>
		<a href="${cpath }/member/join">아직 회원이 아니십니까</a>
		&nbsp;
		<a href="${cpath }/member/resetPassword">비밀번호 재설정</a>
	</p>
</fieldset>
</main>

</body>
</html>