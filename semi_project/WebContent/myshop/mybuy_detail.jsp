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
			<h2>상세주문서</h2>
			<table class="board_list">
				<colgroup>
					<col style="width:15%;">
					<col>
					<col style="width:15%;">
					<col style="width:10%;">
					<col style="width:15%;">
					<col style="width:10%;">
				</colgroup>
			<tr>
			<td>주문번호</td>
			<td>상품코드</td>
			<td>사이즈</td>
			<td>수량</td>
			<td>상품가격</td>
			<td>주문상태</td>
			
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr>
			<td>${vo.orderNum }</td>
			<td>${vo.code }</td>
			<td>${vo.isize }</td>
			<td>${vo.orderAmount }</td>
			<td>${vo.price }</td>
			<td>${vo.state }
			
			<c:if test="${vo.state=='구매완료' || vo.state=='교환완료'}">
			<!-- <a href="/semi_project/board/review_insert.jsp?code=${vo.code }">리뷰</a><br> -->
			<br><input type="button" value="리뷰작성" onclick="javascript:location.href='/semi_project/board/review_insert.jsp?code=${vo.code }'" >
			</c:if>
			<c:if test="${vo.state=='배송중' }">
			<input type="button" value="구매확정" onclick="javascript:location.href='demand.do?cmd=stateconfirm2&num=${vo.buyNum}'">
			</c:if>
			</td>
			</tr>
			
			</c:forEach>
			</table>
			<br>
			<c:if test="${flag==0 }">
			<input type="button" value="목록으로" onclick="/semi_project/demand.do?cmd=paylist" class="btn_write">
<!-- 			<a href="/semi_project/demand.do?cmd=paylist" class="btn_write">목록으로</a><br> -->
			</c:if>
			<c:if test="${flag==1 }">
			<a href="/semi_project/demand.do?cmd=mylist&email=${email }">목록으로</a><br>
			</c:if>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>