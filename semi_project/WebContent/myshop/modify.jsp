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
			<!-- 정보수정, 탈퇴 -->
			<form action="/semi_project/mh/myshopmodify.do?cmd=modify">
				<table>
					<tr>
						<th>이메일</th>
						<td><input type="text" readonly="readonly" value="${vo.email }" name="email"></td>
					</tr>
					<tr>
						<th>비밀번호 변경</th>
						<td><input type="password" value="${vo.password }" name="pwd"></td>
					</tr>
					<tr>
						<th>비밀번호 변경 확인</th>
						<td><input type="password" value="${vo.password }"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" value="${vo.name }" name="name"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="text" value="${vo.phone }" name="phone"></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" value="${vo.addr }" name="addr"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정하기">
							<input type="reset" value="취소">
						</td>
					</tr>
				</table>
				<a href="/semi_project/mh/myshopmodify.do?cmd=delete&&email=${email }">탈퇴하기</a>
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>