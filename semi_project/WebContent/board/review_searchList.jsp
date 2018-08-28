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
		<table border="1" width="500">
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>글제목</th>
					<th>삭제</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.num }</td>
						<td>${vo.name }</td>
						<td><a href="/semi_project/detail.do?num=${vo.num }">${vo.title }</a></td>
					</tr>
				</c:forEach>
			</table>
			<form action="/semi_project/reviewBoard.do?cmd=searchlist" method="post">
				height <select name="height">
					<option value="">입력안함</option>
					<option value="140">~140</option>
					<option value=150>140~150</option>
					<option value=160>150~160</option>
					<option value=170>160~170</option>
					<option value=180>170~180</option>
					<option value=190>180~190</option>
					<option value=200>190~200</option>
					<option value=210>200~</option>
				</select> &nbsp; &nbsp; weight <select name="weight">
					<option value="">입력안함</option>
					<option value="40">~40kg</option>
					<option value=50>40kg~50kg</option>
					<option value=60>50kg~60kg</option>
					<option value=70>60kg~70kg</option>
					<option value=80>70kg~80kg</option>
					<option value=90>80kg~90kg</option>
					<option value=100>90kg~</option>
				</select> 
				<input type="submit" value="리뷰보기">
				
			</form>
			<div>
				<c:choose>
					<c:when test="${pageNum>4}">
						<a href="/semi_project/reviewBoard.do?cmd=searchlist&pageNum=${startPage-1 }">[이전]</a>
					</c:when>
					<c:otherwise>
			[이전]
		</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum==i }">
							<a href="/semi_project/reviewBoard.do?cmd=searchlist&pageNum=${i }"><span style="color: red">[${i }]</span></a>
						</c:when>
						<c:otherwise>
							<a href="/semi_project/reviewBoard.do?cmd=searchlist&pageNum=${i }"><span style="color: #555">[${i }]</span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPage<pageCount}">
						<a href="/semi_project/reviewBoard.do?cmd=searchlist&pageNum=${endPage+1 }">[다음]</a>
					</c:when>
					<c:otherwise>
			[다음]
		</c:otherwise>
				</c:choose>
		</div>
	</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>