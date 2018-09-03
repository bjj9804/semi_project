<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<style>
		#searchlist > div{
		    border: 1px solid #ccc;
		    padding: 20px;
		    background: #fff;
		    margin: 15px 0;		
		}
		#searchlist div img{display:inline-block; vertical-align:middle; width: 150px;}
		#searchlist div div{display:inline-block; vertical-align:middle; padding-left:20px;}
	</style>
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
	
	var xhr1 = null;
	function getList() {
		//var height = document.height;
		//var weight = document.weight;
		//var frm1 = document.frm1;
		//frm1.submit();
		var height = document.getElementById("height").value;
		var weight = document.getElementById("weight").value;
		var code = document.getElementById("code").value;
		xhr1 = new XMLHttpRequest();
		xhr1.onreadystatechange = listOk;
		xhr1.open('post', 'product.do?cmd=itemDetailSearch', true);
		xhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		console.log(height + " " + weight + " " + code);
		var param = "height=" + height + "&weight=" + weight + "&code=" + code;
		//post요청시 파라미터를 send메소드를 통해 보낸다
		xhr1.send(param);
		//xhr1.open('get', '/semi_project/jh/product.do?cmd=itemDetailSearch&height='+height + '&weight='+ weight+'&code='+code, true);
		//xhr1.send();
	}
	
	function listOk() {
		if(xhr1.readyState==4 && xhr1.status==200){
			removeAll();
			var txt = xhr1.responseText;
			var json = JSON.parse(txt);
			var searchlist = document.getElementById("searchlist");
			for(var i=0;i<json.length;i++){
				var mm  = json[i];
				var div = document.createElement("div");
				var html =  "<img src='/semi_project/Upload/"+mm.img+"'>" + "<div><p>작성자:  " + mm.name +"</p> <p>제목: " +mm.title
							+ "</p> <p>내용:" + mm.content + "</p></div>";
			    div.innerHTML = html;
				searchlist.appendChild(div);
			}
		}
	}
	function removeAll() { 
		var searchlist = document.getElementById("searchlist");
		var nodes = searchlist.childNodes;
		for(var i=nodes.length-1;i>=0;i--){
			var child = nodes.item(i);
			searchlist.removeChild(child);
		}
	}

</script>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
		<%String sessionE=(String)session.getAttribute("email"); %>
		<form name="frm" action="/semi_project/jh/demand.do?cmd=cart" method="post">
			<table class="board_list">
				<colgroup>
					<col style="width:20%">
					<col style="width:10%">
					<col>
					<col style="width:10%">
					<col style="width:10%">
				</colgroup>
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
				<img src="/semi_project/itemFile/${imgvo.imgScr }" style="width:500px;">	<br>	
			</c:forEach>
			
			
			<input type="hidden" name="code" value="${item.code }">
			<input type="hidden" name="email" value="${email }">
			<br><input type="button" value="상품담기" onclick="cart()">
		</form>
				Height <select id="height">
					<option value="0">Height</option>
					<option value="140">~140</option>
					<option value=150>140~150</option>
					<option value=160>150~160</option>
					<option value=170>160~170</option>
					<option value=180>170~180</option>
					<option value=190>180~190</option>
					<option value=200>190~200</option>
					<option value=210>200~</option>
				</select> &nbsp; &nbsp; Weight <select id="weight">
					<option value="0">Weight</option>
					<option value="40">~40kg</option>
					<option value=50>40kg~50kg</option>
					<option value=60>50kg~60kg</option>
					<option value=70>60kg~70kg</option>
					<option value=80>70kg~80kg</option>
					<option value=90>80kg~90kg</option>
					<option value=100>90kg~</option>
				</select> 
				<input type="hidden" id="code" value="${item.code }">
				<input type="button" value="리뷰보기" onclick="getList()">
				<br>
		<div id="searchlist"></div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>