<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<jsp:include page="/inc/header.jsp" />
</head>
<body>
	<jsp:include page="/inc/gnb.jsp" />
	<div id="content">
		<div class="inner">
		<h1>교환</h1>
			<table border="1" width=900px bordercolor="black">
				<form action="demand.do?cmd=buychange3" method="post" border="1" bordercolor="black">
				<tr>
					<td><input type="checkbox" class="chk" id="chk_all" name="chk"/></td>
					<td>교환할사이즈</td>
				</tr>
				
				<c:forEach var="vo" items="${list }" varStatus="status">
				<c:forEach var="v" items="${vo }" varStatus="status">
				<tr>
						<td><input type="checkbox" class="chk" name="chk" value="${v.isize }"/></td>
						<td>${v.isize }</td>
						
				</tr>
				</c:forEach>
				</c:forEach>
				<tr>
				<td colspan=3>
				<input type="button" value="교환신청" onclick="change(${buyNum })">
				</td>
				</tr>
				
				</form>
			</table>
		</div>
	</div>.
	
	<jsp:include page="/inc/footer.jsp" />
</body>

<script type="text/javascript">


//선택된 값 얻어오기
function change(buyNum) {
	console.log("함수실행");
		var isize = "S";
		var chk = document.getElementsByName("chk");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked) {
			isize = chk[i].value;
			}
		}
		if (chk == '') {
			alert("교환할 대상을 선택하세요");
			return false;
		}
		console.log(isize);
		if (confirm("교환하시겠습니까?")) {
			location.href = "/semi_project/demand.do?cmd=buychange3&isize="+isize+"&buyNum="+buyNum;
		}
	}

</script>
</html>