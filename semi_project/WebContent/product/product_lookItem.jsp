<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style type="text/css">
		table{width:100%; text-align:center;}
		table th{border:1px solid #ccc; padding:10px 0;}
		table td{border:1px solid #ccc; padding:10px 0;}
	</style>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner" style="overflow:hidden;">
			<div style="float:left; width:400px;">
				<c:if test="${lvo.lookCode!=null }">
					<img src="/semi_project/itemFile/${lvo.lookFront }" onmouseover="this.src='/semi_project/itemFile/${lvo.lookBack }'" onmouseout="this.src='/semi_project/itemFile/${lvo.lookFront }'" style="width:100%;">
				</c:if>
			</div>
			<ul style="float:right;width:400px;">
				<c:forEach var="ob" items="${list }">
					<li>
						<img src="/semi_project/itemFile/${ob[1][0] }" onmouseover="this.src='/semi_project/itemFile/${ob[1][1] }'" onmouseout="this.src='/semi_project/itemFile/${ob[1][0] }'" onclick="location.href='/semi_project/jh/product.do?cmd=itemDetail&code=${ob[0] }'" style="width:100%;">
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>