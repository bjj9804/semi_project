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
			<h2>item수정하기</h2>
			<table>
			<tr><th>상품코드</th><td><input type="text" name="code" value="${itemvo.code }"></td></tr>
			<tr><th>가격</th><td><input type="text" name="price" value="${itemvo.price }"></td></tr>
			<tr><th>상품명</th><td><input type="text" name="itemName" value="${itemvo.itemName }"></td></tr>
			<tr><th>상세설명</th><td><textarea rows="5" cols="30" name="description">${itemvo.description }</textarea></td></tr>
			</table>
			
			<h2>[ ${itemvo.code } ]이미지수정하기</h2>			
			<table>
			<tr><th>이미지타입</th><th>이미지경로</th></tr>
			<c:forEach var="img" items="${imgList }">
			<tr>
				<td><input type="text" name="imgType" value="${img.imgType }"></td>
				<td><input type="text" name="imgScr" value="${img.imgScr }"></td>
			</tr>			
			</c:forEach>			
			</table>
			
			
			
			
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>