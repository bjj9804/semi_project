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
			<c:if test="${cmd=='runway' }">
				<h1>R U N W A Y</h1>
			</c:if>
			<c:if test="${cmd=='women' }">
				<h1>W O M E N</h1>
			</c:if>
			<c:if test="${cmd=='men' }">
				<h1>M E N</h1>
			</c:if>

			<div class="look_list">
				<c:choose>
					<c:when test="${list==null }">
						상품이 준비중입니다.
					</c:when>
					<c:otherwise>
					<c:if test="${cmd!='runway' }">
						<c:forEach var="vo" items="${list }">
							<div class="lookArea">
								<img src="/semi_project/itemFile/${vo.lookFront }" onmouseover="this.src='/semi_project/itemFile/${vo.lookBack }'" onmouseout="this.src='/semi_project/itemFile/${vo.lookFront }'" onclick="location.href='/semi_project/jh/product.do?cmd=showLookItem&lookCode=${vo.lookCode}'" class="imgLook">
							</div>	
						</c:forEach>
					</c:if>
					<c:if test="${cmd=='runway' }">						
						<div class="lookArea">
							<img src="/semi_project/itemFile/${list[0].lookFront }" onmouseover="this.src='/semi_project/itemFile/${list[0].lookBack }'" onmouseout="this.src='/semi_project/itemFile/${list[0].lookFront }'" onclick="location.href='/semi_project/jh/product.do?cmd=lookList&cmd1=women'" class="imgLook">
						</div>	
						<div class="lookArea">
							<img src="/semi_project/itemFile/${list[1].lookFront }" onmouseover="this.src='/semi_project/itemFile/${list[1].lookBack }'" onmouseout="this.src='/semi_project/itemFile/${list[1].lookFront }'" onclick="location.href='/semi_project/jh/product.do?cmd=lookList&cmd1=men'" class="imgLook">
						</div>	
					</c:if>
					</c:otherwise>	
				</c:choose>
			</div>
		
		
		
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>