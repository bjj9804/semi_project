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
			<h1>회원관리</h1>
			<div class="dd">
				<table>
					<tr>
						<th>이메일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>이름</th>
						<th>가입일</th>
						<th>쿠폰</th>
						<th>탈퇴</th>
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.email }</td>
						<td>${vo.phone }</td>
						<td>${vo.addr }</td>
						<td>${vo.name }</td>
						<td>${vo.regdate }</td>
						<td><a href="#">${vo.coupon }</a></td>
						<td><a href="/semi_project/mh/myshopmodify.do?cmd=admindelete&&email=${vo.email }">탈퇴</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>