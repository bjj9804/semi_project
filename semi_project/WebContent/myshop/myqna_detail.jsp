<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
<%
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	int pageNum1 = Integer.parseInt(request.getParameter("pageNum1"));
%>
	<jsp:include page="/inc/gnb.jsp" />
	<div id="content">
		<div class="inner">
			<table class="board_view">
				<colgroup>
					<col style="width:20%;">
					<col style="width:30%;">
					<col style="width:20%;">
					<col style="width:30%;">
				</colgroup>
				<tr>
					<th>글번호</th>
					<td>${vo1.num }</td>
					<th>작성자</th>
					<td>${vo1.name }</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${vo1.email }</td>
					<th>조회수</th>
					<td>${vo1.hit }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${vo1.title }</td>
					<th>작성날짜</th>
					<td>${vo1.regdate }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<div class="table_con">
							${vo1.content }
						</div>
					</td>
				</tr>
			</table>
			
			<div class="view_btn">
				<input type="button" value="목록" onclick="javascript:location.href='/semi_project/myshopBoard.do?cmd=reviewList&email=${email }'" class="btn_list">
				<c:if test="${email==vo1.email || flag==0 }">
					<input type="button" value="수정" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=detail&cmd1=update&cmd2=myshop&num=${vo1.num }&grp=${vo1.grp }&email=${email }&name=${vo1.name }&flag=${flag }'" class="btn_edit">
					<input type="button" value="삭제" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=delete&cmd2=myshop&num=${vo1.num }&checkList=${vo1.num }&cmd=delete&email=${email}&pageNum=<%=pageNum %>&pageNum1=<%=pageNum1%>'" class="btn_del">
				</c:if>
			</div>
			
			<c:forEach var="vo" items="${replist }">
				<table class="board_view">
					<colgroup>
						<col style="width:20%;">
						<col style="width:30%;">
						<col style="width:20%;">
						<col style="width:30%;">
					</colgroup>
					<tr>
						<th>글번호</th>
						<td>${vo.num }</td>
						<th>작성자</th>
						<td>${vo.name }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${vo.hit }</td>
						<th>작성날짜</th>
						<td>${vo.regdate }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">${vo.title }</td>
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
			</c:forEach>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>