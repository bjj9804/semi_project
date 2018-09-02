<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style type="text/css">
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>회원별 구매 리스트</h2>
			<ul class="admin_menu">
				<li><a href="/semi_project/mh/sale.do?cmd=salelist">전체보기</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=userlist">회원별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=daylist">날짜별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=itemlist">상품별</a></li>
			</ul>
			<table class="board_list">
				<colgroup>
					<col style="width:33.33%">
					<col style="width:33.33%">
					<col style="width:33.33%">
				</colgroup>
				<tr>
					<th>회원이메일</th>
					<th>총구매가격</th>
					<th>총구매횟수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.email }</td>
					<td>${vo.tot }</td>
					<td><a href="/semi_project/mh/sale.do?cmd=userdetail&&email=${vo.email }">${vo.cnt }</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>