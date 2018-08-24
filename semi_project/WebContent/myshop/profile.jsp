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
			<!-- 본인 비밀번호 확인 받고 수정들어가기 -->
			<form method="post" action="/semi_project/mh/login.do?cmd=profileView">
				본인확인을 위해 비밀번호를 입력하세요.<br>
				<input type="password" name="pwd"><br>
				<input type="submit" value="확인">
			</form>
			<div>
				<h3>${errMsg }</h3>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>