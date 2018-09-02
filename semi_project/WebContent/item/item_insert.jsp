<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>

	<jsp:include page="/inc/header.jsp"/>
</head>
<script type="text/javascript">
	window.onload=function(){
		var file=document.getElementById("file");				
		var filelist=document.getElementById("filelist");	
		file.onclick=function(){
			var fileCount=document.getElementById("fileCount").value;	
			for(var i=1;i<=fileCount;i++){
				var div=document.createElement("div");
				var file=document.createElement("input");
				file.type="file";
				file.name="imgScr"+i;
				var imgType=document.createElement("input");
				imgType.type="text";
				imgType.name="imgType"+i;			
				
				div.appendChild(imgType);
				div.appendChild(file);
				filelist.appendChild(div);
			}
		}
		
	}
	
	var xhr=null;
	function lookInsert(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=lookInsertOk;
		xhr.open('post','/semi_project/jh/item.do?cmd=lookInsert',true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		var lookCode=document.getElementById("lookCode").value;
		var lookFront=document.getElementById("lookFront").scr;
		var lookBack=document.getElementById("lookBack").scr;
		var param="cmd=lookInsert&lookCode="+lookCode+"&lookFront="+lookFront+"&lookBack"+lookBack;
		xhr.send(param);
	}
	function lookInsertOk(){
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("룩에 대한 정보가 성공적으로 저장되었습니다.");
			}else{
				alert("룩에 대한 정보가 저장에 실패하였습니다...");
			}
		}
	}
	
	var xhr1=null;
	function checkCode(){
		xhr1=new XMLHttpRequest();
		xhr1.onreadystatechange=checkOk;
		xhr1.open('post','/semi_project/jh/item.do?cmd=checkCode',true);
		xhr1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		var code=document.getElementById("code").value;		
		var param="code="+code;
		xhr1.send(param);
	}
	function checkOk(){
		if(xhr1.readyState==4 && xhr1.status==200){
			var xml=xhr1.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("사용가능한 코드입니다.");
			}else{
				alert("이미 등록된 코드입니다.");
			}
		}
	}
	
	
	
	function itemInsert(){
		var itemInfo=document.itemInfo;
		var str="";
		var lookList=document.getElementsByName("lookList")[0];
		var lookCode=document.getElementsByName("lookCode");
		for(i=0;i<lookCode.length;i++){
			if(lookCode[i].checked==true){
				str+=lookCode[i].value+",";
			}
		}
		lookList.value=str.substring(0,str.lastIndexOf(","));
		itemInfo.submit();
	}
	
	
	function size1(){
		var small=document.getElementById("small");
		var medium=document.getElementById("medium");
		var large=document.getElementById("large");
		var amountSmall=document.getElementsByName("amountSmall")[0].value;
		var amountMedium=document.getElementsByName("amountMedium")[0].value;
		var amountLarge=document.getElementsByName("amountLarge")[0].value;
		var amountS=document.getElementById("amountS");
		var amountM=document.getElementById("amountM");
		var amountL=document.getElementById("amountL");
		if(small.checked==true){
			amountS.style.display="inline-block";
		}else{
			amountSmall.value="";
			amountS.style.display="none";
		}
		if(medium.checked==true){
			amountM.style.display="inline-block";
		}else{
			amountMedium.value="";
			amountM.style.display="none";
		}
		if(large.checked==true){
			amountL.style.display="inline-block";
		}else{
			amountLarge.value="";
			amountL.style.display="none";
		}
	}
	
	
</script>

<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<form action="/semi_project/jh/item.do?cmd=lookInsert" method="post" enctype="multipart/form-data">
			<h2>룩등록하기</h2>
				<table>
					<tr>
						<th>룩코드</th>
						<td><input type="text" name="lookCode"></td>
					</tr>
					<tr>
						<th>룩앞면이미지</th>
						<td>
							<input type="file" name="lookFront">
						</td>
					</tr>
					<tr>
						<th>룩뒷면이미지</th>
						<td>
							<input type="file" name="lookBack">
						</td>
					</tr>					
				</table>				
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</form>
			
			
			<form name="itemInfo" action="/semi_project/jh/item.do?cmd=itemInsert" method="post" enctype="multipart/form-data">
			<h2>상품등록하기</h2>
				<table>
					<tr>
						<th>상품코드</th>
						<td><input type="text" id="code" name="code"><input type="button" value="코드중복검사" onclick="checkCode()"></td>
					</tr>
					<tr>
						<th>가격</th>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><input type="text" name="itemName"></td>
					</tr>
					<tr>
						<th>상품설명</th>
						<td><textarea name="description"></textarea></td>
					</tr>
					<tr>
						<th>아이템사이즈</th>
						<td>
							<input type="checkbox" id="small" onclick="size1()">SMALL
							<span id="amountS" style="display: none;"> 수량<input type="text" name="amountSmall"></span><br>
							<input type="checkbox" id="medium" onclick="size1()">MEDIUM
							<span id="amountM" style="display: none;"> 수량<input type="text" name="amountMedium"></span><br>
							<input type="checkbox" id="large" onclick="size1()">LARGE
							<span id="amountL" style="display: none;"> 수량<input type="text"  name="amountLarge"></span>
												
						</td>
					</tr>								
					<tr>
						<th>이미지타입</th>
						<td>	
							추가할 파일갯수<input type="text" size="2" id="fileCount" name="fileCount" >
							<input type="button" id="file" value="파일추가" >												
							<div id="filelist"></div>
						</td>
					</tr>	
					<tr>
						<th>룩코드 선택하기</th>
						<td>
							<c:forEach var="vo" items="${list }">
								<input type="checkbox" name="lookCode" value="${vo.lookCode }">
								<span>${vo.lookCode }</span>
							</c:forEach>
						</td>
					</tr>					
				</table>
					
				<input type="hidden" name="lookList">
				<input type="button" value="등록" onclick="itemInsert()">
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>