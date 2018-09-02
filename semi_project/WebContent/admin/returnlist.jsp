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
			<h1>교환/환불 리스트</h1>
			<table class="board_list">
			<colgroup>
				<col style="10%">
				<col style="10%">
				<col style="10%">
				<col style="10%">
				<col style="10%">
				<col style="10%">
				<col style="10%">
			</colgroup>
			<tr>
			<th>주문번호</th>
			<th>상품코드</th>
			<th>사이즈</th>
			<th>수량</th>
			<th>상품가격</th>
			<th>상태</th>
			<th>반품사유</th>
			<th></th>
			</tr>
			<c:forEach var="vo" items="${list }" varStatus="status">
			<tr>
			<td>${vo.orderNum }</td>
			<td>${vo.code }</td>
			<td>${vo.isize }</td>
			<td>${vo.orderAmount }</td>
			<td>${vo.price }</td>
			<td>${vo.state }</td>
			<td>${realist[status.index].reason }</td>
			<c:if test="${vo.state == '교환신청중' }">
			<td><input type="button" value="교환완료" onclick="javascript:location.href='demand.do?cmd=refundcon2&num=${vo.buyNum}'"></td>
			</c:if>
			<c:if test="${vo.state == '반품신청중' }">
			<td><input type="button" value="반품완료" onclick="javascript:location.href='demand.do?cmd=refundcon&num=${vo.buyNum}'"></td>
			</c:if>
			</tr>
			</c:forEach>
			
			</table>
			
			
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>