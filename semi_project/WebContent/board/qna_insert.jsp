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
			<form action="/semi_project/eb/qnalist.do" method="post">
			<input type="hidden" name="num" value="${param.num }">
			<input type="hidden" name="grp" value="${param.grp }">
			<input type="hidden" name="lev" value="${param.lev }">
			<input type="hidden" name="step" value="${param.step }">
			
			작성자<input type="text" name="name"><br>
			제목<input type="text" name="title"><br>
			내용 <br>
			<textarea rows="5" cols="50" name="content"></textarea><br>
			이메일<input type="text" name="email"><br>
			
			<input type="submit" value="글 등록" style="margin-left:30%">
			<input type="button" value="Q&A 목록" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=list'" style="margin-left:5%">
			<input type="hidden" name="cmd" value="insert">
			<br><br>
		</form>
		</ul>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>