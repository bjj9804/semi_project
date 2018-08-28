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
				file.name="imgSrc"+i;
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
	
	function itemInsert(){
		var itemInfo=document.itemInfo;
		var str="";
		var lookList=document.getElementsByName("lookList")[0];
		var lookCode=document.getElementsByName("lookCode");
		var bool=false;
		for(i=0;i<lookCode.length;i++){
			if(lookCode[i].checked==true){
				str+=lookCode[i].value+",";
				bool=true;
			}
		}
		if(bool==true){
			lookList.value=str.substring(0,str.lastIndexOf(","));
			itemInfo.submit();
		}else{
			alert("룩코드를 선택해주세요");
		}
		
	}
	
</script>

<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<form action="/semi_project/jh/item.do" method="post">
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
				<input type="hidden" name="cmd" value="lookInsert">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</form>
			
			
			<form name="itemInfo" action="/semi_project/jh/item.do" method="post">
			<h2>상품등록하기</h2>
				<table>
					<tr>
						<th>상품코드</th>
						<td><input type="text" name="code"></td>
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
							<select name="isize">
								<option value="small">S</option>
								<option value="medium">M</option>
								<option value="large">L</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>수량</th>
						<td><input type="text" name="amount"></td>
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
				<input type="hidden" name="cmd" value="itemInsert">
				<input type="button" value="등록" onclick="itemInsert()">
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>