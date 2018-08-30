<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
		<c:choose>
		<c:if test="${cmd1=='runway' }">
			<h1>R U N W A Y</h1>
		</c:if>
		<c:if test="${cmd1=='women' }">
			<h1>W O M E N</h1>
		</c:if>
		<c:if test="${cmd1=='men' }">
			<h1>M E N</h1>
		</c:if>
		</c:choose>
		
		<c:choose>
			<c:when test="${list==null }">
				상품이 준비중입니다.
			</c:when>
			<c:otherwise>
				<c:forEach var="vo" items="${list }">
					${vo.lookCode }<br>
					<img src="/semi_project/itemFile/${vo.lookFront }">
					<img src="/semi_project/itemFile/${vo.lookBack }">				
					
				</c:forEach>
			</c:otherwise>	
		</c:choose>
		
		
		
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>