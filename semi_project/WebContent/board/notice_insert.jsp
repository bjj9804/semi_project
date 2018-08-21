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
			<form action="../jh/notice.do" method="post">
				<table>
					<tr><th>작성자</th><td><input type="text" name="name"></td></tr>
					<tr><th>이메일</th><td><input type="text" name="email"></td></tr>
					<tr><th>제목</th><td><input type="text" name="title"></td></tr>
					<tr><th>내용</th><td><textarea rows="5" cols="50" name="content"></textarea></td></tr>									
				</table>
				<input type="hidden" name="cmd" value="insert">
				<input type="submit" value="작성">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>