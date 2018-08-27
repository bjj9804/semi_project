<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />

</head>
<body>
<%
	int pageNum =Integer.parseInt(request.getParameter("pageNum"));
%>
	<jsp:include page="/inc/gnb.jsp" />
	<div id="content">
		<div class="inner">
			<table>
				<tr>
					<th>글번호</th>
					<td>${vo.num }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${vo.name }</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${vo.email }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${vo.title }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${vo.content }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${vo.hit }</td>
				</tr>
				<tr>
					<th>작성날짜</th>
					<td>${vo.regdate }</td>
				</tr>
				<tr>
					<th>사진</th> ${vo.img}
					<td><img src="/semi_project/Upload/${vo.img}"></td>
			</table>
			<input type="button" value="review 목록"
				onclick="javascript:location.href='/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=<%=pageNum %>'"
				style="margin-left: 5%">
			<c:if test="${email==vo.email}">
				<input type="button" value="수정"
					onclick="javascript:location.href='/semi_project/reviewBoard.do?cmd=detail&cmd1=update&num=${vo.num}'"
					style="margin-left: 5%">
				<input type="button" value="삭제"
					onclick="javascript:location.href='/semi_project/reviewBoard.do?checkList=${vo.num }&cmd=delete&email=${email}&pageNum=${pageNum }'"
					style="margin-left: 5%">
			</c:if>
			<c:if test="${flag==0 }">
				<input type="button" value="삭제"
					onclick="javascript:location.href='/semi_project/reviewBoard.do?checkList=${vo.num }&cmd=delete&email=${email}&pageNum=${pageNum }'"
					style="margin-left: 5%">
			</c:if>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>