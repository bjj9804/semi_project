<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
<script type="text/javascript">
	var xhr = null;
	function getPwd(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callPwd;
		var email = document.getElementById("email").value;
		var phone = document.getElementById("phone").value;
		xhr.open("get","findPwd.jsp?email=" + email + "&phone=" + phone,true);
		xhr.send();
	}
	function callPwd(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			var findOk = document.getElementById("findOk");
			findOk.innerHTML = data;
		}
	}
</script>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<div id="findP">
				<h2>비밀번호 찾기</h2>
				이메일<input type="text" id="email">
				폰번호<input type="text" id="phone">
				<input type="button" value="찾기" onclick="getPwd()">
			</div>
			<div id="findOk">
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>