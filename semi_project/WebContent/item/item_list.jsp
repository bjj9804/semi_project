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
		#content .inner a{display:block; width:100px; height:30px; line-height:30px; background-color:#222; color:#fff; text-align:center; margin:20px auto}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table align="center">
				<tr>
					<th>상품코드</th>
					<th>가격</th>
					<th>상품명</th>
					<th>상품설명</th>
					<th>이미지타입</th>
					<th>이미지경로</th>
					<th>아이템사이즈</th>
					<th>수량</th>
					<th>룩번호</th>
					<th>룩코드</th>
					<th>룩앞면</th>
					<th>룩뒷면</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.code }</td>
					<td>${vo.price }</td>
					<td>${vo.itemName }</td>
					<td>${vo.description }</td>
					<td>${vo.imgType }</td>
					<td>${vo.imgSrc }</td>
					<td>${vo.isize }</td>
					<td>${vo.amount }</td>
					<td>${vo.num }</td>
					<td>${vo.lookCode }</td>
					<td>${vo.lookFront }</td>
					<td>${vo.lookBack }</td>
				</tr>
				</c:forEach>
			</table>
			<a href="/semi_project/jh/item.do?cmd=getLookCode">상품등록</a>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>