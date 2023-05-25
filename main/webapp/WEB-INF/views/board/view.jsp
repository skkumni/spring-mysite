<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<main>
	<h3>게시글 읽기</h3>
	
	<div id="viewBoard">
		<div>글번호 : ${dto.idx }</div>
		<div>작성자 : ${dto.writer }</div>
		<div>제목 : ${dto.title }</div>
		<div>작성날짜 : ${dto.writeDate }</div>
		<div>조회수 : ${dto.viewCount }</div>
		<div>
			<c:if test="${not empty dto.fileName }">
			<img src="${cpath }/upload/${dto.fileName}">
			</c:if>
			<pre>${dto.content }</pre>
		</div>
		<div>
		
		</div>
	</div>
	
	<div class="boardMenu">
		<div class="left">
			<button onclick="history.go(-1)">뒤로가기</button>
		</div>
		<div class="right">
			<c:if test="${dto.writer == login.nickname }">
				<a href="${cpath }/board/modify/${dto.idx}"><button>수정</button></a>
				<button id="deleteBtn" idx="${dto.idx }">삭제</button>
			</c:if>
		</div>
	</div>
	
	<div class="replyList">
		<c:set var="replyPlaceHolder" 
			   value="${empty login ? '로그인해야 작성가능합니다' : '댓글내용을 작성하세요' }" />
	
		<form id="replyWrite" method="POST" action="${cpath }/board/replyWrite/${dto.idx}">
			
			<input type="hidden" name="parent_idx" value="0">
			<input type="hidden" name="reply_depth" value="0">
			
			<textarea name="content" 
					  placeholder="${replyPlaceHolder }"
					  ${empty login ? 'readonly' : '' }></textarea>
			<input type="submit" value="댓글작성">
		</form>
	
		<c:forEach var="reply" items="${replyList }">
		<c:set var="absDepth" value="${reply.reply_depth }" />
		<c:if test="${absDepth < 0 }">
			<c:set var="absDepth" value="${absDepth * -1}" />	
		</c:if> 
		<div class="reply" style="margin-left: ${absDepth * 40}px">
			<div class="flex">
				<div>
					<div style="margin-right: 20px;"><b>${reply.writer }</b></div>
					<div>
						<fmt:formatDate value="${reply.writeDate }"
										pattern="yyyy-MM-dd a hh:mm" />
					</div>
				</div>
				<div>
					<div><button>수정</button></div>
					<div><button class="replyDeleteBtn"
								 idx="${reply.idx }">삭제</button></div>
				</div>
			</div>
			<div>
				<pre>${reply.content }</pre>
			</div>
			<div>
				<button class="rreplyWriteBtn" 
						parent_idx="${reply.idx }"
						reply_depth="${reply.reply_depth }">🗨</button>
			</div>
		</div>
		</c:forEach>
	</div>
	
</main>

<script>
	// 댓글 삭제 스크립트
	// 1) 이벤트를 부여할 요소들을 불러온다
	const replyDeleteBtnList = document.querySelectorAll('.replyDeleteBtn')
	
	// 2) 이벤트가 발생하면 실행할 함수를 정의한다
	function replyDeleteHandler(event) {
		const idx = event.target.getAttribute('idx')
		const url = '${cpath}/board/replyDelete/' + idx
		const flag = confirm(idx + '번 댓글을 삭제하시겠습니까?')
		
		if(flag) {
			location.href = url
		}
	}
	
	// 3) 대상 요소(들)에게 클릭하면 실행할 함수를 연결(여러 요소라면 반복문)한다
	replyDeleteBtnList.forEach(function(btn) { btn.onclick = replyDeleteHandler })
</script>

<script>
	// 댓글 관련 스크립트
	const rreplyWriteBtnList = document.querySelectorAll('.rreplyWriteBtn')
	
	function rreplyWriteHandler(event) {
		let parent_idx = event.target.getAttribute('parent_idx')
		let reply_depth = event.target.getAttribute('reply_depth')
		
		console.log(parent_idx, reply_depth)
		
		const form = document.querySelector('form#replyWrite')	// form을 불러온다
		const div = event.target.parentNode		// 클릭한 버튼의 상위 div를 불러온다
		
		form.querySelector('input[name="parent_idx"]').value = parent_idx
		form.querySelector('input[name="reply_depth"]').value = +reply_depth + 1
		
		div.appendChild(form)					// 원래 자리에서 form을 가져와서 버튼 상위 div에 추가한다
		form.querySelector('textarea').focus()	// form 내부 textarea에 포커스 효과를 부여한다
	}
	
	rreplyWriteBtnList.forEach(function(btn) { btn.onclick = rreplyWriteHandler })
</script>

<script>
	const deleteBtn = document.getElementById('deleteBtn')
	
	function deleteHandler() {
		const idx = deleteBtn.getAttribute('idx')
		const url = '${cpath}/board/delete/' + idx
		const flag = confirm(idx + '번 게시글을 삭제하시겠습니까?')
		if(flag) {
			location.href = url
		}
	}
	if(deleteBtn != null) {
		deleteBtn.onclick = deleteHandler
	}
	
	// 로그인 안되어 있을 경우 댓글을 클릭하면 로그인 창으로 이동시키는 이벤트
	const textarea = document.querySelector('div.replyList textarea[name="content"]')
	
	textarea.onclick = function(event) {
		const login = '${login}'
		if(login == '') {
			const flag = confirm('댓글 작성을 위해 로그인이 필요합니다. 이동하시겠습니까?')
			if(flag) {
				// <form id="replyWrite" method="POST" action="...">
				const path = '${cpath}/member/login?url=' + location.href + '#replyWrite'
				location.href = path
			}
		}
	}
	
</script>

</body>
</html>











