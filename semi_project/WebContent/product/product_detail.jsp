<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="inner">
		<form action="">
			<table>
				<tr>
					<th>상품명</th>
					<th>가격</th>
					<th>상품설명</th>
					<th>사이즈</th>
					<th>수량</th>
					<th>상품이미지</th>
				</tr>
				<tr>
					<td>${itemvo.itemName }</td>
					<td>${itemvo.price }</td>
					<td>${itemvo.description }</td>
					<td>
						<select name="isize">
							<option value="small">S</option>
							<option value="medium">M</option>
							<option value="large">L</option>
						</select>
					</td>
					<td>
						<select name="amount">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>
					</td>
					<td>
						상품상세이미지.jpg
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>