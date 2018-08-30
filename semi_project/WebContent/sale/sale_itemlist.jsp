<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style type="text/css">
		.dd table{width:100%; text-align:center;}
		.dd table th{border:1px solid #ccc; padding:10px 0;}
		.dd table td{border:1px solid #ccc; padding:10px 0;}
		.dd{width: 100%; text-align: center; margin-top: 20px;}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>상품별 판매 리스트</h2>
			<ul class="admin_menu">
				<li><a href="/semi_project/mh/sale.do?cmd=salelist">전체보기</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=userlist">회원별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=daylist">날짜별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=itemlist">상품별</a></li>
			</ul>
			<div class="dd">
				<table>
					<tr>
						<th>상품코드</th>
						<th>상품명</th>
						<th>총판매수</th>
						<th>총판매액</th>
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.code }</td>
						<td>${vo.cnt }</td>
						<td>${vo.tot }</td>
						<td>${vo.itemname }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>