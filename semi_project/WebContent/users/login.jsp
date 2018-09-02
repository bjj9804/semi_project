<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<script type="text/javascript">
		function check(){
			var id = document.getElementsByName("id")[0];
			var email = document.getElementsByName("email")[0];
			
			if(id.value == "admin" || id.value == "ADMIN"){
				return true;
				break;
			}
			
			if(id.value == "" || email.value == ""){
				alert("이메일을 작성해주세요");
				id.focus();
				return false;
			}
		}
		function findPcheck(){
			var findPid = document.getElementById("findPid").value;
			var findPemail = document.getElementById("findPemail").value;
			var findPphone1 = document.getElementById("findPphone1").value;
			var findPphone2 = document.getElementById("findPphone2").value;
			var findPphone3 = document.getElementById("findPphone3").value;
			var findPquestion = document.getElementById("findPquestion").value;
			var findPanswer = document.getElementById("findPanswer").value;
			
			if(findPid == "" || findPemail == "" || findPphone1 == "" || findPphone2 == "" || findPphone3 == "" || findPquestion == "" || findPanswer == ""){
				alert("비밀번호 찾기 질문을 모두 기입해주세요");
				findpwd.style.display = "block";
				return false;
			}
		}
		function findEcheck(){
			var findEname = document.getElementById("findEname").value;
			var findEphone1 = document.getElementById("findEphone1").value;
			var findEphone2 = document.getElementById("findEphone2").value;
			var findEphone3 = document.getElementById("findEphone3").value;
			var findEquestion = document.getElementById("findEquestion").value;
			var findEanswer = document.getElementById("findEanswer").value;
			
			if(findEname == "" || findEphone1 == "" || findEphone2 == "" ||  findEphone3 == "" || findEquestion == "" ||  findEanswer == ""){
				alert("이메일 찾기 질문을 모두 기입해주세요");
				findemail.style.display = "block";
				return false;
			}
		}
		<%
			String id = "";
			String email1 = "";
			Cookie[] cooks = request.getCookies();
			if(cooks != null){
				for(Cookie cook : cooks){
					if(cook.getName().equals("id")){
						id=cook.getValue();
					}else if(cook.getName().equals("email1")){
						email1=cook.getValue();
					}
				}
			}
		%>
		function setEmail(){
			var inputVal = "<%=email1 %>"; //받아온값
			for(var i = 0; i < document.getElementById("emailSelect").options.length; i++)
			{
			  if(inputVal ==  document.getElementById("emailSelect").options[i].value)
			  {
			    document.getElementById("emailSelect").options[i].selected = true;
			  }
			}
		}
	</script>
	<style>
		#loginWrap{width: 100%;}
		#loginWrap #tableWrap{margin: auto; width: 100%; margin-top: 50px;}
		#loginWrap #tableWrap tr td{padding: 10px;}
		#loginWrap #tableWrap tr td select{margin-left: 10px; border: 1px solid #333; height: 38px; color: #333; padding-left: 10px; vertical-align: middle;}
		#loginWrap #tableWrap tr td input[type=text]{margin-right: 10px; border: 1px solid #333; height: 35px; color: #333; padding-left: 10px; vertical-align: middle;}
		#loginWrap #tableWrap tr td input[type=password]{border: 1px solid #333; height: 35px; color: #333; padding-left: 10px; vertical-align: middle;}
		#loginWrap #tableWrap tr td input[type=submit]{border: 1px solid #333; height: 35px; color: #fff; background-color:#333; pading-left: 10px; vertical-align: middle; width: 100px;}
		#loginWrap #tableWrap tr td input[type=button]{border: 1px solid #333; height: 35px; color: #fff; background-color:#aaa; pading-left: 10px; vertical-align: middle; width: 150px;}
		#loginWrap #tableWrap tr td ul li{margin-bottom: 10px;}
		#loginWrap #tableWrap tr td ul h3,h4{margin-bottom: 20px;}
		#findWrap table tr td{padding: 10px;}
		#findWrap table tr td select{border: 1px solid #333; height: 38px; color: #333; padding-left: 10px; vertical-align: middle;}
		#findWrap table tr td input[type=text]{margin-right: 10px; border: 1px solid #333; height: 35px; color: #333; padding-left: 10px; vertical-align: middle;}
		#findWrap table tr td input[type=password]{border: 1px solid #333; height: 35px; color: #333; padding-left: 10px; vertical-align: middle;}
		#findWrap table tr td input[type=submit]{border: 1px solid #333; height: 35px; color: #fff; background-color:#333; pading-left: 10px; vertical-align: middle; width: 100px;}
		#findWrap table tr td input[type=button]{border: 1px solid #333; height: 35px; color: #fff; background-color:#aaa; pading-left: 10px; vertical-align: middle; width: 150px;}
		#findWrap{width: 100%; padding: 20px;}
		#findWrap #findpwd,#findemail{margin: auto; width: 50%; padding:10px; background: #fff; border: 1px solid #ccc;}
		#findMsg{margin: auto; text-align: center;}
		
	</style>
