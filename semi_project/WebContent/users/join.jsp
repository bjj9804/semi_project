<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<script type="text/javascript">
	var xhr = null;
	function emailcheck(){
		var email1 = doucument.getElementById("email1").value;
		var email2 = doucument.getElementById("email2").value;
		if(email1 == ""){
			document.getElementById(email1).innerHTML = "";
		}
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = emailcall;
		xhr.open();
	}
</script>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<div id="joinWrap">
			<form action="/semi_project/mh/join.do" method="post">
				<!-- 계정 세부정보 email,pwd 확인 -->
				<table style="width: 800px;">
					<tr>
						<th rowspan="10" style="width: 300px">계정 세부 정보</th>
					</tr>
					<tr>
						<td>이메일 주소</td>
						<td> </td>
						<td> </td>
					</tr>
					<tr>
						<td>
							<input type="text" name="email1" id="email1">
						</td>
						<td>
							@
						</td>
						<td>
							<select name="email2" onchange="emailcheck()" id="email2">
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
							<span id="emailcheck" style="color:red"> </span>
						</td>
					</tr>
					<tr>
						<td>이메일 주소 다시 입력</td>
						<td> </td>
						<td> </td>
					</tr>
					<tr>
						<td>
							<input type="text" name="emailok1" id="email1">
						</td>
						<td>
							@
						</td>
						<td>
							<select name="emailok2" id="email1">
								<option value="">옵션선택</option>
								<option value="naver.com">naver.com</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="nate.com">nate.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="hatmail.com">hatmail.com</option>
								<option value="daum.com">daum.net</option>
								<option value="yahoo.co.kr">yahoo.co.kr</option>
								<option value="hanmir.com">hanmir.com</option>
								<option value="dreamwiz.com">dreamwiz.com</option>
								<option value="lycos.co.kr">lycos.co.kr</option>
								<option value="empas.com">empas.com</option>
								<option value="paran.com">paran.com</option>
								<option value="">직접입력</option>
							</select>
						<td>
					</tr>
					<tr>
						<td colspan="3">
							<span id="emailcheck2" style="color:red"> </span>
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
						<td><input type="password" name="pwd"> </td>
						<td> </td>
						<td><input type="password" name="pwdok"></td>
					</tr>
					<tr>
						<td>
							<span id="pwdcheck" style="color:red"> </span>
						</td>
						<td> </td>
						<td>
							<span id="pwdcheck2" style="color:red"> </span>
						</td>
					</tr>
				</table>
				<!-- 필수 동의 -->
				<table style="width: 800px">
					<tr>
						<th style="width: 300px">
							필수 동의
						</th>
						<td>
							<input type="checkbox"><label>GUCCI공식 온라인 스토어 이용약관</label><br>
							<input type="checkbox"><label>개인정보 수집/이용에 관한 동의</label><br>
							<input type="checkbox"><label>개인정도 국외 이전에 관한 동의</label><br>
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
							<input type="text" name="name1">
						</td>
						<td>
							<input type="text" name="name2">
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
							</select>
							<input type="text" name="phone2">
							<input type="text" name="phone3">
						</td>
					</tr>
					<tr>
						<td>주소</td>
						<td> </td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="text" name="addr">
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
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>
