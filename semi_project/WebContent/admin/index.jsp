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
			<ul class="admin_menu">
				<li><a href="#">상품관리</a></li>
				<li><a href="/semi_project/board/index.jsp">게시글관리</a></li>
				<li><a href="#">주문관리</a></li>
				<li><a href="#">판매관리</a></li>
			</ul>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>