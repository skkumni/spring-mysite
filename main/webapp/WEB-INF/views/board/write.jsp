<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<main>
	<h3>새 글 작성</h3>
	<form id="writeForm" method="POST" enctype="multipart/form-data">
		<div>
			작성자 : 
			<input type="text" name="writer" value="${login.nickname }" readonly>
		</div>
		<div>
			제목 : <input type="text" name="title" placeholder="제목" required autofocus>
		</div>
		<div>
			<textarea name="content" placeholder="바르고 고운 말을 사용합시다"></textarea>
		</div>
		<div>
			<input type="file" name="uploadFile">
		</div>
		<div>
			<label><input type="radio" name="allowReply" value="y" checked>댓글 허용</label>
			<label><input type="radio" name="allowReply" value="n">댓글 금지</label>
		</div>
		<div>
			<input type="submit" value="작성완료">
		</div>
	</form>
</main>

</body>
</html>