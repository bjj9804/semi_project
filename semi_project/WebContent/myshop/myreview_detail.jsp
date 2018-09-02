<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
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
				<tr>
					<th>사진</th>
					<td colspan="3">
						<div class="table_con">
							<img src="/semi_project/Upload/${vo.img}">
						</div>
					</td>
				</tr>
			</table>
			<div class="view_btn">
				<input type="button" value="목록" onclick="javascript:location.href='/semi_project/myshopBoard.do?cmd=reviewList&pageNum=${pageNum }&pageNum1=${pageNum }'" class="btn_list">
				<c:if test="${email==vo.email}">
					<a href="/semi_project/reviewBoard.do?num=${vo.num }&cmd=detail&cmd1=update&cmd2=myshop&flag=${flag}&pageNum=${pageNum}" class="btn_edit">수정</a>
					<a href="/semi_project/reviewBoard.do?checkList=${vo.num }&cmd=delete&cmd2=myshop&email=${email }&pageNum=${pageNum}" class="btn_del">삭제</a>						
				</c:if>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>