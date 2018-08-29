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
			<h2>
				주문정보
			</h2>
			<div>
				<ul class="admin_menu"> 
					<li><a href="/semi_project/demand.do?cmd=mylist&email=${email }">주문 배송 조회</a></li>
					<li><a href="profile.jsp">정보 수정</a></li>
					<li><a href="/semi_project/myshopBoard.do?cmd=reviewList&email=${email }">게시물</a></li>
					<li><a href="/semi_project/mh/coupon.do?cmd=coupon&email=${email }">쿠폰</a></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>