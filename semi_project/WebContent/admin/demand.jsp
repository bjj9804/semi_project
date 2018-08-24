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
			<td>번호</td>
			<td>상품코드</td>
			<td>사이즈</td>
			<td>주문자이메일</td>
			<td>배송지</td>
			<td>총가격</td>
			<td>실결제가</td>
			<td>배송상태</td>
			<td>상태수정</td>
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr>
			<td>${vo.num }</td>
			<td>${vo.writer }</td>
			<td>44</td>
			<td>92eunbyul@naver.com</td>
			<td>서울시 은평구</td>
			<td>456513</td>
			<td>163513</td>
			<td>배송준비중</td>
			<td>상태수정</td>
			
			</tr>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>