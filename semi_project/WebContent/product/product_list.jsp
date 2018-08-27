<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style type="text/css">
		table{width:100%; text-align:center;}
		table th{border:1px solid #ccc; padding:10px 0;}
		table td{border:1px solid #ccc; padding:10px 0;}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table>
				<tr>
					<th>상품명</th>
					<th>가격</th>
					<th>상품앞면이미지</th>
					<th>상품뒷면이미지</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td><a href="/jh/product.do?cmd=detail&code=${vo.code }">${vo.itemName }</a></td>
					<td>${vo.price }원</td>
					<td>${vo.lookFront }</td>
					<td>${vo.lookBack }</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>