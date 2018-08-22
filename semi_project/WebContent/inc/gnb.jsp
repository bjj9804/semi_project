<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div class="login_area">
		<ul>
			<c:choose>
				<c:when test="${empty sessionScope.email }">
					<li><a href="<c:url value='/mh/login.do?cmd=loginform'/>">Sign In</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value='/mh/login.do?cmd=logoutform'/>">Sign Out</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="#">Bag</a></li>
		</ul>
	</div>
	<div class="inner">
		<h1><a href="#">로고</a></h1>
		<ul class="gnb">
			<li class="sub">
				<a href="#" class="">RUNWAY</a>
				<ul class="sub_menu sub1">
					<li><a href="#">Women Fall Winter 2018</a></li>
					<li><a href="#">Men Fall Winter 2018</a></li>
				</ul>
			</li>
			<li class="sub">
				<a href="#">WOMEN</a>
				<ul class="sub_menu sub2">
					<li><a href="#">top</a></li>
					<li><a href="#">pants</a></li>
					<li><a href="#">skirts</a></li>
					<li><a href="#">outer</a></li>
					<li><a href="#">dresses</a></li>
				</ul>
			</li>
			<li class="sub">
				<a href="#">MEN</a>
				<ul class="sub_menu sub3">
					<li><a href="#">top</a></li>
					<li><a href="#">pants</a></li>
					<li><a href="#">skirts</a></li>
					<li><a href="#">outer</a></li>
					<li><a href="#">dresses</a></li>
				</ul>
			</li>
			<li class="sub">
				<a href="#">ACC</a>
				<ul class="sub_menu sub3">
					<li><a href="#">jewelry</a></li>
					<li><a href="#">sunglasses</a></li>
					<li><a href="#">bag</a></li>
					<li><a href="#">shoes</a></li>
				</ul>
			</li>
			<li><a href="/semi_project/eb/qnalist.do?cmd=list">Q&amp;A</a></li>
			<li><a href="/semi_project/reviewBoard.do?cmd=list">Review</a></li>
			<li><a href="/semi_project/jh/notice.do?cmd=list&email=${sessionScope.email }">Notice</a></li>
		</ul>
	</div>
	<div class="sub_bg"></div>
</div>