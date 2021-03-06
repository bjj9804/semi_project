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
			<h2>NOTICE</h2>		
			<form action="/semi_project/jh/notice.do?cmd=list&email=${email}" method="post">
				<div class="search_wrap">
					<select name="search">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="keyword" value="${param.keyword }">
					<input type="submit" value="검색">
				</div>
			</form>
			<c:if test="${flag==0}">
				<input type="button" value="삭제" onclick="delete1('${email}','${pageNum}')" class="btn_del">
				<table class="board_list">
					<colgroup>
						<col style="width:5%;">
						<col style="width:10%;">
						<col>
						<col style="width:20%;">
						<col style="width:10%;">
					</colgroup>
					<tr>
						<th><input type="checkbox" name="check" onclick="checkAll()"></th>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td><input type="checkbox" name="check" value="${vo.num }"
								onclick="check1()"></td>
							<td>${vo.num }</td>
							<td>
								<a href="/semi_project/jh/notice.do?num=${vo.num }&cmd=detail&cmd1=detail&flag=${flag}&pageNum=${pageNum}">${vo.title }</a>
							</td>
							<td>관리자</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btn_wrap">
					<input type="button" value="글작성" onclick="location.href='/semi_project/board/notice_insert.jsp'" class="btn_write">
				</div>
				<div class="pagination">
					<c:if test="${startPage!=1 }">
						<a
							href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${startPage-1 }&search=${param.search}&keyword=${param.keyword}" class="prev">이전</a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum==i }">
								<%--현재페이지인 경우 --%>
								<a href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}" class="on">
									${i }
								</a>
							</c:when>
							<c:otherwise>
								<a href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}">
									${i }
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${endPage!=pageCnt }">
						<a
							href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${endPage+1 }&search=${param.search}&keyword=${param.keyword}" class="next">다음</a>
					</c:if>
				</div>
			</c:if>

			<c:if test="${flag!=0}">
				<table class="board_list">
					<colgroup>
						<col style="width:10%;">
						<col style="width:20%;">
						<col>
						<col style="width:10%;">
					</colgroup>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>${vo.num }</td>
							<td><a
								href="/semi_project/jh/notice.do?num=${vo.num }&cmd=detail&cmd1=detail&flag=${flag}&pageNum=${pageNum}">${vo.title }</a></td>
							<td>관리자</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination">
					<c:if test="${startPage!=1 }">
						<a href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${startPage-1 }&search=${param.search}&keyword=${param.keyword}" class="prev">이전</a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum==i }">
								<%--현재페이지인 경우 --%>
								<a
									href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}" class="on">
									>${i }</a>
							</c:when>
							<c:otherwise>
								<a
									href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${i }&search=${param.search}&keyword=${param.keyword}">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${endPage!=pageCnt }">
						<a href="/semi_project/jh/notice.do?cmd=list&email=${email }&pageNum=${endPage+1 }&search=${param.search}&keyword=${param.keyword}" class="next">다음</a>
					</c:if>
				</div>
			</c:if>
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
		console.log(checkList);
		if (confirm("삭제하시겠습니까?")) {
			location.href = "/semi_project/jh/notice.do?checkList=" + checkList
					+ "&cmd=delete&email=" + email + "&pageNum=" + pageNum;
		}
	}
</script>
</html>