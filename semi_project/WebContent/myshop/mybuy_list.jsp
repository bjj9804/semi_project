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
			<h1>나의 주문내역</h1>
			<tr>
			<td>주문일시</td>
			<td>주문번호</td>
			<td>배송지</td>
			<td>총가격</td>
			<td>실결제가</td>
			<td>배송상태</td>
			<td>   </td>
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr>
			<td>${vo.orderDate }</td>
			<td><a href="demand.do?cmd=mydetail&num=${vo.orderNum}">${vo.orderNum }</a></td>
			<td>${vo.addr }</td>
			<td>${vo.totalPrice }</td>
			<td>${vo.payMoney }</td>
			<td>${vo.state }</td>
			<!-- 배송준비중상태에서는 바로 취소가 가능한 취소버튼이 보인다. -->
			<c:if test="${vo.state=='배송준비중' }">
			<td><input type="button" value="구매취소" onclick="javascript:location.href='demand.do?cmd=cancel&num=${vo.orderNum}'"></td>
			</c:if>
			<!-- 판매자가 배송을 완료하여 배송중상태여야지 구매확정버튼이 보인다. -->
			<c:if test="${vo.state=='배송중' }">
			<td><input type="button" value="구매확정" onclick="javascript:location.href='demand.do?cmd=stateconfirm&num=${vo.orderNum}'"></td>
			<td><input type="button" value="교환" onclick="javascript:location.href='demand.do?cmd=buychange&num=${vo.orderNum}'"></td>
			<td><input type="button" value="환불" onclick="javascript:location.href='demand.do?cmd=stateupdate&num=${vo.orderNum}'"></td>
			</c:if>
			<!-- 구매확정이 완료되면 교환,환불버튼이 안보이고 리뷰남기기버튼이 보인다.. -->
			
			</tr>
			
			</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>