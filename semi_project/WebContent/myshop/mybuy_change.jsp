<%@page import="eb.QnaBoardDao"%>
<%@page import="eb.QnaBoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>

	<jsp:include page="/inc/gnb.jsp"/>
	<h1>상품교환</h1>
		교환할상품
		<select name="교환">
		<option value="">물건1</option>
		<option value="">물건2</option>
		<option value="">물건3</option>
		<option value="">물건4</option>
		
		
		
		</select>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>