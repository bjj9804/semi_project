<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style type="text/css">
		table{width:100%; text-align:center;}
		table th{border:1px solid #ccc; padding:10px 0;}
		table td{border:1px solid #ccc; padding:10px 0;}
		#content .inner a{display:block; width:100px; height:30px; line-height:30px; background-color:#222; color:#fff; text-align:center; margin:20px auto}
	</style>
</head>
<script type="text/javascript">

	function write1(event){
		var t=event.target;
		t.readOnly=false;
	}
	var xhr=null;
	function amountUpdate(code,isize){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=updateOk;
		xhr.open('post','/semi_project/jh/item.do?cmd=amountUpdate',true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var amount=document.getElementById("amount").value;
		var param="code="+code+"&isize="+isize+"&amount="+amount;
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
			var aa=document.getElementsByName("amount");
			for(var i=0;i<aa.length;i++){
				if(aa[i].readOnly==false){
					aa[i].readOnly=true;
				}
			}
		}
	}
	</script>
	



<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<a href="/semi_project/jh/item.do?cmd=lookCode">상품등록</a>
			
			<table align="center">
				<tr>
					<th>상품코드</th>
					<th>가격</th>
					<th>상품명</th>
					<th>상품설명</th>
					<th>아이템사이즈</th>
					<th>수량</th>
					<th>수정</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td><a href="/semi_project/jh/item.do?cmd=itemDetail&code=${vo.code }">${vo.code }</a></td>
					<td>${vo.price }</td>
					<td>${vo.itemName }</td>
					<td>${vo.description }</td>
					<td>${vo.isize }</td>
					<td><input type="text" id="amount" name="amount" value="${vo.amount }" readonly="readonly" ondblclick="write1(event)"></td>
					<td><input type="button" value="수량변경" onclick="amountUpdate('${vo.code}','${vo.isize }')"></td>
				</tr>
				</c:forEach>
			</table>
			<br>
			<h2>[ LOOK INFO ]</h2>
			<table align="center">
				<tr>
				<c:forEach var="vo" items="${list1 }">
					<td><a href="/semi_project/jh/item.do?cmd=lookDetail&lookCode=${vo.lookCode }">${vo.lookCode }</a></td>
				</c:forEach>
				</tr>
			</table>

			
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>