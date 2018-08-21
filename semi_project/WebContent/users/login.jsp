<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	#logiWrap{width: 800px; border: 1px solid #ccc;}
	#loginJoin #loginLogin{float: left; width: 50%;}
</style>
</head>
<body>
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
		<input type="button" value="신규 회원 가입" src="join.jsp">
	</div>
	<div id="loginLogin">
		<form method="post" action="login.do">
			아이디 <input type="text" name="id"><br>
			비밀번호 <input type="text" name="pwd"><br>
			<input type="checkbox" name="idCheck"><br>
			<input type="checkbox" name="pwdCheck"><br>
			<input type="submit" value="로그인">
		</form>
	</div>
</div>
</body>
</html>