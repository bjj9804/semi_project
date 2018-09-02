<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
	<jsp:include page="/inc/gnb.jsp" />
	<div id="content">
		<div class="inner">
			<form action="/semi_project/reviewBoard.do?cmd=update&cmd2=myshop&email=${email }&num=${vo.num}" method="post" enctype="multipart/form-data">
				<table class="board_write">
					<colgroup>
						<col style="width:20%">
						<col style="width:30%">
						<col style="width:20%">
						<col style="width:30%">
					</colgroup>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text" name="title" value="${vo.title }"></td>
					</tr>
					<tr>
						<th>height</th>
						<td>
							<select name="height">
								<option value="0">입력안함</option>
								<option value="140">~140</option>
								<option value=150>140~150</option>
								<option value=160>150~160</option>
								<option value=170>160~170</option>
								<option value=180>170~180</option>
								<option value=190>180~190</option>
								<option value=200>190~200</option>
								<option value=210>200~</option>
							</select> 						
						</td>
						<th>weight</th>
						<td>
							<select name="weight">
								<option value="0">입력안함</option>
								<option value="40">~40kg</option>
								<option value=50>40kg~50kg</option>
								<option value=60>50kg~60kg</option>
								<option value=70>60kg~70kg</option>
								<option value=80>70kg~80kg</option>
								<option value=90>80kg~90kg</option>
								<option value=100>90kg~</option>
							</select>						
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea rows="5" cols="50" name="content">${vo.content }</textarea></td>
					</tr>
					<tr>
						<th>리뷰사진</th>
						<td colspan="3">
							<div class="table_con">
								<input type="file" name="img" value="${vo.img }">
							</div>
						</td>
					</tr>
				</table>
				<div class="view_btn">
					<input type="submit" value="작성" class="btn_del">
					<input type="reset" value="취소" class="btn_edit">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>