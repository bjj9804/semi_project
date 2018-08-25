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
			<ul>
				<li><a href="/semi_project/myshopBoard?cmd=reviewList">내가쓴 리뷰</a></li>
				<li><a href="/semi_project/myshopBoard?cmd=qnaList">내가쓴 질문</a></li>
			</ul>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>