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
		var input=document.createElement("input");
		input.type="file";
		filelist.appendChild(file);
	}
	/*var img1=document.createElement("img");
	var img2=document.createElement("img");
	var img3=document.createElement("img");
	btn.onclick=function(){			
		img1.src="images/1.png";			
		img2.src="images/2.png";			
		img3.src="images/3.png";
	}
	imglist.appendChild(img1);
	imglist.appendChild(img2);
	imglist.appendChild(img3);
	
	img1.onmouseover=function(){
		img1.border="2px solid black";
	}
	img2.onmouseover=function(){
		img2.border="2px solid black";
	}
	img3.onmouseover=function(){
		img3.border="2px solid black";
	}*/
}
</script>

<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<form action="/semi_project/jj/item.do" method="post">
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
				<input type="hidden" name="cmd" value="insert">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</form>
			<form action="/semi_project/jj/item.do" method="post">
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
						<td><input type="text" name="lookCode"></td>
					</tr>
					<tr>
						<th>이미지타입</th>
						<td>
							<input type="button" id="file" value="파일추가" onclick="">
							<div id="filelist"></div>
						</td>
					</tr>					
				</table>				
				<input type="hidden" name="cmd" value="insert">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>