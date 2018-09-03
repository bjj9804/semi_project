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
			<ul class="category">
				<li>
					<img src="/semi_project/images/main/men1.jpg" onclick="location.href='/semi_project/jh/product.do?cmd=showItem&mark=m'">
				</li>
				<li>
					<img src="/semi_project/images/main/men2.jpg" onclick="location.href='/semi_project/jh/product.do?cmd=showItem&mark=m'">
				</li>
				<li>
					<img src="/semi_project/images/main/men3.jpg" onclick="location.href='/semi_project/jh/product.do?cmd=showItem&mark=m'">
				</li>
			</ul>			
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>