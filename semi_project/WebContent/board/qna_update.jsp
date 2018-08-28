<%@page import="eb.QnaBoardDao"%>
<%@page import="eb.QnaBoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
	<jsp:include page="/inc/gnb.jsp" />
	<ul style="position: relative; margin-left: 500px; margin-top: 150px;">
		<li style="font-size: 50px"><a
			href="/semi_project/eb/qnalist.do?cmd=list" style="color: black">Q&A</a></li>
		<form action="/semi_project/eb/qnalist.do?cmd=update" method="post">
			<input type="hidden" name="num" value="${param.num }"> <input
				type="hidden" name="grp" value="${param.grp }"> <input
				type="hidden" name="lev" value="${param.lev }"> <input
				type="hidden" name="step" value="${param.step }"> <input
				type="hidden" name="name" value="${param.name }"> <input
				type="hidden" name="email" value="${param.email }"> 제목<input
				type="text" name="title" value="${vo.title}"><br> 내용 <br>
			<textarea rows="5" cols="100" name="content">${vo.content }</textarea>
			<br> <input type="submit" value="글 수정" style="margin-left: 30%">
			<input type="reset" value="취소">
			<!--<input type="button" value="Q&A 목록" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=list&email=${email}'" style="margin-left:5%">-->
			<br>
			<br>
		</form>
	</ul>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>