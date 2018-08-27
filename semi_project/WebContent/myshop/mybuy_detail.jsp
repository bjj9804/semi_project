<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table border="1" width=900px bordercolor="black">
			<h1>상세주문서</h1>
			<tr>
			<td>주문번호</td>
			<td>상품코드</td>
			<td>사이즈</td>
			<td>수량</td>
			<td>상품가격</td>
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr>
			<td>${vo.orderNum }</td>
			<td>${vo.code }</td>
			<td>${vo.isize }</td>
			<td>${vo.orderAmount }</td>
			<td>${vo.price }</td>
			<td>
			<c:if test="${state=='구매완료' }">
			<a href="/semi_project/board/review_insert.jsp?code=${vo.code }">리뷰남겨</a><br>
			<td><input type="button" value="리뷰남기기" onclick="javascript:location.href='/semi_project/board/review_insert.jsp?code='+${vo.code}"></td>
			</c:if>
			</td>
			</tr>
			
			</c:forEach>
			</table>
			<br>
			<c:if test="${flag==0 }">
			<a href="/semi_project/demand.do?cmd=paylist">목록으로</a><br>
			</c:if>
			<c:if test="${flag==1 }">
			<a href="/semi_project/demand.do?cmd=mylist&email=${email }">목록으로</a><br>
			</c:if>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>