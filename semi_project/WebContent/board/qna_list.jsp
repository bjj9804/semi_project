<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<c:if test="${flag==0}">
				<input type="button" value="삭제" onclick="delete1('${email}', '${pageNum }')" class="btn_del">
			</c:if>
			<table class="board_list">
				<colgroup>
					<col style="width:10%">
					<col style="width:10%">
					<col style="width:40%">
					<col style="width:30%">
					<col style="width:10%">
				</colgroup>
				<tr>
				<c:if test="${flag==0}">	
				<%-- 	<input type="button" value="삭제" onclick="delete1('${email}', '${pageNum }')"> --%>
					<th><input type="checkbox" name="check" onclick="checkAll()"></th>
				</c:if>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>
			
				<c:forEach var="vo" items="${list }">
					<tr>
						<c:if test="${flag==0}">
						<td><input type="checkbox" name="check" value="${vo.num }"
								onclick="check1()"></td>
						</c:if>
						<td>${vo.num }</td>
						
						<td>
						<c:if test="${vo.lev>0 }">
							<c:forEach var="i" begin="1" end="${vo.lev }">
									&nbsp;&nbsp;
							</c:forEach>
							└
						</c:if> <a href="qnalist.do?cmd=detail&cmd1=det&num=${vo.num }&grp=${vo.grp }&email=${originalEmail }&writer=${vo.email}&flag=${flag}&pageNum=${pageNum}">${vo.title }</a>
						</td>
						<td>${vo.name }</td>
						<td>${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="pagination">
				<c:choose>
					<c:when test="${startPage>10 }">
						<a href="qnalist.do?cmd=list&pageNum=${startPage-1 }&email=${originalEmail}" class="prev">
							이전
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;" class="prev">이전</a>
					</c:otherwise>
				</c:choose>
		
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum==i }">
							<a href="qnalist.do?cmd=list&pageNum=${i }&email=${originalEmail}" class="on">
								${i }
							</a>
						</c:when>
						<c:otherwise>
							<a href="qnalist.do?cmd=list&pageNum=${i }&email=${originalEmail}">
								${i }
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		
				<c:choose>
					<c:when test="${endPage<pageCount }">
						<a href="qnalist.do?cmd=list&pageNum=${endPage+1 }&email=${originalEmail}" class="next">다음</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;" class="next">다음</a>
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="${not empty originalEmail}">
				<input type="button" value="글쓰기" onclick="javascript:location.href='../board/qna_insert.jsp'" class="btn_write">
			</c:if>
		</div>

	</div>
	<jsp:include page="/inc/footer.jsp"/>
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
		location.href = "/semi_project/eb/qnalist.do?checkList=" + checkList + "&cmd=delete&cmd2=board&email=" + email + "&pageNum=" + pageNum;
	}
</script>
</html>
