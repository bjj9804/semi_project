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
			<h2>나의 쿠폰리스트</h2>
			<div class="dd">
				<table>
					<tr>
						<th>쿠폰내용</th>
						<th>쿠폰상태</th>
						<th>쿠폰발급일</th>
						<th>쿠폰만료일</th>
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.couponName }</td>
						<td>${vo.couponState }</td>
						<td>${vo.offerDate }</td>
						<td>${vo.endDate }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>