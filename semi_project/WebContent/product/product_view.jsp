<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<script type="text/javascript">
	function cart(){
		var frm=document.frm;
		var zero=document.getElementById("isize").value;
		if(zero=='0'){
			alert('품절된 상품입니다.');
		}else{
			frm.submit();
		}
	}

</script>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
		<%String sessionE=(String)session.getAttribute("email"); %>
		<form name="frm" action="/semi_project/jh/demand.do?cmd=cart" method="post">
			<table width="800">
				<tr>
					<th>상품명</th>
					<th>가격</th>
					<th>상품설명</th>
					<th>사이즈</th>
					<th>수량</th>					
				</tr>
				<tr>
					<td>${item.itemName }</td>
					<td>${item.price }</td>
					<td>${item.description }</td>
					<td>
						<select id="isize" name="isize">
							<c:forEach var="svo" items="${size}">
							<c:if test="${svo.amount==0 }">
								<option value="0">${svo.isize }------품절</option>
							</c:if>
							<c:if test="${svo.amount!=0 }">
								<option value="${svo.isize }">${svo.isize } (${svo.amount })개 남음</option>
							</c:if>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="orderAmount">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>						
					</td>					
				</tr>
			</table>
			<br>
			<h2>상세이미지</h2>
			<c:forEach var="imgvo" items="${img }">
				<img src="/semi_project/itemFile/${imgvo.imgScr }">	<br>	
			</c:forEach>
			
			
			<input type="hidden" name="code" value="${item.code }">
			<input type="hidden" name="email" value="${email }">
			<br><input type="button" value="상품담기" onclick="cart()">
		</form>
		
		
		
		
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>