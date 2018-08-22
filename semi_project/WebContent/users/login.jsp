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
			<div id="logiWrap">
				<div id="loginHeader">로그인</div>
				<div id="loginJoin">
					신규고객
					구찌 온라인 서비스 이용을 위해 회원가입을 해주시기 바랍니다.
					<ul>
						<li>상품 배송 현황 및 반품 내역 확인</li>
						<li>나만을 위한 구찌의 상품 추천</li>
						<li>관심 상품 편집</li>
					</ul>
					<input type="button" value="신규 회원 가입" onclick = "location.href = '../user/join.jsp' ">
				</div>
				<div id="loginLogin">
					<form method="post" action="../mh/login.do">
						아이디 <input type="text" name="id"><br>
						비밀번호 <input type="text" name="pwd"><br>
						<input type="checkbox" name="idCheck"><br>
						<input type="checkbox" name="pwdCheck"><br>
						<input type="submit" value="로그인">
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>