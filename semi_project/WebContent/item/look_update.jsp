<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<script type="text/javascript">
	var xhr=null;
	function delete1(code,lookCode){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=deleteOk;
		xhr.open('post','/semi_project/jh/item.do?cmd=lookItemDelete',true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		var param="code="+code+"&lookCode="+lookCode;
		xhr.send(param);		
	}
	function deleteOk(){
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("룩아이템이 삭제되었습니다.");				
			}else{
				alert("삭제실패!");
			}
		}
	}

	var xhr1=null;
	function insert1(lookCode){
		xhr1=new XMLHttpRequest();
		xhr1.onreadystatechange=insertOk;
		xhr1.open('post','/semi_project/jh/item.do?cmd=lookItemInsert',true);
		xhr1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		var code=document.getElementById("code").value;
		var param="code="+code+"&lookCode="+lookCode;
		xhr1.send(param);		
	}
	function insertOk(){
		if(xhr1.readyState==4 && xhr1.status==200){
			var xml=xhr1.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("룩아이템이 추가되었습니다.");				
			}else{
				alert("추가실패!");
			}
		}
	}

	
	function update1(n){
		var look=document.look;
		var check=document.getElementById("check");
		if(n=='1'){
			check.value="1";
		}else if(n=='2'){
			check.value="2";
		}
		look.submit();
	}
</script>

<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>[ ${lvo.lookCode } ] 룩</h2>
			<h3>이미지 변경</h3>
			<form name="look" action="/semi_project/jh/item.do?cmd=lookImgUpdate" method="post">
			<input type="hidden" name="lookCode" value="${lvo.lookCode }">
			<input type="hidden" id="check" name="check">
			룩 앞면 ${lvo.lookFront } <input type="file" name="lookFront" value="변경할 파일선택"> <input type="button" value="파일변경" onclick="update1('1')">	
			<br>
			룩 뒷면 ${lvo.lookBack } <input type="file" name="lookBack" value="변경할 파일선택"> <input type="button" value="파일변경" onclick="update1('2')">
			</form>
			
			<h3>룩 아이템 변경</h3>
			<table width="300">
			<tr><th>상품코드</th><th>삭제</th></tr>
			<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.code }</td>
				<td><input type="button" value="삭제" onclick="delete1('${vo.code}','${vo.lookCode }')"></td>
			</tr>			
			</c:forEach>
			</table>
			상품코드입력<input type="text" id="code"><input type="button" value="룩아이템추가" onclick="insert1('${lvo.lookCode }')">




		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>