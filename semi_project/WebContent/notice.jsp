<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>NOTICE BOARD</h2>
<table border="1" width="800">
	<tr><th>글번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.num }</td>
			<td><a href="notice.do?num=${vo.num }&cmd='detail'">${vo.title }</a></td>
			<td>${vo.email }</td>
			<td>${vo.hit }</td>		
		</tr>
	</c:forEach>

</table>
</body>
</html>