<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style>
		#joinConWrap{margin: auto; width: 60%; padding: 20px; border: solid 1px #ccc; background: #fff; text-align: center;}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<div id="joinConWrap">
				<c:choose>
					<c:when test="${requestScope.code == 'success'}">
						<h2>회원가입 성공</h2>
						<a href="<c:url value='/mh/users.do?cmd=loginform'/>">로그인하기</a>
					</c:when>
					<c:otherwise>
						<h2>회원가입 실패</h2>
						<a href="<c:url value='../users/join.jsp'/>">다시 회원 가입</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>