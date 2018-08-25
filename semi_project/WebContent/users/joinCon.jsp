<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<c:when test="${requestScope.code == 'success'}">
					<h2>회원가입 성공</h2>
					<a href="<c:url value='/mh/users.do?cmd=loginform'/>">Sign In</a>
				</c:when>
				<c:otherwise>
					<h2>회원가입 실패</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>