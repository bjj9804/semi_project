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
			<h2>NOTICE</h2>
			<form action="/semi_project/jh/notice.do" method="post">
				<table class="board_write">
					<colgroup>
						<col style="width:30%;">
						<col>
					</colgroup>			
					<tr><th>제목</th><td><input type="text" name="title"></td></tr>
					<tr><th>내용</th><td><textarea rows="5" cols="50" name="content"></textarea></td></tr>									
				</table>
				<div class="view_btn">
					<input type="hidden" name="email" value=${sessionScope.email }>
					<input type="hidden" name="cmd" value="insert">
					<input type="submit" value="작성" class="btn_write">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>