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
			<h2>Review</h2>
			<c:if test="${flag==0}">
				<input type="button" value="삭제"
					onclick="delete1('${email}','${pageNum}')" class="btn_del">
				<table class="board_list">
					<colgroup>
						<col style="width: 5%">
						<col style="width: 10%">
						<col style="width: 15%">
						<col>
						<col style="width: 10%">
					</colgroup>
					<tr>
						<th><input type="checkbox" name="check" onclick="checkAll()"></th>
						<th></th>
						<th>작성자11</th>
						<th>글제목</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>
								<input type="checkbox" name="check" value="${vo.num }" onclick="check1()">
							</td>
							<td>
								<img src="/semi_project/itemFile/${vo.itemImg}" class="review_img">
							</td>
							<td>${vo.name }</td>
							<td>
								<a href="/semi_project/reviewBoard.do?cmd=detail&cmd1=det&num=${vo.num }&flag=${flag}&pageNum=${pageNum}">${vo.title }</a>
							</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination">
					<c:choose>
						<c:when test="${pageNum>10}">
							<a
								href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${startPage-1 }"
								class="prev">이전</a>
						</c:when>
						<c:otherwise>
							<a href="javascrit:;" class="prev">이전</a>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum==i }">
								<a
									href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${i }"
									class="on">${i }</a>
							</c:when>
							<c:otherwise>
								<a
									href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${endPage<pageCount}">
							<a
								href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${endPage+1 }"
								class="next">다음</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" class="next">다음</a>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>




			<c:if test="${flag!=0}">
				<table class="board_list">
					<tr>
						<th></th>
						<th>작성자</th>
						<th>글제목11</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td><img src="/semi_project/itemFile/${vo.itemImg}" style="display:block; width:100%;"></td>
							<td>${vo.name }</td>
							<td>${vo.title }</td>
							<td><a
								href="/semi_project/reviewBoard.do?cmd=detail&cmd1=det&email=${email }&flag=${flag}&num=${vo.num }&pageNum=${pageNum}">${vo.title }</a>
							</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination">
					<c:choose>
						<c:when test="${pageNum>10}">
							<a href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${startPage-1 }" class="prev">이전</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" class="prev">이전</a>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum==i }">
								<a href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${i }" class="on">${i }</a>
							</c:when>
							<c:otherwise>
								<a href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${endPage<pageCount}">
							<a href="/semi_project/reviewBoard.do?cmd=list&email=${email }&pageNum=${endPage+1 }" class="next">다음</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" class="next">다음</a>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			<div class="search_wrap">
				<form action="/semi_project/reviewBoard.do?cmd=list&email=${email }" method="post">
					<select name="search">
						<option value="name">작성자</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select> 
					<input type="text" name="keyword"> <input type="submit" value="검색">
				</form>
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
		console.log(checkList);
		if (confirm("삭제하시겠습니까?")) {
			location.href = "/semi_project/reviewBoard.do?checkList="
					+ checkList + "&cmd=delete&cmd2=board&email=" + email
					+ "&pageNum=" + pageNum;
		}
	}
</script>
</html>