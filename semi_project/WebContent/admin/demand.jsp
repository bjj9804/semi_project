<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table border="1" width=900px bordercolor="black">
			<h1>주문서</h1>
			<tr>
			<td>주문번호</td>
			<td>주문자이메일</td>
			<td>배송지</td>
			<td>총가격</td>
			<td>실결제가</td>
			<td>배송상태</td>
			<td>   </td>
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr>
			<td><a href="demand.do?cmd=buylist&num=${vo.orderNum}">${vo.orderNum }</a></td>
			<td>${vo.email }</td>
			<td>${vo.addr }</td>
			<td>${vo.totalPrice }</td>
			<td>${vo.payMoney }</td>
			<td>${vo.state }</td>
			<td><input type="button" value="배송완료" onclick="javascript:location.href='demand.do?cmd=stateupdate&num=${vo.orderNum}'"></td>
			</tr>
			</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>