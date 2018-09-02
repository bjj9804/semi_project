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
			<h2>주문서</h2>
			<table class="board_list">
				<colgroup>
					<col style="width:15%;">
					<col>
					<col style="width:15%;">
					<col style="width:10%;">
					<col style="width:15%;">
					<col style="width:10%;">
				</colgroup>
				<tr>
					<th>주문번호</th>
					<th>주문자이메일</th>
					<th>배송지</th>
					<th>총가격</th>
					<th>실결제가</th>
					<th>배송상태</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td><a href="demand.do?cmd=buylist&num=${vo.orderNum}">${vo.orderNum }</a></td>
						<td>${vo.email }</td>
						<td>${vo.addr }</td>
						<td>${vo.totalPrice }</td>
						<td>${vo.payMoney }</td>
						<td>
							${vo.state }<br>
							<c:if test="${vo.state=='배송준비중' }">
								<input type="button" value="배송완료" onclick="javascript:location.href='demand.do?cmd=stateadmin&num=${vo.orderNum}'">
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>