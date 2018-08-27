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
			<form action="/semi_project/jj/item.do" method="post">
				<table>
					<tr>
						<th>상품코드</th>
						<td><input type="text" name="code"></td>
					</tr>
					<tr>
						<th>가격</th>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><input type="text" name="itemName"></td>
					</tr>
					<tr>
						<th>상품설명</th>
						<td><textarea name="description"></textarea></td>
					</tr>
					<tr>
						<th>이미지타입</th>
						<td>
							<input type="file" name="imgType">
						</td>
					</tr>
					<tr>
						<th>아이템사이즈</th>
						<td>
							<select name="isize">
								<option value="small">S</option>
								<option value="medium">M</option>
								<option value="large">L</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>수량</th>
						<td><input type="text" name="amount"></td>
					</tr>
					<tr>
						<th>룩코드</th>
						<td><input type="text" name="lookCode"></td>
					</tr>
					<tr>
						<th>룩앞면</th>
						<td>
							<input type="file" name="lookFront">
						</td>
					</tr>
					<tr>
						<th>룩뒷면</th>
						<td>
							<input type="file" name="lookBack">
						</td>
					</tr>
				</table>				
				<input type="hidden" name="cmd" value="insert">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>