</head>
<body onload="setEmail();">
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<div id="loginWrap">
				<table id="tableWrap">
					<tr>
						<th colspan="2"><h1>로그인</h1></th>
					</tr>
					<tr>
						<td style="width: 40%; padding: 20px; padding-left: 60px;">
							<h3>신규고객</h3>
							<h4>구찌 온라인 서비스 이용을 위해 회원가입을 해주시기 바랍니다.</h4>
							<ul>
								<li>상품 배송 현황 및 반품 내역 확인</li>
								<li>나만을 위한 구찌의 상품 추천</li>
								<li>관심 상품 편집</li>
							</ul>
							<input type="button" value="신규 회원 가입" onclick = "location.href = '/semi_project/users/join.jsp'"><br>
							<a href="javascript:getid()">아이디찾기</a>
						</td>
						<td style="width: 40%; border-left: solid 1px #aaa; padding: 20px; padding-left: 40px;">
							<form method="post" action="<c:url value='../mh/users.do?cmd=login'/>" onsubmit="return check();">
								<table>
									<tr>
										<th>
											이메일
										</th>
										<td>
											<input type="text" name="id" value=<%=id %>>@
											<select name="email" onchange="emailcheck()" id="emailSelect">
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
										</td>
									</tr>
									<tr>
										<th>
											비밀번호
										</th>
										<td>
											<input type="password" name="pwd">
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<input type="checkbox" name="autoCheck"><label>로그인 상태 유지</label>
											<input type="checkbox" name="idCheck" checked="checked"><label>아이디 기억하기</label><br>
										</td>
									</tr>
									<tr>
										<td colspan="2" style="height: 10px">
											<div style="font_size: 12px; color:red">${errMsg }</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<input type="submit" value="로그인"><br>
											<a href="javascript:getpwd()">비밀번호찾기</a>
										</td>
									</tr>
								</table>
							</form>
							
						</td>
					</tr>
				</table>
				<div id="findWrap">
					<!-- 아이디&비밀번호 찾기 -->
					<div id="findpwd" style="display: none">
						<form method="post" action="/semi_project/mh/users.do?cmd=findpwdform" onsubmit="return findPcheck();">
							<table>
								<tr>
									<th colspan="2">비밀번호 찾기</th>
								</tr>
								<tr>
									<td>
										이메일
									</td>
									<td>
										<input type="text" name="id" id="findPid"> @ 
										<select name="email" id="findPemail">
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
									</td>
								</tr>
								<tr>
									<td>
										전화번호
									</td>
									<td>
										<select name="phone1" id="findPphone1">
											<option value="010">010</option>
											<option value="011">011</option>
											<option value="070">070</option>
										</select> - 
										<input type="text" name="phone2" id="findPphone2"> - 
										<input type="text" name="phone3" id="findPphone3">
									</td>
								</tr>
								<tr>
									<td>
										질문
									</td>
									<td>
										<select name="question" id="findPquestion">
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
										</select>
									</td>
								</tr>
								<tr>
									<td>
										답변
									</td>
									<td>
										<input type="text" name="answer" id="findPanswer">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="찾기">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<!-- 이메일찾기 -->
					<div id="findemail" style="display: none">
						<form method="post" action="/semi_project/mh/users.do?cmd=findidform" onsubmit="return findEcheck();">
							<table>
								<tr>
									<th colspan="2">아이디 찾기</th>
								</tr>
								<tr>
									<td>
										이름
									</td>
									<td>
										<input type="text" name="name" id="findEname"><br>
									</td>
								</tr>
								<tr>
									<td>
										전화번호
									</td>
									<td>
										<select name="phone1" id="findEphone1">
											<option value="010">010</option>
											<option value="011">011</option>
											<option value="070">070</option>
										</select> - 
										<input type="text" name="phone2" id="findEphone2"> - 
										<input type="text" name="phone3" id="findEphone3">
									</td>
								</tr>
								<tr>
									<td>
										질문
									</td>
									<td>
										<select name="question" id="findEquestion">
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
										</select>
									</td>
								</tr>
								<tr>
									<td>
										답변
									</td>
									<td>
										<input type="text" name="answer" id="findEanswer">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="찾기">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="findMsg">
						<h2>${findMsg }</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
	<script type="text/javascript">
		var findpwd = document.getElementById("findpwd");
		var findemail = document.getElementById("findemail");
		var findMsg = document.getElementById("findMsg");
		function getpwd() {
			findMsg.style.display = "none";
			findemail.style.display = "none";
			findpwd.style.display = "block";
		}
		function getid() {
			findMsg.style.display = "none";
			findpwd.style.display = "none";
			findemail.style.display = "block";
		}
	</script>
</html>