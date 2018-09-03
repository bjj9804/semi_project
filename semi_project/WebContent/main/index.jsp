<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<%
		Cookie[] cooks = request.getCookies();
		System.out.println(cooks);
		if(cooks != null){
			for(Cookie cook : cooks){
		System.out.println(cook.getName()+","+cook.getValue());
				if(cook.getName().equals("email")){
					session.setAttribute("email", cook.getValue());
				}
			}
		}
	%>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="title" class="section">
		<video autoplay	loop id="myVideo">
			<source	src="../images/video/main.mp4" type="video/mp4">
		</video>
		<p class="main_tit">PABAL FALL/WINTER 2018</p> 
	</div>
	<div id="section1" class="section"> 
		<div class="main_content">
			<a href="#">
				<img src="../images/main/look_1.jpg">
				<span class="sub_tit">레디 투 웨어</span>
				<span class="tit">Look No.12</span>
			</a>
		</div>
	</div>
	<div id="section2" class="section">
		<ul>
			<li>
				<a href="/semi_project/jh/product.do?cmd=showItem&mark=bg">
					<img src="../images/main/item1.jpg">
					<span class="sub_tit">BAG</span>
					<span class="tit">The Performers: Hiroshi Ishiguro </span>
				</a>
			</li>
			<li>
				<a href="/semi_project/jh/product.do?cmd=showItem&mark=ac">
					<img src="../images/main/item2.jpg">
					<span class="sub_tit">ACCESSORIES</span>
					<span class="tit">Engraved leaf and snake motif with Interlocking G</span>
				</a>
			</li>
			<li>
				<a href="/semi_project/jh/product.do?cmd=showItem&mark=wskirts">
					<img src="../images/main/item3.jpg">
					<span class="sub_tit">SKIRTS</span>
					<span class="tit">Blue Boudoir print silk twill</span>
				</a>
			</li>
		</ul>
	</div>
	<jsp:include page="/inc/footer.jsp"/> 
</body>
</html>