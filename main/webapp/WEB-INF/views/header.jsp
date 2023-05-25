<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cpath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::: BOARD ::: (day20)</title>
<link type="text/css" rel="stylesheet" href="${cpath }/resources/css/default.css">
<style>
	html {
		scroll-behavior: smooth;
	}
	#boardList {
		margin: 20px auto;
		width: 100%;
		border: 2px solid black;
	}
	#boardList td,
	#boardList th {
		border: 0;
		text-align: center;
	}
	#boardList td:nth-child(2) {
		text-align: left;
	}
	#boardList > thead {
		background-color: black;
		color: white;
	}
	#boardList > tbody > tr:hover {
		background-color: #dadada;
	}
	
	div.boardMenu {
		display: flex;
		justify-content: space-between;
		width: 100%;
	}
	
	/* 작성 폼에 대한 스타일 */
	#writeForm {
		padding: 20px 0;
	}
	#writeForm > div {
		padding: 10px 0;
		position: relative;
		display: flex;
		align-items: center;
	}
	#writeForm > div > textarea {
		width: 100%;
		min-height: 200px;
		padding: 10px;
		font-size: 16px;
		box-sizing: border-box;
		resize: none;
		margin: 20px auto;
	}
	#writeForm > div > input[type="text"] {
		all: unset;
		position: absolute;
		left: 65px;
		padding: 5px;
		border-bottom: 2px solid black;
		width: 300px;
	}
	
	/* 게시글 읽기 스타일 */
	#viewBoard {
		padding: 20px;
		border: 2px solid black;
		margin-bottom: 20px;
	}
	#viewBoard > div > img {
		display: block;
		max-width: 90%;
		margin: 20px auto;
	}
	
	/* 페이징 스타일 */
	div.paging {
		text-align: center;
	}
	div.paging > button {
		opacity: 0.1;
	}
	
	/* 댓글 스타일 */
	div.replyList {
		border: 2px solid black;
		padding: 10px;
		margin-top: 20px;
	}
	div.reply {
		background-color: #eee;
		box-sizing: border-box;
		padding: 20px 10px;
		margin: 5px;
	}
	div.reply > div.flex {
		display: flex;
		justify-content: space-between;
	}
	div.reply > div.flex > div:first-child {
		flex: 1;
		display: flex;
		justify-content: flex-start;
	}
	div.reply > div.flex > div:last-child {
		flex: 1;
		display: flex;
		justify-content: flex-end;
	}
	div.reply pre {
		background-color: #fafafa;
		min-height: 50px;
		padding: 5px;
	}
	
	#replyWrite  textarea {
		width: 500px;
		height: 100px;
		padding: 10px;
		font-size: 16px;
		box-sizing: border-box;
		resize: none;
	}
	
</style>
</head>
<body>

<header>
	<h1><a href="${cpath }">ITBANK</a></h1>
	<div id="loginInfo">
		<c:if test="${not empty login }">
			${login.nickname } ( ${login.userid } )
		</c:if>
	</div>
</header>

<nav>
	<ul>
		<%--항상 보이는 메뉴 --%>		
		<li><a href="${cpath }/board/list">게시판</a></li>
		
		<%--로그인 했을 때만 보이는 메뉴 --%>
		<c:if test="${not empty login }">
			<li><a href="${cpath }/member/mypage">마이페이지</a></li>
			<li><a href="${cpath }/member/logout">로그아웃</a></li>
		
		</c:if>		
		<%--로그아웃 했을 때만 보이는 메뉴 --%>
		<c:if test="${empty login }">
			<li><a href="${cpath }/member/login">로그인</a></li>
		
		</c:if>
		
	</ul>
</nav>




