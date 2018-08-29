<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style type="text/css">
		table{width:100%; text-align:center;}
		table th{border:1px solid #ccc; padding:10px 0;}
		table td{border:1px solid #ccc; padding:10px 0;}
		.dd{width: 100%; text-align: center; margin-top: 20px;}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>판매 전체 리스트</h2>
			<ul class="admin_menu">
				<li><a href="/semi_project/mh/sale.do?cmd=salelist">전체보기</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=userlist">회원별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=daylist">날짜별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=itemlist">상품별</a></li>
			</ul>
			<div class="dd">
				<table>
					<tr>
						<th>회원이메일</th>
						<th>구매가격</th>
						<th>구매상태</th>
						<th>구매날짜</th>
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.email }</td>
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