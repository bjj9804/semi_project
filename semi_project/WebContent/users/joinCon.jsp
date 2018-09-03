<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style>
		#joinConWrap{margin: auto; width: 60%; padding: 20px; border: solid 1px #ccc; background: #fff; text-align: center;}
		#joinConWrap a{padding: 8px; border: solid 1px #333; color: #fff; background: #333}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<div id="joinConWrap">
				<c:choose>
					<c:when test="${requestScope.code == 'success'}">
						<br><br>
						<h2>PABAL에 가입하신것을 축하드립니다!</h2><br>
						<a href="<c:url value='/mh/users.do?cmd=loginform'/>">로그인하기</a><br><br><br>
					</c:when>
					<c:otherwise><br><br>
						<h2>회원가입에 실패하셨습니다</h2><br>
						<a href="<c:url value='../users/join.jsp'/>">다시 회원 가입</a><br><br><br>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>