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
			<form action="/semi_project/jh/demand.do?cmd=orderForm" method="post">
			
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
						<td><input type="checkbox" name="check" value="${vo.buyNum }" onclick="check1()"></td>
						<td>이미지</td>
						<td>상품정보</td>
						<td>판매가</td>
						<td>${vo.orderAmount }</td>
						<td>${vo.price }</td>
					</tr>
					</c:forEach>	
				</table>
				<input type="button" value="내보내기" onclick="delete1()">
				<br><br>
				
				
				<table border="1" width="800" align="center">
					<tr><th><h4>결제예정금액<h4></th><td>결제예정금액</td></tr>
				</table><br>
				
				<input type="submit" value="선택상품주문" >			
				<input type="submit" value="전체상품주문" >
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
	function check1(){
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==false){
				chk[0].checked=false;
			}				
		}		
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
