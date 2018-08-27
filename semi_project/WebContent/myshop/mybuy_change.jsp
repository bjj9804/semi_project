<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
	<jsp:include page="/inc/gnb.jsp" />
	<div id="content">
		<div class="inner">
			<table border="1" width=900px bordercolor="black">
				<h1>교환</h1>
				<form action="" method="post" border="1" bordercolor="black">
				<tr>
					<td> </td>
					<td>주문상품정보</td>
					<td>가격</td>
				</tr>
				<c:forEach var="vo1" items="${itemlist }">
					<tr>
						<td><input type="checkbox" value=${vo1.itemName }></td>
						<td>${vo1.itemName }</td>
				</c:forEach>
				<c:forEach var="vo" items="${list }">
					<td>${vo.price }</td>
					</tr>
				</c:forEach>
				</form>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>