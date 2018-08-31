<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>

<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
		<%String sessionE=(String)session.getAttribute("email"); %>
		<form action="/semi_project/jh/demand.do?cmd=cart" method="post">
			<table width="800">
				<tr>
					<th>상품명</th>
					<th>가격</th>
					<th>상품설명</th>
					<th>사이즈</th>
					<th>수량</th>					
				</tr>
				<tr>
					<td>${item.itemName }</td>
					<td>${item.price }</td>
					<td>${item.description }</td>
					<td>
						<select name="isize">
							<c:forEach var="svo" items="${size}">
								<option value="${svo.isize }">${svo.isize } (${svo.amount })개 남음</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="orderAmount">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>						
					</td>					
				</tr>
			</table>
			<br>
			<h2>상세이미지</h2>
			<c:forEach var="imgvo" items="${img }">
				<img src="/semi_project/itemFile/${imgvo.imgScr }">	<br>	
			</c:forEach>
			
			
			<input type="hidden" name="code" value="${item.code }">
			<input type="hidden" name="email" value="${email }">
			<br><input type="submit" value="상품담기">
		</form>
		
		
		
		
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>