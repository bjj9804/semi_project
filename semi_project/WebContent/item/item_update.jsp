<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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
	function itemUpdate(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=updateOk;
		xhr.open('post','/semi_project/jh/item.do?cmd=itemUpdate',true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var code=document.getElementById("code").value;
		var price=document.getElementById("price").value;
		var itemName=document.getElementById("itemName").value;
		var description=document.getElementById("description").value;
		var param="code="+code+"&price="+price+"&itemName="+itemName+"&description="+description;
		xhr.send(param);		
	}
	function updateOk(){
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				alert("상품정보가 수정되었습니다.");				
			}else{
				alert("변경실패!");
			}
		}
	}
	
</script>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>[ ${itemvo.code } ] 상품정보수정</h2>
			<table>
			<tr><th>가격</th><td><input type="text" id="price" value="${itemvo.price }"></td></tr>
			<tr><th>상품명</th><td><input type="text" id="itemName" value="${itemvo.itemName }"></td></tr>
			<tr><th>상세설명</th><td><textarea rows="5" cols="30" id="description" >${itemvo.description }</textarea></td></tr>
			</table>
			<input type="hidden" id="code" value="${itemvo.code }">
			<input type="button" value="수정" onclick="itemUpdate()">
			
			
			
			<h2>[ ${itemvo.code } ]이미지수정하기</h2>		
			<form action="/semi_project/jh/item.do?cmd=imgUpdate" method="post">
				<table width="500" align="center">
				<tr><th>이미지타입</th><th>이미지경로</th><th>삭제</th></tr>
				<c:forEach var="img" items="${imgList }">
				<tr>
					<td>${img.imgType }</td>
					<td>${img.imgScr }</td>
					<td><a href="/semi_project/jh/item.do?cmd=imgDelete&imgType=${img.imgType }&code=${img.code}">삭제</a></td>
				</tr>			
				</c:forEach>			
				</table>	
				<br>							
				<div id="filelist"></div>
				추가할 파일갯수<input type="text" size="2" id="fileCount" name="fileCount" >
				<input type="button" id="file" value="파일추가" >	
				<input type="hidden" name="code" value="${itemvo.code }">
				<br><input type="submit" value="수정">
			</form>	
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>