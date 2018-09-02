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
			</a>
		</div>
	
	</div>
	<div id="section2" class="section">
	</div>
	<div id="section3" class="section">
	</div>
	<jsp:include page="/inc/footer.jsp"/> 
</body>
</html>