<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			이잉
			<c:choose>
				<c:when test="${requestScope.code == 'cuccess'}">
					<h2>회원가입 성공<h2>
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