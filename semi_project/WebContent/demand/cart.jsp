<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
		
			<h1>CART</h1><br>			
			<form name="cart" action="/semi_project/jh/demand.do?cmd=showOrderForm" method="post">
			
			<h3>담긴 상품</h3>	
				<table border="1" width="800" align="center">
					<tr>					
						<c:choose>
							<c:when test="${!empty list }">
								<th><input type="checkbox" name="check" onclick="checkAll()"></th>
							</c:when>
						</c:choose>
						<th>이미지</th> 
						<th>상품정보</th> 
						<th>판매가</th> 
						<th>수량</th> 
						<th>합계</th>					
					</tr>					
					<c:choose>
						<c:when test="${empty list }">
							<tr><td colspan="5">등록된 상품이 없습니다.</td></tr>							
						</c:when>					
						<c:otherwise>
							<c:forEach var="vo" items="${list }">						
								<tr>
									<td><input type="checkbox" name="check" value="${vo[0].buyNum }" onclick="check1()"></td>
									<td>${vo[1] }</td> <!-- 이미지 -->
									<td>${vo[2] }</td> <!-- 상품정보 -->
									<td>${vo[3] }</td> <!-- 판매가 -->
									<td>${vo[0].orderAmount }</td> <!-- 수량 -->
									<td name="price">${vo[0].price }</td> <!-- 합계 -->
								</tr>	
							</c:forEach>						
						</c:otherwise>
					</c:choose>
					
				</table>
				
				<c:choose>
					<c:when test="${!empty list }">
						<input type="button" value="내보내기" onclick="delete1('${email}')">
						<br><br>
						
						결제예정금액<div id="totalPrice1"></div>
						<input type="hidden" name="totalPrice">
						<input type="hidden" name="email" value="${email }">
						<input type="hidden" id="buyList" name="buyList">
						<input type="button" value="선택상품주문" onclick="getBuyList('${email}')">		
					</c:when>
				</c:choose>					
				</form>
				<br><a href="/semi_project/main/index.jsp">쇼핑계속하기</a>
								
				
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
<script type="text/javascript">	
	function checkAll(){
		var chk=document.getElementsByName("check");
		if(chk[0].checked==true){
			for(var i=1;i<chk.length;i++){
				chk[i].checked=true;				
			}
			var totalPrice1=document.getElementById("totalPrice1");
			var totalPrice=document.getElementsByName("totalPrice")[0];
			var price=document.getElementsByName("price");	
			var totPrice=0;
			for(var i=1;i<chk.length;i++){
				totPrice=totPrice*1+price[i-1].innerHTML*1;	
			}	
			totalPrice1.innerHTML=totPrice;
			totalPrice.value=totPrice;
		}else{
			for(var i=1;i<chk.length;i++){
				chk[i].checked=false;				
			}
			var totalPrice1=document.getElementById("totalPrice1");
			var totalPrice=document.getElementsByName("totalPrice")[0];			
			totalPrice1.innerHTML=0;
			totalPrice.value=0;
			
			
		}
	}
	function check1(){
		var chk=document.getElementsByName("check");		
		var price=document.getElementsByName("price");		
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==false){
				chk[0].checked=false;
			}				
		}	
		var totalPrice1=document.getElementById("totalPrice1");
		var totalPrice=document.getElementsByName("totalPrice")[0];
		var totPrice=0;
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==true){
				totPrice=totPrice*1+price[i-1].innerHTML*1;				
			}
		}	
		totalPrice1.innerHTML=totPrice;
		totalPrice.value=totPrice;
	}
	function getBuyList(){
		var cart=document.cart;
		var buyList=document.getElementById("buyList");
		var checkList="";
		var chk=document.getElementsByName("check");
		var bool=false;
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==true){
				checkList+=chk[i].value+",";
				bool=true;
			}
		}
		if(bool==true){
			checkList=checkList.substring(0,checkList.lastIndexOf(","));//맨끝 콤마 지우기
			buyList.value=checkList;
			cart.submit();
		}else{
			alert("구매할 상품을 선택해주세요");
		}
		
	}
	
	function delete1(email){
		var checkList="";
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==true){
				checkList+=chk[i].value+",";
			}
		}
		checkList=checkList.substring(0,checkList.lastIndexOf(","));//맨끝 콤마 지우기
		
		if(checkList==''){
			alert("삭제할 대상을 선택하세요");
			return false;
		}
		console.log(checkList);
		if(confirm("삭제하시겠습니까?")){
			location.href="/semi_project/jh/demand.do?checkList="+checkList+"&cmd=delete&email="+email;
		}
	}	

</script>
</html>
