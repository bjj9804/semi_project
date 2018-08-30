<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<c:forEach var="vo" items="${list }">
				${vo.price }<br>
				${vo.itemName }
			</c:forEach>			
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>