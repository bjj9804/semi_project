<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
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
					<th>사진</th>
					<td><img src="/semi_project/Upload/${vo.img}"></td>
			</table>
			<input type="button" value="My Review 목록"
				onclick="javascript:location.href='/semi_project/myshopBoard.do?cmd=reviewList&pageNum=${pageNum }&pageNum1=${pageNum }'"
				style="margin-left: 5%">
			<c:if test="${email==vo.email}">
				<a href="/semi_project/myshopBoard.do?num=${vo.num }&cmd=reviewDetail&cmd1=det_update&flag=${flag}&pageNum=${pageNum}">수정</a>
				<a href="/semi_project/myshopBoard.do?checkList=${vo.num }&cmd=delete&email=${email }&pageNum=${pageNum}">삭제</a>			
			
			</c:if>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>