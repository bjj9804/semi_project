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
	<div id="content">
		<div class="inner">
			<form action="/semi_project/eb/qnalist.do?cmd=update&cmd2=myshop&num=${vo.num }" method="post">
				<input type="hidden" name="num" value="${param.num }">
				<input type="hidden" name="grp" value="${param.grp }">
				<input type="hidden" name="lev" value="${param.lev }">
				<input type="hidden" name="step" value="${param.step }">
				<input type="hidden" name="name" value="${param.name }">
				<input type="hidden" name="email" value="${param.email }">			
				<table class="board_write">
					<colgroup>
						<col style="width:20%;">
						<col>
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${vo.title}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="100" name="content">${vo.content }</textarea>
						</td>
					</tr>
				</table>
				<div class="view_btn">
					<input type="submit" value="수정" class="btn_edit">
					<input type="reset" value="취소" class="btn_del">
				</div>				
			</form>	
		</div>
	</div>

	<ul>
		<li style="font-size: 50px">
			<a href="/semi_project/eb/qnalist.do?cmd=list" style="color: black">Q&A</a>
		</li>
		

	</ul>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>