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
				<c:when test="${requestScope.deleteMsg == 'success'}">
					<h2>탈퇴 성공<h2>
					<a href="<c:url value='../main/index.jsp'/>">메인으로 가기</a>
				</c:when>
				<c:otherwise>
					<h2>탈퇴 실패</h2>
					<a href="<c:url value='../main/index.jsp'/>">메인으로 가기</a>
					<a href="<c:url value='../myshop/modify.jsp'/>">다시 탈퇴하러 가기</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>