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
						<td><input type="checkbox" class="chk" name="chk" value=${vo.itemName }/></td>
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
				<input type="submit" value="반품신청" style="margin-left:30%">
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


</script>
</html>