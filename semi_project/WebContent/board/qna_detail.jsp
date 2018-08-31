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
	int pageNum =Integer.parseInt(request.getParameter("pageNum"));
	int flag=Integer.parseInt(flag1);
	
	session.setAttribute("email", email);
	session.setAttribute("flag", flag);

%>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>Q&amp;A</h2>
			<table class="board_view">
				<colgroup>
					<col style="width:20%">
					<col style="width:30%">
					<col style="width:20%">
					<col style="width:30%">
				</colgroup>
				<tr>
					<th>글번호</th>
					<td>${vo.num }</td>
					<th>작성자</th>
					<td>${vo.name }</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${vo.email }</td>
					<th>조회수</th>
					<td>${vo.hit }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${vo.title }</td>
					<th>작성날짜</th>
					<td>${vo.regdate }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<div class="table_con">
							${vo.content }
						</div>
					</td>
				</tr>
			</table>
			<div class="view_btn">
				<input type="button" value="목록" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=list&email=${email}'" class="btn_list">
				<c:if test="${email==vo.email || flag==0}">
					<input type="button" value="답글" onclick="javascript:location.href='../board/qna_insert.jsp?num=${vo.num}&grp=${vo.grp}&lev=${vo.lev}&step=${vo.step}'" class="btn_reply">
					<input type="button" value="삭제" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=delete&cmd2=del&num=${vo.num }&checkList=${vo.num }&cmd=delete&email=${email}&pageNum=<%=pageNum%>'" class="btn_del">
					<input type="button" value="수정" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=detail&cmd1=update&num=${vo.num }&grp=${vo.grp }&email=${email }&name=${vo.name }&flag=${flag }'" class="btn_edit">
				</c:if>
			</div>

		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>