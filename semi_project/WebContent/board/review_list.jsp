<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table border="1" width="500">
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>글제목</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.num }</td>
		<td>${vo.writer }</td>
		<td>
		<a href="detail.do?num=${vo.num }">${vo.title }</a>
		</td>
		<td>${vo.hit }</td>
	</tr>
	</c:forEach>
</table>
<div>
	<c:choose>
		<c:when test="${pageNum>10}">
			<a href="list.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${pageNum==i }">
			<a href="list.do?pageNum=${i }"><span style="color:red" >[${i }]</span></a>
		</c:when>
		<c:otherwise>
			<a href="list.do?pageNum=${i }"><span style="color:#555" >[${i }]</span></a>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount}">
			<a href="list.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>