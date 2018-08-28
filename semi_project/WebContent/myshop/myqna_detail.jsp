<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
<%
	String email=request.getParameter("email");
	String flag1=request.getParameter("flag");
	int flag=Integer.parseInt(flag1);
	
	session.setAttribute("email", email);
	session.setAttribute("flag", flag);

%>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<table>
				<tr><th>글번호</th><td>${vo1.num }</td></tr>
				<tr><th>작성자</th><td>${vo1.name }</td></tr>
				<tr><th>이메일</th><td>${vo1.email }</td></tr>
				<tr><th>제목</th><td>${vo1.title }</td></tr>
				<tr><th>내용</th><td>${vo1.content }</td></tr>
				<tr><th>조회수</th><td>${vo1.hit }</td></tr>
				<tr><th>작성날짜</th><td>${vo1.regdate }</td></tr>
			</table>
			<input type="button" value="Q&A 목록" onclick="javascript:location.href='/semi_project/myshopBoard.do?cmd=reviewList&email=${email }'" style="margin-left:5%">



	<c:if test="${email==vo.email || flag==0 }">
			<input type="button" value="글 삭제" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=delete&num=${vo.num }'">
			<input type="button" value="글 수정" onclick="javascript:location.href='../board/qna_update.jsp?num=${vo.num }'">
	</c:if>

		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>