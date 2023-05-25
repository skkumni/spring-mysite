<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<main>
	<h3>게시글 수정하기</h3>
	<form id="writeForm" method="POST" enctype="multipart/form-data">
		<div>
			작성자 : 
			<input type="text" name="writer" value="${login.nickname }" readonly>
		</div>
		<div>
			제목 : <input type="text" name="title" placeholder="제목" value="${dto.title }" required autofocus>
		</div>
		<div>
			<textarea name="content" placeholder="바르고 고운 말을 사용합시다">${dto.content }</textarea>
		</div>
		
		<c:if test="${not empty dto.fileName }">
		<div>
			<a href="${cpath }/upload/${dto.fileName}" target="_blank"><b>[첨부파일]</b></a>
			&nbsp;&nbsp;
			<sub>새로운 첨부파일을 지정하면 기존 파일을 대체합니다</sub>			
		</div>
		</c:if>
		
		<div>
			<input type="file" name="uploadFile">
		</div>
		<div>
			<label><input type="radio" name="allowReply" value="y" ${dto.allowReply == 'y' ? 'checked' : '' }>댓글 허용</label>
			<label><input type="radio" name="allowReply" value="n" ${dto.allowReply == 'n' ? 'checked' : '' }>댓글 금지</label>
		</div>
		<div>
			<input type="submit" value="작성완료">
		</div>
	</form>
</main>

</body>
</html>