<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div class="login_area">
		<ul>
			<c:choose>
				<c:when test="${empty sessionScope.email }">
					<li><a href="<c:url value='/mh/users.do?cmd=loginform'/>">Sign In</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value='/mh/users.do?cmd=logoutform'/>">Sign Out</a></li>
					<c:if test="${flag==1 }">
						<li><a href="/semi_project/myshop/myshop_index.jsp">Mypage</a></li>
					</c:if>
					<c:if test="${flag==0 }">
						<li><a href="/semi_project/admin/index.jsp">admin</a></li>
					</c:if>
				</c:otherwise>
			</c:choose>
			<li><a href="/semi_project/jh/demand.do?cmd=showCart&check=1&email=${email }">cart</a></li>
		</ul>
	</div>
	<div class="inner">
		<h1><a href="/semi_project/main/index.jsp"><img src="/semi_project/images/main/logo.png" alt="구찌"></a></h1>
		<ul class="gnb">
			<li class="sub">
				<a href="/semi_project/jh/product.do?cmd=lookList&cmd1=runway" class="">RUNWAY</a>
				<ul class="sub_menu sub1">
					<li><a href="/semi_project/jh/product.do?cmd=lookList&cmd1=women">Women Fall Winter 2018</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=lookList&cmd1=men">Men Fall Winter 2018</a></li>
				</ul>
			</li>
			<li class="sub">
				<a href="/semi_project/product/product_women.jsp">WOMEN</a>
				<ul class="sub_menu sub2">
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">top</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">pants</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">skirts</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">outer</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">dresses</a></li>
				</ul>
			</li>
			<li class="sub">
				<a href="/semi_project/product/product_men.jsp">MEN</a>
				<ul class="sub_menu sub3">
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">top</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">pants</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">skirts</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">outer</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">dresses</a></li>
				</ul>
			</li>
			<li class="sub">
				<a href="/semi_project/product/product_acc.jsp">ACC</a>
				<ul class="sub_menu sub3">
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">jewelry</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">sunglasses</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">bag</a></li>
					<li><a href="/semi_project/jh/product.do?cmd=showItem&mark=">shoes</a></li>
				</ul>
			</li>
			<li><a href="/semi_project/eb/qnalist.do?cmd=list&email=${email }">Q&amp;A</a></li>
			<li><a href="/semi_project/reviewBoard.do?cmd=list&email=${email }">Review</a></li>
			<li><a href="/semi_project/jh/notice.do?cmd=list&email=${email }">Notice</a></li>
		</ul>
	</div>
	<div class="sub_bg"></div>
</div>