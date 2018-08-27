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
				<form action="" method="post" border="1" bordercolor="black">
				<tr>
					<td><input type="checkbox" class="chk" id="chk_all" name="chk"/></td>
					<td>주문상품명</td>
					<td>가격</td>
					<td>사이즈</td>
				</tr>
				
				<c:forEach var="vo" items="${itemlist }" varStatus="status">
				<tr>
						<td><input type="checkbox" class="chk" name="chk" value=${vo.itemName }/></td>
						<td>${vo.itemName }</td>
						<td>${vo.price }</td>
						<td>${list[status.index].isize }</td>
				</c:forEach>
				</tr>
				<tr>
				<td colspan=3>
				<input type="submit" value="교환신청" style="margin-left:30%">
				</td>
				</tr>
				</form>
			</table>
		</div>
	</div>
	토네이도는 롯리데아에 있는 아이크스림 이름이라가합니다.
	
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


</script>

</html>