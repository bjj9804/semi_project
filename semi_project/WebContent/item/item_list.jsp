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
					<th>상품명</th>
					<th>상품이미지</th>
					<th>상품설명</th>
					<th>가격</th>
					<th>사이즈</th>
					<th>수량</th>
					<th>룩코드</th>
					<th>룩앞면사진</th>
					<th>룩뒷면사진</th>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>