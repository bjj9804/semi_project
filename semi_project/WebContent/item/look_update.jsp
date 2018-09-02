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

	var xhr2=null;
	function lookDelete(lookCode){
		xhr2=new XMLHttpRequest();
		xhr2.onreadystatechange=delete1Ok;
		xhr2.open('post','/semi_project/jh/item.do?cmd=lookDelete',true);
		xhr2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		var param="lookCode="+lookCode;
		xhr2.send(param);		
	}
	function delete1Ok(){
		if(xhr2.readyState==4 && xhr2.status==200){
			var xml=xhr2.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("정상적으로 삭제되었습니다.");			
			}else{
				alert("삭제실패!");
			}
			history.go(-1);
		}
	}
	
	var xhr3=null;
	function runway(lookCode){
		xhr3=new XMLHttpRequest();
		xhr3.onreadystatechange=runwayOk;
		xhr3.open('post','/semi_project/jh/item.do?cmd=runway',true);
		xhr3.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		var param="lookCode="+lookCode;
		xhr3.send(param);		
	}
	function runwayOk(){
		if(xhr3.readyState==4 && xhr3.status==200){
			var xml=xhr3.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("정상적으로 등록되었습니다.");			
			}else{
				alert("등록실패!");
			}
			history.go(-1);
		}
	}
	
	var xhr4=null;
	function lookCodeUpdate(lookCode){
		xhr4=new XMLHttpRequest();
		xhr4.onreadystatechange=lookCodeUpdateOk;
		xhr4.open('post','/semi_project/jh/item.do?cmd=lookCodeUpdate',true);
		xhr4.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		var lookCode1=document.getElementById("lookCode1").value;
		var param="lookCode="+lookCode+"&lookCode1="+lookCode1;
		xhr4.send(param);		
	}
	function lookCodeUpdateOk(){
		if(xhr4.readyState==4 && xhr4.status==200){
			var xml=xhr4.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("정상적으로 변경되었습니다.");			
			}else{
				alert("변경실패!");
			}
			history.go(-1);
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
			<input type="text" id="lookCode1" value="${lvo.lookCode }"><input type="button" value="룩코드변경" onclick="lookCodeUpdate('${lvo.lookCode }')">
			<h3>이미지 변경</h3>
			
			<form name="look" action="/semi_project/jh/item.do?cmd=lookImgUpdate&lookCode=${lvo.lookCode }" method="post" enctype="multipart/form-data">			
			<input type="hidden" id="check" name="check">
				<table class="board_write">
					<colgroup>
						<col style="width:20%">
						<col style="width:40%">
						<col style="width:40%">
					</colgroup>
					<tr>
						<th>룩 앞면</th>
						<td><img src="/semi_project/itemFile/${lvo.lookFront }"></td>
						<td><input type="file" name="lookFront" value="변경할 파일선택"></td>
						<td><input type="button" value="파일변경" onclick="update1('1')">	</td>
					</tr>
					<tr>
						<th>룩 뒷면</th>
						<td><img src="/semi_project/itemFile/${lvo.lookBack }"></td>
						<td><input type="file" name="lookBack" value="변경할 파일선택"></td>
						<td><input type="button" value="파일변경" onclick="update1('2')"></td>
					</tr>
				</table>
			</form>
			
			<h2>룩 아이템 변경</h2>
			<table class="board_write">
			<colgroup>
				<col style="width:50%">
				<col style="width:50%">
			</colgroup>
			<tr><th>상품코드</th><th>삭제</th></tr>
			<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.code }</td>
				<td><input type="button" value="삭제" onclick="delete1('${vo.code}','${vo.lookCode }')"></td>
			</tr>			
			</c:forEach>
			</table>
			상품코드입력<input type="text" id="code"><input type="button" value="룩아이템추가" onclick="insert1('${lvo.lookCode }')">

			<br><br><br>
			<input type="button" value="룩 삭제하기" onclick="lookDelete('${lvo.lookCode }')">
			<input type="button" value="런웨이룩으로 등록하기" onclick="runway('${lvo.lookCode }')">


		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>