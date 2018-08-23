<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<table>
					<tr>
						<th colspan="2">로그인</th>
					</tr>
					<tr>
						<td>
							신규고객
							구찌 온라인 서비스 이용을 위해 회원가입을 해주시기 바랍니다.
							<ul>
								<li>상품 배송 현황 및 반품 내역 확인</li>
								<li>나만을 위한 구찌의 상품 추천</li>
								<li>관심 상품 편집</li>
							</ul>
							<input type="button" value="신규 회원 가입" onclick = "location.href = '/semi_project/users/join.jsp'">
						</td>
						<td>
							<form method="post" action="<c:url value='../mh/login.do?cmd=login'/>">
								이메일 <input type="text" name="id">@
								<select name="email">
									<option value="">옵션선택</option>
									<option value="@naver.com">naver.com</option>
									<option value="@hanmail.net">hanmail.net</option>
									<option value="@nate.com">nate.com</option>
									<option value="@gmail.com">gmail.com</option>
									<option value="@hatmail.com">hatmail.com</option>
									<option value="@daum.com">daum.net</option>
									<option value="@yahoo.co.kr">yahoo.co.kr</option>
									<option value="@hanmir.com">hanmir.com</option>
									<option value="@dreamwiz.com">dreamwiz.com</option>
									<option value="@lycos.co.kr">lycos.co.kr</option>
									<option value="@empas.com">empas.com</option>
									<option value="@paran.com">paran.com</option>
								</select>
								<br>
								비밀번호 <input type="password" name="pwd"><br>
								<input type="checkbox" name="idCheck"><label>로그인 상태 유지</label><br>
								<input type="checkbox" name="pwdCheck"><label>아이디 기억하기</label><br>
								<div style="font_size: 12px; color:red">${errMsg }</div>
								<input type="submit" value="로그인"><br>
								<input type="button" value="비밀번호찾기" onclick="getpwd()">
								<input type="button" value="아이디찾기" onclick="getid()"><br>
							</form>
							<div id="findpwd" style="display: none">
								<form method="post" action="/semi_project/mh/find.do?cmd=findpwdform">
									폰번호<input type="text" name="phone"><br>
									이메일<input type="text" name="email"><br>
									질문
									<select name="question">
										<option value="">질문선택</option>
										<option value="1">당신의 취미는?</option>	
										<option value="2">어릴적 별명은?</option>	
										<option value="3">첫째 고모 성함은?</option>	
										<option value="4">감명깊게 읽은 책은?</option>	
										<option value="5">여자친구의 발사이즈는?</option>	
										<option value="6">친구는 몇명인가요?</option>	
										<option value="7">전 여자친구 이름은?</option>	
										<option value="8">전 남자친구 이름은?</option>	
										<option value="9">햄최몇?</option>	
										<option value="10">엄마의 발사이즈는?</option>	
										<option value="11">짝궁의 이름은?</option>	
										<option value="12">방 벽지 색깔은?</option>	
									</select><br>
									답변<input type="text" name="answer">
									<input type="submit" value="찾기">
								</form>
							</div>
							<div id="findemail" style="display: none">
								<form method="post" action="/semi_project/mh/find.do?cmd=findidform">
									폰번호<input type="text" name="phone"><br>
									질문
									<select name="question">
										<option value="">질문선택</option>
										<option value="1">당신의 취미는?</option>	
										<option value="2">어릴적 별명은?</option>	
										<option value="3">첫째 고모 성함은?</option>	
										<option value="4">감명깊게 읽은 책은?</option>	
										<option value="5">여자친구의 발사이즈는?</option>	
										<option value="6">친구는 몇명인가요?</option>	
										<option value="7">전 여자친구 이름은?</option>	
										<option value="8">전 남자친구 이름은?</option>	
										<option value="9">햄최몇?</option>	
										<option value="10">엄마의 발사이즈는?</option>	
										<option value="11">짝궁의 이름은?</option>	
										<option value="12">방 벽지 색깔은?</option>	
									</select><br>
									답변<input type="text" name="answer">
									<input type="submit" value="찾기">
								</form>
							</div>
							<div id="findMsg">
								<h2>${findMsg }</h2>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
	<script type="text/javascript">
		var findpwd = document.getElementById("findpwd");
		var findemail = document.getElementById("findemail");
		function getpwd() {
			findemail.style.display = "none";
			findpwd.style.display = "block";
		}
		function getid() {
			findpwd.style.display = "none";
			findemail.style.display = "block";
		}
	</script>
</html>