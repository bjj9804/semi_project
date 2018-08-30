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
				<li><a href="/semi_project/jh/notice.do?cmd=list&email=${email }">공지사항 게시판</a></li>
				<li><a href="/semi_project/eb/qnalist.do?cmd=list&email=${email }">문의게시판</a></li>
				<li><a href="/semi_project/reviewBoard.do?cmd=list&email=${email }">후기게시판</a></li>
			</ul>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>