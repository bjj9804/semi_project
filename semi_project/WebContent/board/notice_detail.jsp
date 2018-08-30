<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>NOTICE</h2>
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
					<td>관리자</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${vo.hit }</td>
					<th>작성날짜</th>
					<td>${vo.regdate }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">
						<div class="table_con">
							${vo.title }
						</div>
					</td>
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
				<c:if test="${flag==0}">
					<a href="/semi_project/jh/notice.do?num=${vo.num }&cmd=detail&cmd1=det_update&flag=${flag}&pageNum=${pageNum}" class="btn_edit">수정</a>
					<a href="/semi_project/jh/notice.do?checkList=${vo.num }&cmd=delete&email=${email }&pageNum=${pageNum}" class="btn_del">삭제</a>			
				</c:if>
				<a href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${pageNum }" class="btn_list">목록</a>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>