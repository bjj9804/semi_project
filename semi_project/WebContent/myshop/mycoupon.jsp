<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>나의 쿠폰리스트</h2>
			<table class="board_list">
				<colgroup>
					<col style="width:25%;">
					<col style="width:25%;">
					<col style="width:25%;">
					<col style="width:25%;">
				</colgroup>
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
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>