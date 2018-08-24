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
			<div>
				주문정보
			</div>
			<div>
				<ul>
					<li><a href="#">주문 배송 조회</a></li>
					<li><a href="profile.jsp">정보 수정</a></li>
					<li><a href="#">게시물</a></li>
					<li><a href="#">쿠폰</a></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>