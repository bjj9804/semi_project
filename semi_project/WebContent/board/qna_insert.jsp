<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
		<ul style="position:relative; margin-left:500px; margin-top:150px;">
			<li style="font-size: 50px" ><a href="/semi_project/eb/qnalist.do?cmd=list" style="color:black">Q&A</a></li>
			<form action="/semi_project/eb/qnalist.do?cmd=insert&email=${email }" method="post">
			<input type="hidden" name="num" value="${param.num }">
			<input type="hidden" name="grp" value="${param.grp }">
			<input type="hidden" name="lev" value="${param.lev }">
			<input type="hidden" name="step" value="${param.step }">
			<input type="hidden" name="name" value="${param.name }">
			<input type="hidden" name="email" value="${param.email }">
			
			제목<input type="text" name="title"><br>
			내용 <br>
			<textarea rows="5" cols="100" name="content"></textarea><br>
			
			<input type="submit" value="글 등록" style="margin-left:30%">
			<input type="button" value="Q&A 목록" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=list&email=${email}'" style="margin-left:5%">
			<!-- <input type="hidden" name="cmd" value="insert">
			<input type="hidden" name="email" value="${email }"> -->
			<br><br>
		</form>
		</ul>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>