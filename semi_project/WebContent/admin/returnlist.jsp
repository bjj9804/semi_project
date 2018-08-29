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
			<h1>교환/환불 리스트</h1>
			<tr>
			<td>주문번호</td>
			<td>상품코드</td>
			<td>사이즈</td>
			<td>수량</td>
			<td>상품가격</td>
			<td>상태</td>
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr>
			<td>${vo.orderNum }</td>
			<td>${vo.code }</td>
			<td>${vo.isize }</td>
			<td>${vo.orderAmount }</td>
			<td>${vo.price }</td>
			<td>${vo.state }</td>
			</tr>
		
			</c:forEach>
			</table>
			<br>
			<c:if test="${vo.state == '교환대기중' }">
			<input type="button" value="교환완료" onclick="javascript:location.href='demand.do?cmd=stateadmin&num=${vo.orderNum}'"><br>
			</c:if>
			<c:if test="${vo.state == '반품대기중' }">
			<input type="button" value="반품완료" onclick="javascript:location.href='demand.do?cmd=stateadmin&num=${vo.orderNum}'"><br>
			</c:if>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>