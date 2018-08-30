<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<script type="text/javascript">
	
		//이메일 사용가능 여부
		
		var xhr = null;
		function emailcheck(){
			var email1 = document.getElementById("email1");
			var email2 = document.getElementById("email2");
			var email = email1.value + email2.value;
			if(email1.value == "" || email2.value == ""){
				document.getElementById("echeck").innerHTML = "";
				return;//메소드 끝내기
			}
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = emailcall;
			xhr.open('get','/semi_project/mh/users.do?cmd=emailcheck&&email='+email,true);
			xhr.send();
		}
		function emailcall(){
			 if(xhr.readyState == 4 && xhr.status == 200){
				 var text = xhr.responseText;
				 var json = JSON.parse(text);
				 var span = document.getElementById("echeck");
				 if(json.using == true){
					 span.innerHTML = "사용할 수 없는 이메일 입니다."
				 }else{
					 span.innerHTML = "사용가능한 아이디 입니다."
				 }
			 }
		}
		
		//이메일 다시입력 확인
		
		function reemailOk(){
			var email1 = document.getElementById("email1");
			var email2 = document.getElementById("email2");
			var email = email1.value + email2.value;
			var reemail1 = document.getElementById("reemail1");
			var reemail2 = document.getElementById("reemail2");
			var reemail = reemail1.value + reemail2.value;
			var span = document.getElementById("echeck2");
			if(reemail1.value == "" || reemail2.value == ""){
				span.innerHTML = "";
				return;//메소드 끝내기
			}else if(email == reemail){
				span.innerHTML = "Ok!";
				return;//메소드 끝내기
			}else if(email != reemail){
				span.innerHTML = "이메일이 같지 않습니다.";
			}
		}
		
		//비밀번호 제약
		
		function pwdcheck(){
			var pwd = document.getElementById("pwd").value;
			var span = document.getElementById("pwdcheck");
			if(pwd == ""){
				span.innerHTML = "";
				return;//메소드 끝내기
			}
			for(var i=0; i<pwd.length; i++){
				if(!(pwd.charAt(i)>= 'a' && pwd.charAt(i) <= 'z') && !(pwd.charAt(i)>= 'a' && pwd.charAt(i) <= 'z') && !(pwd.charAt(i)>= '0' && pwd.charAt(i) <= '9')){
					span.innerHTML = "대소문자나 숫자로만 작성";
				}
			}
			for(var i=0; i<pwd.length; i++){
				if((pwd.charAt(i)>= 'a' && pwd.charAt(i) <= 'z') && (pwd.charAt(i)>= 'a' && pwd.charAt(i) <= 'z') && (pwd.charAt(i)>= '0' && pwd.charAt(i) <= '9')){
					span.innerHTML = "";
				}
			}
		}
		
		//비밀번호 확인
		
		function pwdcheck2(){
			var pwd = document.getElementById("pwd").value;
			var pwdok = document.getElementById("pwdok").value;
			var span = document.getElementById("pwdcheck2");
			if(pwd == "" || pwdok == ""){
				span.innerHTML = "";
				return;//메소드 끝내기
			}else if(pwd == pwdok){
				span.innerHTML = "Ok!";
				return;//메소드 끝내기
			}else if(pwd != pwdok){
				span.innerHTML = "비밀번호가 같지 않습니다.";
			}
		}
		
		function check(){
			var email1 = document.getElementById("email1");
			var email2 = document.getElementById("email2");
			var reemail1 = document.getElementById("reemail1");
			var reemail2 = document.getElementById("reemail2");
			var pwd = document.getElementById("pwd");
			var pwdok = document.getElementById("pwdok");
			var question = document.getElementsByName("question")[0];
			var answer = document.getElementsByName("answer")[0];
			var name1 = document.getElementsByName("name1")[0];
			var name2 = document.getElementsByName("name2")[0];
			var phone1 = document.getElementsByName("phone1")[0];
			var phone2 = document.getElementsByName("phone2")[0];
			var phone3 = document.getElementsByName("phone3")[0];
			var addr = document.getElementsByName("addr")[0];
			
			if(email1.value == "" || email2.value == ""){
				alert("아이디를 확인하세요");
				email1.focus();
				return false;
			}
			if(reemail1.value == "" || reemail2.value == ""){
				alert("아이디를 다시 확인하세요");
				reemail1.focus();
				return false;
			}
			if(pwd.value == ""){
				alert("비밀번호를 확인하세요");
				pwd.focus();
				return false;
			}
			if(pwdok.value == ""){
				alert("비밀번호를 다시 확인하세요");
				pwdok.focus();
				return false;
			}
			if(question.value == ""){
				alert("질문을 확인하세요");
				question.focus();
				return false;
			}
			if(answer.value == ""){
				alert("답변을 확인하세요");
				answer.focus();
				return false;
			}
			var Ckcnt = 0;
	        var arr_ckb = document.getElementsByName("ckb");
	        for(var i=0;i<arr_ckb.length;i++){
	            if(arr_ckb[i].checked == true) {
	            	Ckcnt = Ckcnt+1;
	            }
	        }
	        if(Ckcnt != 3){
	            alert("약관에 동의 해주세요");
	            return false;
	        }
			if(name1.value == ""){
				alert("이름을 확인하세요");
				name1.focus();
				return false;
			}
			if(name2.value == ""){
				alert("이름을 확인하세요");
				name2.focus();
				return false;
			}
			if(phone1.value == "" || phone2.value == "" || phone3.value == ""){
				alert("전화번호를 확인하세요");
				phone2.focus();
				return false;
			}
			if(addr.value == ""){
				alert("주소를 확인하세요");
				addr.focus();
				return false;
			}
		}
	</script>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<form action="/semi_project/mh/users.do?cmd=joinform" method="post" onsubmit="return check();">
				<!-- 계정 세부정보 email,pwd 확인 -->
				<table style="width: 800px;">
					<tr>
						<th rowspan="12" style="width: 300px">계정 세부 정보</th>
					</tr>
					<tr>
						<td>이메일 주소</td>
						<td> </td>
						<td> </td>
					</tr>
					<tr>
						<td>
							<input type="text" name="email1" id="email1" onkeyup="emailcheck()" placeholder="ID">
						</td>
						<td>
							@
						</td>
						<td>
							<select name="email2" id="email2" onchange="emailcheck()">
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
						<td>
					</tr>
					<tr>
						<td colspan="3">
							<span id="echeck" style="color:red"> </span>
						</td>
					</tr>
					<tr>
						<td>이메일 주소 다시 입력</td>
						<td> </td>
						<td> </td>
					</tr>
					<tr>
						<td>
							<input type="text" name="emailok1" id="reemail1" onkeyup="reemailOk()" placeholder="ID">
						</td>
						<td>
							@
						</td>
						<td>
							<select name="emailok2" id="reemail2" onchange="reemailOk()">
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
						<td>
					</tr>
					<tr>
						<td colspan="3">
							<span id="echeck2" style="color:red"> </span>
						</td>
					</tr>
					<tr>
						<td>
							비밀번호
						</td>
						<td> </td>
						<td>
							비밀번호 다시 입력
						</td>
					</tr>
					<tr>
						<td><input type="password" name="pwd" id="pwd" onkeyup="pwdcheck()" placeholder="PASSWORD"> </td>
						<td> </td>
						<td><input type="password" name="pwdok" id="pwdok" onkeyup="pwdcheck2()" placeholder="PASSWORD"></td>
					</tr>
					<tr>
						<td>
							<span id="pwdcheck" style="color:red"></span>
						</td>
						<td></td>
						<td>
							<span id="pwdcheck2" style="color:red"></span>
						</td>
					</tr>
					<tr>
						<td>
							이메일/비밀번호 찾기 질문
						</td>
						<td> </td>
						<td>
							답변
						</td>
					</tr>
					<tr>
						<td>
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
							</select>
						</td>
						<td> </td>
						<td><input type="text" name="answer" placeholder="ANSWER"></td>
					</tr>
				</table>
				<!-- 필수 동의 -->
				<table style="width: 800px">
					<tr>
						<th style="width: 300px">
							필수 동의
						</th>
						<td>
							<input type="checkbox" name="ckb"><label>GUCCI공식 온라인 스토어 이용약관</label><br>
							<input type="checkbox" name="ckb"><label>개인정보 수집/이용에 관한 동의</label><br>
							<input type="checkbox" name="ckb"><label>개인정도 국외 이전에 관한 동의</label><br>
						</td>
					</tr>
				</table>
				<!-- 정보 -->
				<table style="width: 800px">
					<tr>
						<th rowspan="10" style="width: 300px">정보</th>
					</tr>
					<tr>
						<td>성</td>
						<td>이름</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="name1" placeholder="LAST NAME">
						</td>
						<td>
							<input type="text" name="name2" placeholder="NAME">
						</td>
					</tr>
					<tr>
						<td>연락처</td>
						<td> </td>
					</tr>
					<tr>
						<td colspan="2">
							<select name="phone1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="070">070</option>
							</select>-
							<input type="text" name="phone2" placeholder="0000">-
							<input type="text" name="phone3" placeholder="0000">
						</td>
					</tr>
					<tr>
						<td>주소</td>
						<td> </td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="text" name="addr" placeholder="ADDRRESS">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="저장">
							<input type="reset" value="취소">
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>
