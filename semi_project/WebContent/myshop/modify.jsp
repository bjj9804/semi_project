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
			<form method="post" action="/semi_project/mh/myshopmodify.do?cmd=modify">
				<table class="board_view">
					<colgroup>
						<col style="width:20%;">
						<col style="width:30%;">
						<col style="width:20%;">
						<col style="width:30%;">
					</colgroup>
					<tr>
						<th>이름</th>
						<td><input type="text" value="${vo.name }" name="name"></td>
						<th>이메일</th>
						<td><input type="text" readonly="readonly" value="${vo.email }" name="email"></td>
					</tr>
					<tr>
						<th>비밀번호 변경</th>
						<td><input type="password" value="${vo.password }" name="pwd"></td>
						<th>비밀번호 변경 확인</th>
						<td><input type="password" value="${vo.password }"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="text" value="${vo.phone }" name="phone"></td>
						<th>주소</th>
						<td><input type="text" value="${vo.addr }" name="addr"></td>
					</tr>
				</table>
				<div class="view_btn">
					<input type="submit" value="수정" class="btn_edit">
					<input type="reset" value="취소" class="btn_del">		
					<a href="/semi_project/mh/myshopmodify.do?cmd=delete&&email=${email }" class="btn_list">탈퇴</a>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>