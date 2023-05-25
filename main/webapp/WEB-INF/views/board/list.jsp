<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<main>
	
	<table id="boardList">
		<%--컬럼 이름 --%>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성날짜</th>
			</tr>
		</thead>
		
		<%--내용 --%>
		<tbody>
			<c:if test="${empty list }">
			<tr>
				<td colspan="5">
					<h3>검색 결과를 찾을 수 없습니다</h3>
				</td>
			</tr>
			</c:if>
			<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.idx }</td>
				<td>
					<a href="${cpath }/board/view/${dto.idx}">${dto.title }</a>
					<c:if test="${not empty dto.fileName }">
					🎈
					</c:if>
				</td>
				<td>${dto.writer }</td>
				<td>${dto.viewCount }</td>
				<td>${dto.writeDate }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="paging">
		<c:if test="${paging.prev }">
			<a href="${cpath }/board/list/${paging.begin - 10}?search=${param.search}">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${paging.begin }" end="${paging.end }">
			<a href="${cpath }/board/list/${i}?search=${param.search}">
			${paging.page == i ? '<b>' : '' }
				[${i }]${paging.page == i ? '</b>' : '' }</a>
		</c:forEach>
		
		<c:if test="${paging.next }">
			<a href="${cpath }/board/list/${paging.end + 1}?search=${param.search}">[다음]</a>
		</c:if>
		<br><br>
		<button onclick="alert('${paging}')">페이징 정보 확인</button>
	</div>
	
	<div class="boardMenu">
		<div class="left">
			<form method="GET" action="${cpath }/board/list/1">
			
				<input type="search" name="search" 
					   placeholder="제목, 작성자 혹은 내용"
					   autocomplete="off"
					   value="${param.search }">
				<input type="submit" value="검색">
			</form>
		</div>
		<div class="right">
			<a href="${cpath }/board/write"><button>새 글 작성</button></a>
		</div>
	</div>
	
</main>

</body>
</html>