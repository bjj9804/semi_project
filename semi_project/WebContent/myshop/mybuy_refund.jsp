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
			<table border="1" width=900px bordercolor="black">
				<h1>반품</h1>
				<form action="demand.do?cmd=insertrefund" method="post" border="1" bordercolor="black">
				<tr>
					<td><input type="checkbox" class="chk" id="chk_all" name="chk"/></td>
					<td>주문상품정보</td>
					<td>가격</td>
					<td>사이즈</td>
					<td>반품사유</td>
				</tr>
				<c:forEach var="vo" items="${itemlist }" varStatus="status">
				<tr>
						<td><input type="checkbox" class="chk" name="chk" value=${list[status.index].buyNum }/></td>
						<td>${vo.itemName }</td>
						<td>${vo.price }</td>
						<td>${list[status.index].isize }</td>
				<td>
				<select name="reason">
					<option value="">반품사유</option>
					<option value="상품불량">상품불량</option>
					<option value="단순변심">단순변심</option>
					<option value="주머니사정">주머니사정</option>
					<option value="기타">기타</option>
				</select>
				</td>
				</c:forEach>
				</tr>
				<tr>
				<td colspan=3>
				<input type="submit" value="반품신청" onclick="change('${list[status.index].buyNum}')">
				</td>
				</tr>
				</form>
			</table>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp" />
</body>

<script type="text/javascript">
//체크박스 전체 선택 전체 해제
$("#chk_all").click(function(){
	if($("#chk_all").is(":checked")){
		$(".chk").prop("checked",true);
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
function change(buyNum) {
		var checkList = "";
		var reasonList = "";
		var chk = document.getElementsByName("check");
		var rs = document.getElementsByName("reason");
		for (var i = 1; i < chk.length; i++) {
			if (chk[i].checked == true) {
				checkList += chk[i].value + ",";
				reasonList += rs[i].value + ",";
			}
		}
		checkList = checkList.substring(0, checkList.lastIndexOf(","));//맨끝 콤마 지우기
		reasonList = reasonList.substring(0, reasonList.lastIndexOf(","));//맨끝 콤마 지우기

		if (checkList == '') {
			alert("반품할 대상을 선택하세요");
			return false;
		}
		console.log(checkList);
		if (confirm("반품하시겠습니까?")) {
			location.href = "/semi_project/demand.do?checkList=" + checkList
					+ "&cmd=buychange2&buyNum=" + buyNu
					m +"&reasonList="+reasonList;
		}
	}


</script>
</html>