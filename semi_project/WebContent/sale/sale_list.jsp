<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<li><a href="/semi_project/mh/sale.do?cmd=salelist">전체보기</a></li>
					<li><a href="/semi_project/mh/sale.do?cmd=userlist">회원별</a></li>
					<li><a href="#">날짜별</a></li>
					<li><a href="#">상품별</a></li>
				</ul>
				<table>
					<tr>
						<td>회원이메일</td>
						<td>구매가격</td>
						<td>구매상태</td>
						<td>구매날짜</td>
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.email }<input type="button" value="ㅇㅇㅇㅇ"></td>
						<td>${vo.payMoney }</td>
						<td>${vo.state }</td>
						<td>${vo.orderDate }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>