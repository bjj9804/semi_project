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
				<form action="demand.do?cmd=buychange2" method="post" border="1" bordercolor="black">
				<tr>
					<td><input type="checkbox" class="chk" id="chk_all" name="chk"/></td>
					<td>주문상품명</td>
					<td>가격</td>
					<td>사이즈</td>
				</tr>
				
				<c:forEach var="vo" items="${itemlist }" varStatus="status">
				<tr>
						<td><input type="checkbox" class="chk" name="chk" value="${list[status.index].buyNum }"/></td>
						<td>${vo.itemName }</td>
						<td>${vo.price }</td>
						<td>${list[status.index].isize }</td>
				
				</tr>
				</c:forEach>
				<tr>
				
				<td colspan=3>
				<input type="button" value="교환신청" onclick="change(${orderNum })">
				</td>
				</tr>
				</form>
			</table>
		</div>
	</div>.
	
	<jsp:include page="/inc/footer.jsp" />
</body>

<script type="text/javascript">
//체크박스 전체 선택 전체 해제
$("#chk_all").click(function(){
	if($("#chk_all").is(":checked")){
		$(".chk").prop("checked",true);
	}else{
		$(".chk").prop("checked",false);
	}
});

//한개의 체크박스 선택해제시 전체도 해제
$(".chk").click(function(){
	if($("input[name='chk']:checked").length == 3){
		$("#chk_all").prop("checked",true);
	}else{
		$("#chk_all").prop("checked",false);
	}	
});

//선택된 값 얻어오기
function change(orderNum) {
	console.log("함수실행");
		var checkList = "";
		var chk = document.getElementsByName("chk");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked) {
				checkList += chk[i].value + ",";
			}
		}
		checkList = checkList.substring(0, checkList.lastIndexOf(","));//맨끝 콤마 지우기
		console.log(checkList+"123");
		if (checkList == '') {
			alert("교환할 대상을 선택하세요");
			return false;
		}
		if (confirm("교환하시겠습니까?")) {
			location.href = "/semi_project/demand.do?checkList="+checkList+"&cmd=buychange2&orderNum="+orderNum;
		}
	}

</script>

</html>