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
	function check1(){
		var look=document.getElementById("look");	
		var lookCode=document.getElementById("lookCode");	
		var lookCodeList=document.getElementsByName("lookCodeList");	
		lookCode.innerHTML+=look.value+",";
		lookCodeList.value=lookCode.innerHTML;
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
			
			
			<form action="/semi_project/jh/item.do" method="post">
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
						<th>룩코드</th>
						<td>
							<select id="look" onclick="check1()">
								<c:forEach var="vo" items="${list }">
									<option value="${vo.lookCode }">${vo.lookCode }</option>
								</c:forEach>
							</select>							
							<div id="lookCode"></div>
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
				</table>
					
				<input type="hidden" name="lookCodeList">
				<input type="hidden" name="cmd" value="itemInsert">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>