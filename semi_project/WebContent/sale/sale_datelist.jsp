<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
	<script type="text/javascript">
		function addYear() {
			var year = document.getElementById("year");
			for(var i = 2018;i > 1918;i--){
				year.innerHTML = year.innerHTML + "<option value="+i+">"+i+"</option>";
			}
		}
		
		function addMonth() {
			var month = document.getElementById("month");
			for(var i = 1;i <= 12;i++){
				month.innerHTML = month.innerHTML + "<option value="+(i-1)+">"+i+"</option>";
			}
		}
		function selectYear() {
			var startyear = document.getElementById("startyear");
			var endyear = document.getElementById("endyear");
			for(var i = 2018;i > 1918;i--){
				startyear.innerHTML = startyear.innerHTML + "<option value="+i+">"+i+"</option>";
				endyear.innerHTML = endyear.innerHTML + "<option value="+i+">"+i+"</option>";
			}
		}
		
		function selectMonth() {
			var startmonth = document.getElementById("startmonth");
			var endmonth = document.getElementById("endmonth");
			for(var i = 1;i <= 12;i++){
				startmonth.innerHTML = startmonth.innerHTML + "<option value="+(i-1)+">"+i+"</option>";
				endmonth.innerHTML = endmonth.innerHTML + "<option value="+(i-1)+">"+i+"</option>";
			}
		}
		function selectday() {
			var startDay = document.getElementById("startDay");
			var endDay = document.getElementById("endDay");
			for(var i = 1;i <= 31;i++){
				startDay.innerHTML = startDay.innerHTML + "<option value="+i+">"+i+"</option>";
				endDay.innerHTML = endDay.innerHTML + "<option value="+i+">"+i+"</option>";
			}
		}
		function showDate() {
			var year = document.getElementById("year");
			var month = document.getElementById("month");
			var today = new Date();
			year.value=today.getFullYear();
			month.value=today.getMonth();
			
			var d = new Date(year.value,month.value,1,0,0,0,0);
			var days = ['일','월','화','수','목','금','토'];
			var lastdays = [31,28,31,30,31,30,31,31,30,31,30,31];
			if(month.value == 1){
				if((year.value%4 == 0 && year.value%100 != 0) || year.value%400 == 0){
					lastdays[1] = 29;
				}else{
					lastdays[1] = 28;
				}
			}
			var n = 1;
			outer:
			for(var i = 1;;){
				var tr = document.getElementById("tr" + n++);
				var tds = tr.childNodes;
				for(var j = 0;j < tds.length;j++){
					if(i > lastdays[d.getMonth()]) {
						tds[j].innerHTML = "";
						continue;
					}
					if(i == 1) {
						for(var k = 0;k < d.getDay();k++){
							tds[j++].innerHTML = "";
						}
					}
					tds[j].innerHTML = "<a href='#' onclick='fun1("+year.value+","+(Number(month.value)+1)+","+i+")'>"+(i++)+"</a>";
				}
				if(i > lastdays[d.getMonth()]) {
					for(;;){
						var tr = document.getElementById("tr" + n++);
						if(tr != null){
							var tds = tr.childNodes;
							for(var j = 0;j < tds.length;j++){
								tds[j].innerHTML = "";
							}
						}else{
							break outer;
						}
					}
				}
			}
		}
		
		function fun1(year,month,day){
			alert(""+year+month+day);
		}
		
		function selectlist(){
			var selectlistForm = document.getElementById("selectlistForm");
			selectlistForm.style.display = "block";
		}
	</script>
	<style>
		#selectlistForm{display: none;}
		#cal{display: none;}
		#dateList{margin-top: 20px; }
		#dateList li{display:inline-block;}
		.dd table{width:100%; text-align:center;}
		.dd table th{border:1px solid #ccc; padding:10px 0;}
		.dd table td{border:1px solid #ccc; padding:10px 0;}
		.dd{width: 100%; text-align: center; margin-top: 20px;}
	</style>
</head>
<body onload="addYear();addMonth();showDate();selectYear();selectMonth();selectday()">
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>날짜별 판매리스트</h2>
			<ul class="admin_menu">
				<li><a href="/semi_project/mh/sale.do?cmd=salelist">전체보기</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=userlist">회원별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=daylist">날짜별</a></li>
				<li><a href="/semi_project/mh/sale.do?cmd=itemlist">상품별</a></li>
			</ul>
			<div></div>
			<div id="cal">
				<select id="year" onchange="showDate()">
				</select>년
				<select id="month" onchange="showDate()">
				</select>월
				<br>
				<div style="background-color: #ccc;width: 200px;">
					<table id="htd">
						<tr>
							<td>일</td>
							<td>월</td>
							<td>화</td>
							<td>수</td>
							<td>목</td>
							<td>금</td>
							<td>토</td>
						</tr>
					</table>
				</div>
				<div style="background-color: #eee;width: 200px;">
					<table id="ctd">
						<tr id="tr1"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
						<tr id="tr2"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
						<tr id="tr3"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
						<tr id="tr4"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
						<tr id="tr5"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
						<tr id="tr6"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					</table>
				</div>
			</div>
			<div id="dateList">
				<ul>
					<li><a href="/semi_project/mh/sale.do?cmd=daylist">오늘 판매량</a></li> | 
					<li><a href="/semi_project/mh/sale.do?cmd=weeklist">이번주 판매량</a></li> | 
					<li><a href="/semi_project/mh/sale.do?cmd=monthlist">이번달 판매량</a></li> | 
					<li><a href="#" onclick="selectlist()">선택</a></li>
				</ul>
				<div id="selectlistForm">
					<form method="post" action="/semi_project/mh/sale.do?cmd=selectlist">
						시작날짜
						<select id="startyear" name="startyear">
						</select>년
						<select id="startmonth" name="startmonth">
						</select>월
						<select id="startDay" name="startDay">
						</select>월
						~
						<select id="endyear" name="endyear">
						</select>년
						<select id="endmonth" name="endmonth">
						</select>월
						<select id="endDay" name="endDay">
						</select>월
						<input type="submit" value="조회">
					</form>
				</div>
				<div class="dd">
				<table>
					<tr>
						<th>총판매액</th>
						<th>총판매수</th>
					</tr>
					<tr>
						<td>${vo.tot }</td>
						<td>${vo.cnt }</td>
					</tr>
				</table>
				<table>
					<tr>
						<th>회원이메일</th>
						<th>구매가격</th>
						<th>구매상태</th>
						<th>구매날짜</th>
					</tr>
					<c:forEach var="sale" items="${list }">
					<tr>
						<td>${sale.email }</td>
						<td>${sale.payMoney }</td>
						<td>${sale.state }</td>
						<td>${sale.orderDate }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>