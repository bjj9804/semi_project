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
				<h2>판매 전체 리스트</h2>
				<ul class="admin_menu">
					<li><a href="sale_list.jsp">전체보기</a></li>
					<li><a href="#">회원별</a></li>
					<li><a href="#">날짜별</a></li>
					<li><a href="#">상품별</a></li>
				</ul>
			</div>
			<div>
				<table>
					<tr>
						<td>판매번호</td>
						<td>회원명</td>
						<td>구매가격</td>
						<td>구매날짜</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>