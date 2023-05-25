<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<main>
	<h3>회원 가입</h3>
		
	<textarea readonly
			  style="resize: none; 
				     width: 700px; 
				     height: 300px; 
				     padding: 10px;">${agreement }</textarea>
	<p>
		<label>
			<input type="checkbox" name="agreementCheck">
			이용약관에 동의합니다
		</label>
	</p>
	
	<form method="POST">
		<p>
			<input type="text" name="userid" placeholder="아이디"
				  autocomplete="off" required autofocus>
			<span></span>
		</p>
		<p><input type="password" name="userpw" placeholder="비밀번호" required></p>
		<p><input type="date" name="birthDate" required></p>
		<p><input type="text" name="nickname" placeholder="닉네임" required></p>
		<p><input type="email" name="email" placeholder="foo@bar.com" required></p>
		<p>
			<label><input type="radio" name="gender" value="남성" required>남성</label>
			<label><input type="radio" name="gender" value="여성" required>여성</label>
		</p>
		<p><input type="submit" value="가입신청"></p>
	</form>
	
</main>

<script>
	// 비동기 통신을 사용하여 회원 데이터베이스에 이미 사용중인 ID인지 체크하는 코드
	// Asynchronous Javascript And XML (AJAX)
	// ResponseBody, RestController, RestControllerAdvice
	const userid = document.querySelector('input[name="userid"]')
	
	function dupIdCheckHandler(event) {
		const value = document.querySelector('input[name="userid"]').value
		
		const url = '${cpath}/ajax/dupIdCheck?userid=' + value
		
		fetch(url)					// 지정한 주소로 비동기 방식 요청을 보낸다
		.then(resp => resp.text())	// 응답을 받으면 응답을 문자열 형식으로 변환한다
		.then(text => {				// 변환된 문자열을 이용하여
			// 팝업창에 내용을 출력한다
			
			if(text == 1) {
				userid.nextElementSibling.innerText = '이미 사용중인 ID입니다.'
				userid.nextElementSibling.style.color = 'red'
				userid.select()
			}
			else {
				userid.nextElementSibling.innerText = '사용 가능한 ID입니다'
				userid.nextElementSibling.style.color = 'blue'
			}
		})
	}
	
	// blur : 흐릿해지다. 포커스를 잃으면 실행되는 함수(이벤트)
	userid.onblur = dupIdCheckHandler
</script>

<script>
	const form = document.querySelector('form')
	const chk = document.querySelector('input[name="agreementCheck"]')
	
	form.style.display = 'none'
	
	chk.onclick = function(event) {
		form.style.display = event.target.checked ? 'block' : 'none'
	}
	
	form.onsubmit = function(event) {
		event.preventDefault()
		chk.checked = false	
		event.target.submit()
	}
</script>

</body>
</html>