<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table align="center">
				<tr>
					<th>상품코드</th>
					<th>가격</th>
					<th>상품명</th>
					<th>상품설명</th>
					<th>이미지타입</th>
					<th>이미지경로</th>
					<th>아이템사이즈</th>
					<th>수량</th>
					<th>룩번호</th>
					<th>룩코드</th>
					<th>룩앞면</th>
					<th>룩뒷면</th>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>