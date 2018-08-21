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
			<table>
				<tr><th>글번호</th><td>${vo.num }</td></tr>
				<tr><th>작성자</th><td>${vo.name }</td></tr>
				<tr><th>제목</th><td>${vo.title }</td></tr>
				<tr><th>내용</th><td>${vo.content }</td></tr>
				<tr><th>조회수</th><td>${vo.hit }</td>></tr>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>