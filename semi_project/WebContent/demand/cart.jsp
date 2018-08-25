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
						<th><input type="checkbox" name="check" onclick="checkAll()"></th>
						<th>이미지</th> 
						<th>상품정보</th> 
						<th>판매가</th> 
						<th>수량</th> 
						<th>합계</th>					
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td><input type="checkbox" name="check" value="${vo[0].buyNum }" onclick="check1('${vo[0].price}')"></td>
						<td>${vo[1] }</td> <!-- 이미지 -->
						<td>${vo[2] }</td> <!-- 상품정보 -->
						<td>${vo[3] }</td> <!-- 판매가 -->
						<td>${vo[0].orderAmount }</td> <!-- 수량 -->
						<td>${vo[0].price }</td> <!-- 합계 -->
					</tr>					
					</c:forEach>						
				</table>
				<input type="button" value="내보내기" onclick="delete1()">
				<br><br>
				
				결제예정금액<span id="totalPrice" name="totalPrice"></span>
				<input type="hidden" name="email" value="${email }">
				<input type="hidden" id="buyList" name="buyList">
				<input type="button" value="선택상품주문" onclick="getBuyList()">			
				<a href="">쇼핑계속하기</a>
			</form>
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
		}else{
			for(var i=1;i<chk.length;i++){
				chk[i].checked=false;				
			}
		}
	}
	function check1(price){
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==false){
				chk[0].checked=false;
			}				
		}	
		var totalPrice=document.getElementById("totalPrice");
		var totPrice=totalPrice.innerHTML;
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==true){
				totPrice=totPrice*1+price*1;				
			}else{
				totPrice=totPrice*1-price*1;
			}
		}	
		totalPrice.innerHTML=totPrice;
	}
	function getBuyList(){
		var cart=document.cart;
		var buyList=document.getElementById("buyList");
		var checkList="";
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==true){
				checkList+=chk[i].value+",";
			}
		}
		checkList=checkList.substring(0,checkList.lastIndexOf(","));//맨끝 콤마 지우기
		buyList.value=checkList;
		cart.submit();
	}
	function delete1(){
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
			location.href="/semi_project/jh/demand.do?checkList="+checkList+"&cmd=delete";
		}
	}
	

</script>
</html>
