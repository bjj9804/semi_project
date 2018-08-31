<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>Q&amp;A</h2>
			<form action="/semi_project/eb/qnalist.do?cmd=insert&email=${email }" method="post">
				<input type="hidden" name="num" value="${param.num }">
				<input type="hidden" name="grp" value="${param.grp }">
				<input type="hidden" name="lev" value="${param.lev }">
				<input type="hidden" name="step" value="${param.step }">
				<input type="hidden" name="name" value="${param.name }">
				<input type="hidden" name="email" value="${param.email }">	
				<table class="board_write">
					<colgroup>
						<col style="width:20%">
						<col>
					</colgroup>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="100" name="content"></textarea>
						</td>
					</tr>
				</table>
				<div class="view_btn">
					<input type="submit" value="글쓰기" class="btn_write">
					<input type="button" value="목록" onclick="javascript:location.href='/semi_project/eb/qnalist.do?cmd=list&email=${email}'" class="btn_list">
				</div>		
			</form>	
		</div>
	</div>

	<jsp:include page="/inc/footer.jsp"/>
</body>
</html>