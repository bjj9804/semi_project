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
			<h1>회원관리</h1>
			<table class="board_list">
				<colgroup>
					<col>
					<col style="width:15%;">
					<col style="width:15%;">
					<col style="width:7%;">
					<col style="width:10%;">
					<col style="width:3%;">
					<col style="width:20%;">
					<col style="width:5%;">
				</colgroup>
				<tr>
					<th>이메일</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>이름</th>
					<th>가입일</th>
					<th>쿠폰</th>
					<th>쿠폰지급</th>
					<th>탈퇴</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.email }</td>
					<td>${vo.phone }</td>
					<td>${vo.addr }</td>
					<td>${vo.name }</td>
					<td>${vo.regdate }</td>
					<td>${vo.coupon }</td>
					<td>
						<form method="post" action="/semi_project/mh/users.do?cmd=coupongift&&email=${vo.email }">
							<select name="couponGift">
								<option value="vip쿠폰(10%)">vip쿠폰(10%)</option>
								<option value="이벤트당첨쿠폰(5%)">이벤트당첨쿠폰(5%)</option>
								<option value="랜덤쿠폰(5%)">랜덤쿠폰(5%)</option>
								<option value="여름시즌쿠폰(5%)">여름시즌쿠폰(5%)</option>
								<option value="겨울시즌쿠폰(5%)">겨울시즌쿠폰(5%)</option>
							</select>
							<input type="submit" value="지급">
						</form>
					</td>
					<td><a href="/semi_project/mh/myshopmodify.do?cmd=admindelete&&email=${vo.email }">탈퇴</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>