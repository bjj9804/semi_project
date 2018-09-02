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
		var code1=document.getElementById("code1").value;
		var price=document.getElementById("price").value;
		var itemName=document.getElementById("itemName").value;
		var description=document.getElementById("description").value;
		var param="code="+code+"&code1="+code1+"&price="+price+"&itemName="+itemName+"&description="+description;
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
			<table class="board_write">
			<colgroup>
				<col style="width:20%;">
				<col>
			</colgroup>
			<tr><th>상품코드</th><td><input type="text" id="code1" value="${itemvo.code }"></td></tr>
			<tr><th>가격</th><td><input type="text" id="price" value="${itemvo.price }"></td></tr>
			<tr><th>상품명</th><td><input type="text" id="itemName" value="${itemvo.itemName }"></td></tr>
			<tr><th>상세설명</th><td><textarea rows="5" cols="30" id="description" >${itemvo.description }</textarea></td></tr>
			</table>
			<div class="view_btn">  
				<input type="hidden" id="code" value="${itemvo.code }">
				<input type="button" value="수정" onclick="itemUpdate()" class="btn_edit">
			</div>
			
			
			
			<h2>[ ${itemvo.code } ]이미지수정하기</h2>		
			<form action="/semi_project/jh/item.do?cmd=imgUpdate&code=${itemvo.code }" method="post" enctype="multipart/form-data">
				<table class="board_list">
					<colgroup>
						<col style="20%">
						<col>
						<col style="20%">
					</colgroup>
					<tr><th>이미지타입</th><th>이미지</th><th>삭제</th></tr>
					<c:forEach var="img" items="${imgList }">
					<tr>
						<td>${img.imgType }</td>
						<td>
							<img src="/semi_project/itemFile/${img.imgScr }" style="width:300px; margin:0 auto;">
						</td>
						<td><a href="/semi_project/jh/item.do?cmd=imgDelete&imgType=${img.imgType }&code=${img.code}">삭제</a></td>
					</tr>			
					</c:forEach>			
				</table>	
				<br>							
				<div id="filelist"></div>
				추가할 파일갯수<input type="text" size="2" id="fileCount" name="fileCount" >
				<input type="button" id="file" value="파일추가" >	
				<div class="view_btn">
					<input type="submit" value="수정" class="btn_edit">
				</div>
			</form>	
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>