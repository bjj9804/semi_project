<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul style="position:relative; margin-left:500px; margin-top:150px;">
	<li style="font-size: 50px" ><a href="qnalist.do" style="color:black">Q&A</a></li>
	<form action="/semi_project/qnalist.do?cmd=insert" method="post">
	<input type="hidden" name="num" value="${param.num }">
	<input type="hidden" name="grp" value="${param.grp }">
	<input type="hidden" name="lev" value="${param.lev }">
	<input type="hidden" name="step" value="${param.step }">
	
	작성자<input type="text" name="name"><br>
	제목<input type="text" name="title"><br>
	내용 <br>
	<textarea rows="5" cols="50" name="content"></textarea><br>
	이메일<input type="text" name="email"><br>
	
	<input type="submit" value="등록">
</form>
</ul>
</body>
</html>