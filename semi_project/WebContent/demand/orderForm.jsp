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
		
			<h1>ORDER FORM</h1><br>			
			<form action="/semi_project/jh/demand.do?cmd=order" method="post">
			<h3>주문내역</h3>				
				<table border="1" width="800" align="center">
					<tr><th>이미지</th><th>상품정보</th><th>판매가</th><th>수량</th><th>합계</th></tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo[1] }</td>
						<td>${vo[2] }</td>
						<td>${vo[3] }</td>
						<td>${vo[0].orderAmount }</td>
						<td>${vo[0].price }</td>
					</tr>
					</c:forEach>	
				</table>
				상품주문금액 : ${totalPrice }
				<br><br>
				
			<h3>배송정보</h3>					
				<table border="1" width="800" align="center">
					<tr><th>배송지 선택</th>
						<td><input type="radio" name="root" checked="checked" onclick="root1(1,'${usersvo.addr }','${usersvo.phone }','${usersvo.email }')">회원정보와 동일
							<input type="radio" name="root" onclick="root1(0)">새로운 배송지</td></tr>
					<tr><th>주소</th><td><input type="text" id="addr" name="addr" value="${usersvo.addr }" readonly="readonly"></td></tr>
					<tr><th>전화번호</th><td><input type="text" id="phone" name="phone" value="${usersvo.phone }" readonly="readonly"></td></tr>
					<tr><th>이메일</th><td><input type="text" id="email" name="email" value="${email }" readonly="readonly"></td></tr>
					<!-- <tr><th>배송메시지</th><td>배송메시지</td></tr> -->
				</table>
				<br>
				
			<h3>결제예정금액</h3>	
				<table border="1" width="800" align="center">
					<tr><th>총 주문금액</th><th>할인 금액</th><th>총 결제예정금액</th></tr>
					<tr><td>${totalPrice }</td><div id="sale"></div><td><div id="payMoney"></div></td></tr>
				</table><br>				
				<div>쿠폰사용
					<select name="coupon" onchange="couponCheck(${totalPrice },'${vo.couponName }')">
						<option value="0">사용하지 않음</option>
						<c:forEach var="vo" items="${cvo }">
						<option value="">${vo.couponName }</option>
						</c:forEach>				
					</select></div>
				<br>
				
			<h3>결제방법</h3>	
				<div>
					<input type="radio" name="method" value="신용카드">신용카드
					<input type="radio" name="method" value="무통장입금">무통장입금
					<input type="radio" name="method" value="실시간 계좌이체">실시간 계좌이체
					<input type="radio" name="method" value="휴대폰 결제">휴대폰 결제
				</div>
				<div>최종결제금액</div>
				
			<input type="hidden" name="buyList" value="${buyList }">
			<input type="hidden" name="totalPrice" value="${totalPrice }">			
			<input type="submit" value="결제하기">
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
<script type="text/javascript">
	function couponCheck(totalPrice,couponName){		
		var percent=couponName.indexOf("(","%");
		var coupon=document.getElementsByName("coupon")[0];
		if(coupon.value!=0){
			coupon.value=percent;
		}
		var sale=document.getElementById("sale");
		var payMoney=document.getElementById("payMoney");
		sale.innerHTML=totalPrice*coupon.value;
		payMoney.innerHTML=totalPrice-totalPrice*coupon.value;
		
	}
	function root1(num,a,p,e){
		var root=document.getElementsByName("root");
		var addr=document.getElementById("addr");
		var phone=document.getElementById("phone");
		var email=document.getElementById("email");
		if(num==1){
			addr.value=a;
			phone.value=p;
			email.value=e;
			addr.readOnly=true;
			phone.readOnly=true;
			email.readOnly=true;
		}else{
			addr.value="";
			phone.value="";
			email.value="";
			addr.readOnly=false;
			phone.readOnly=false;
			email.readOnly=false;
		}
	}
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
	function check1(){
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==false){
				chk[0].checked=false;
			}				
		}		
	}
	function delete1(email,pageNum){
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
			location.href="/semi_project/jh/notice.do?checkList="+checkList+"&cmd=delete&email="+email+"&pageNum="+pageNum;
		}
	}
	

</script>
</html>



				