<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="../inc/header.jsp"/>
</head>
<body>
	<jsp:include page="../inc/gnb.jsp"/>
<h2>NOTICE BOARD</h2>
<!-- 관리자가 들어왔을때 -->









<!-- 일반인이 들어왔을때 -->
<table border="1" width="800">
	<tr><th>글번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.num }</td>
			<td><a href="jh/notice.do?num=${vo.num }&cmd='detail'">${vo.title }</a></td>
			<td>관리자</td>
			<td>${vo.hit }</td>		
		</tr>
	</c:forEach>
</table>
</body>
</html>