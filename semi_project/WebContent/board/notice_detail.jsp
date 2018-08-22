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
			<table>
				<tr><th>글번호</th><td>${vo.num }</td></tr>
				<tr><th>작성자</th><td>${vo.name }</td></tr>
				<tr><th>이메일</th><td>${vo.email }</td></tr>
				<tr><th>제목</th><td>${vo.title }</td></tr>
				<tr><th>내용</th><td>${vo.content }</td></tr>
				<tr><th>조회수</th><td>${vo.hit }</td></tr>
				<tr><th>작성날짜</th><td>${vo.regdate }</td></tr>
			</table>
			<input type="button" value="답글작성" onclick="javascript:location.href='insert.jsp?num=${vo.num }&grp=${vo.grp}&lev=${vo.lev}&step=${vo.step}'" style="position:relative; left:420px">
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>