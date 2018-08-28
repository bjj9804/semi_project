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
		<h1>My Review</h1>
				<input type="button" value="삭제"
					onclick="delete1('${email}','${pageNum})">
				<table border="1" width="500">
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
								onclick="check1()"></td>
							<td>${vo.num }</td>
							<td>${vo.name }</td>
							<td><a
								href="/semi_project/myshopBoard.do?cmd=reviewDetail&num=${vo.num }&flag=${flag}">${vo.title }</a>
							</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div>
					<c:choose>
						<c:when test="${pageNum>10}">
							<a
								href="/semi_project/myshopBoard?cmd=reviewList&email=${email }&pageNum=${startPage-1 }">[이전]</a>
						</c:when>
						<c:otherwise>
			[이전]
		</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum==i }">
								<a
									href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum=${i }"><span
									style="color: red">[${i }]</span></a>
							</c:when>
							<c:otherwise>
								<a
									href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum=${i }"><span
									style="color: #555">[${i }]</span></a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${endPage<pageCount}">
							<a
								href="/semi_project/myshopBoard.do?cmd=reviewList&email=${email }&pageNum=${endPage+1 }">[다음]</a>
						</c:when>
						<c:otherwise>
			[다음]
		</c:otherwise>
					</c:choose>
				</div>
	<br><br>
	<br><br>
	<br><br>
	<h1>My Q&A</h1>
				<input type="button" value="삭제"
					onclick="delete1('${email}','${pageNum1})">
				<table border="1" width="500">
					<tr>
						<th><input type="checkbox" name="check" onclick="checkAll()"></th>
						<th>글번호</th>
						<th>작성자</th>
						<th>글제목</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list1 }">
						<tr>
							<td><input type="checkbox" name="check" value="${vo.num }"
								onclick="check1()"></td>
							<td>${vo.num }</td>
							<td>${vo.name }</td>
							<td><a
								href="/semi_project/myshopBoard.do?cmd=qnaDetail&num=${vo.num }&flag=${flag}">${vo.title }</a>
							</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div>
					<c:choose>
						<c:when test="${pageNum1>10}">
							<a
								href="/semi_project/myshopBoard?cmd=reviewList&email=${email }&pageNum1=${startPage1-1 }">[이전]</a>
						</c:when>
						<c:otherwise>
			[이전]
		</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startPage1 }" end="${endPage1 }">
						<c:choose>
							<c:when test="${pageNum1==i }">
								<a
									href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum1=${i }"><span
									style="color: red">[${i }]</span></a>
							</c:when>
							<c:otherwise>
								<a
									href="myshopBoard.do?cmd=reviewList&email=${email }&pageNum1=${i }"><span
									style="color: #555">[${i }]</span></a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${endPage1<pageCount1}">
							<a
								href="/semi_project/myshopBoard.do?cmd=reviewList&email=${email }&pageNum1=${endPage1+1 }">[다음]</a>
						</c:when>
						<c:otherwise>
			[다음]
		</c:otherwise>
					</c:choose>
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
	function check1() {
		var chk = document.getElementsByName("check");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked == false) {
				chk[0].checked = false;
			}
		}
	}
	function delete1(email, pageNum) {
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
		console.log(checkList);	a
		if (confirm("삭제하시겠습니까?")) {
			location.href = "/semi_project/reviewBoard.do?checkList=" + checkList
					+ "&cmd=delete&email=" + email + "&pageNum=" + pageNum;
		}
	}
</script>
</html>