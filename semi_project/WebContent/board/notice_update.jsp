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
			<form action="/semi_project/jh/notice.do?cmd=update&num=${vo.num }&flag=${flag}&pageNum=${pageNum}" method="post">
				<table>
					<tr><th>제목</th><td><input type="text" name="title" value=${vo.title }></td></tr>
					<tr><th>내용</th><td><textarea rows="5" cols="30" name="content" >${vo.content}</textarea> </td></tr>
				</table>
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</form>
			
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>