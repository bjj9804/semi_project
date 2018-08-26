<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		function showDate() {
			var year = document.getElementById("year");
			var month = document.getElementById("month");
			var d = new Date(year.value,month.value,1,0,0,0,0);
			var days = ['일','월','화','수','목','금','토'];
			var lastdays = [31,28,31,30,31,30,31,31,30,31,30,31];
			if(month.value == 1){
				if((year.value%4 == 0 && year.value%100 != 0) || year%400 == 0){
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
					tds[j].innerHTML = "<a href='#'>"+(i++)+"</a>";
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
	</script>
</head>
<body onload="addYear();addMonth();showDate();">
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<div>
				<h2>판매 전체 리스트</h2>
				<ul class="admin_menu">
					<li><a href="/semi_project/mh/sale.do?cmd=salelist">전체보기</a></li>
					<li><a href="/semi_project/mh/sale.do?cmd=userlist">회원별</a></li>
					<li><a href="#">날짜별</a></li>
					<li><a href="#">상품별</a></li>
				</ul>
			</div>
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
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>