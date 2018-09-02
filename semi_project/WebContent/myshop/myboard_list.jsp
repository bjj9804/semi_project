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
			<h2>My Review</h2>
			<input type="button" value="삭제" onclick="delete1('${email}','${pageNum})" class="btn_del">
			<table class="board_list">
				<colgroup>
					<col style="width:5%;">
					<col style="width:10%;">
					<col style="width:15%;">
					<col>
					<col style="width:10%">
				</colgroup>
				<tr>
					<th><input type="checkbox" name="check" onclick="checkAll()"></th>
					<th>글번호</th>
					<th>작성자</th>
					<th>글제목</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td><input type="checkbox" name="check" value="${vo.num }"
							onclick="check()"></td>
						<td>${vo.num }</td>
						<td>${vo.name }</td>
						<td><a
							href="/semi_project/myshopBoard.do?cmd=reviewDetail&cmd1=detail&num=${vo.num }&pageNum=${pageNum}&pageNum1=${pageNum1}">${vo.title }</a>
						</td>
						<td>${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pagination">
				<c:choose>
					<c:when test="${pageNum>10}">
						<a href="/semi_project/myshopBoard?cmd=reviewList&email=${email }&pageNum=${startPage-1 }" class="prev">이전</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;" class="prev">이전</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum==i }">
							<a href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum=${i }" class="on">${i }</a>
						</c:when>
						<c:otherwise>
							<a href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum=${i }">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPage<pageCount}">
						<a href="/semi_project/myshopBoard.do?cmd=reviewList&email=${email }&pageNum=${endPage+1 }" class="next">다음</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;" class="next">다음</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="position:relative">
				<h2>My Q&amp;A</h2>
				<input type="button" value="삭제" onclick="delete2('${email}','${pageNum1}')" class="btn_del1">
				<table class="board_list">
					<colgroup>
						<col style="width:5%;">
						<col style="width:10%;">
						<col style="width:15%;">
						<col>
						<col style="width:10%">
					</colgroup>
					<tr>
						<th><input type="checkbox" name="check1" onclick="checkAll1()"></th>
						<th>글번호</th>
						<th>작성자</th>
						<th>글제목</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list1 }">
						<tr>
							<td><input type="checkbox" name="check1" value="${vo.num }"
								onclick="check1()"></td>
							<td>${vo.num }</td>
							<td>${vo.name }</td>
							<td><a
								href="/semi_project/myshopBoard.do?cmd=qnaDetail&grp=${vo.grp }&pageNum=${pageNum }&pageNum1=${pageNum1 }&email=${email }&num=${vo.num }&flag=${flag}">${vo.title }</a>
							</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination">
					<c:choose>
						<c:when test="${pageNum1>10}">
							<a href="/semi_project/myshopBoard?cmd=reviewList&email=${email }&pageNum1=${startPage1-1 }" class="prev">이전</a>
						</c:when>
						<c:otherwise>
							<a href="#" class="prev">이전</a>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startPage1 }" end="${endPage1 }">
						<c:choose>
							<c:when test="${pageNum1==i }">
								<a href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum1=${i }" class="on">${i }</a>
							</c:when>
							<c:otherwise>
								<a href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum1=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${endPage1<pageCount1}">
							<a href="/semi_project/myshopBoard.do?cmd=reviewList&email=${email }&pageNum1=${endPage1+1 }" class="next">다음</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" class="next">다음</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>
<script type="text/javascript">
	function checkAll() {
		var chk = document.getElementsByName("check");
		if (chk[0].checked == true) {
			for (var i = 1; i < chk.length; i++) {
				chk[i].checked = true;
			}
		} else {
			for (var i = 1; i < chk.length; i++) {
				chk[i].checked = false;
			}
		}
	}
	function checkAll1() {
		var chk = document.getElementsByName("check1");
		if (chk[0].checked == true) {
			for (var i = 1; i < chk.length; i++) {
				chk[i].checked = true;
			}
		} else {
			for (var i = 1; i < chk.length; i++) {
				chk[i].checked = false;
			}
		}
	}
	function check() {
		var chk = document.getElementsByName("check");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked == false) {
				chk[0].checked = false;
			}
		}
	}
	
	function check1() {
		var chk = document.getElementsByName("check1");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked == false) {
				chk[0].checked = false;
			}
		}
	}
	
	function delete1(email, pageNum) {
		console.log("함수 실행");
		var checkList = "";
		var chk = document.getElementsByName("check");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked == true) {
				checkList += chk[i].value + ",";
			}
		}
		checkList = checkList.substring(0, checkList.lastIndexOf(","));//맨끝 콤마 지우기

		if (checkList == '') {
			alert("삭제할 대상을 선택하세요");
			return false;
		}
		if (confirm("삭제하시겠습니까?")) {
			location.href = "/semi_project/reviewBoard.do?checkList=" + checkList
				+ "&cmd=delete&email=" + email + "&pageNum=" + pageNum;
		}
	}
	
	function delete2(email, pageNum1) {
		var checkList = "";
		var chk = document.getElementsByName("check1");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked == true) {
				checkList += chk[i].value + ",";
			}
		}
		checkList = checkList.substring(0, checkList.lastIndexOf(","));//맨끝 콤마 지우기

		if (checkList == '') {
			alert("삭제할 대상을 선택하세요");
			return false;
		}
		if (confirm("삭제하시겠습니까?")) {
			location.href = "/semi_project/eb/qnalist.do?checkList=" + checkList
			+ "&cmd=delete&cmd2=myshop&email=" + email + "&pageNum=" + pageNum1;	
		}
	}
</script>
</html>