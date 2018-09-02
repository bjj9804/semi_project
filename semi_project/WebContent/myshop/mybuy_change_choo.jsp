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
				<form action="demand.do?cmd=buychange3" method="post" border="1"
					bordercolor="black">
					<tr>
						<td>교환할아이템명</td>
						<td>교환할사이즈</td>
					</tr>
					<%-- 
            <c:forEach var="vo1" items="${list2 }" varStatus="status">
         
            <c:forEach var="vo" items="${list }" varStatus="status">
            <c:forEach var="v1" items="${vo1 }" varStatus="status">
            
            <c:forEach var="v" items="${vo }" varStatus="status">
            <tr>
                  <c:choose>
                  <c:when test="${v1.code ==v.code }">
                  
                  <td><input type="checkbox" class="chk" name="chk" value="${v1.code },${v.isize }"/></td>
                  <td>${v1.itemName }</td>

                  <td>
                  <select name="size">
                  <c:forEach var="i" items="${vo }">
              
					    <option value="${i.isize }">${i.isize }</option>
				
					</c:forEach>
					</select>
					</td>
					
                  </c:when>
                  </c:choose>
            </tr>
            </c:forEach>
            </c:forEach>
            
            </c:forEach>
            </c:forEach>
            --%>
					<c:forEach var="vo1" items="${list2 }" varStatus="status">
						<!-- 아이템명 -->
						<c:forEach var="v1" items="${vo1 }" varStatus="status">
							<!-- 아이템 -->

							<c:forEach var="vo" items="${list }" varStatus="status">
								<!-- 사이즈 -->
								<c:set var="a" value="1" />
								<c:forEach var="v" items="${vo }" varStatus="status">
									<tr>
										<c:choose>
											<c:when test="${v1.code ==v.code and a==1}">

												
												<td>${v1.itemName }</td>

												<td><select name="size">
														<c:forEach var="i" items="${vo }">

															<option value="${i.isize },${v1.itemName }">${i.isize }</option>

														</c:forEach>
												</select></td>
												<c:set var="a" value="2" />
											</c:when>
											<c:otherwise>

											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>

							</c:forEach>

						</c:forEach>
					</c:forEach>
			<tr>

						<td colspan=2><input type="button" value="교환신청" onclick="change1('${buyNum }')"></td>

	
			</tr>
				</form>
			</table>
		</div>
	</div>
	

	<jsp:include page="/inc/footer.jsp" />
</body>

<script type="text/javascript">
	//선택된 사이즈값 , buyNum 얻어오기
	function change1(buyNum) {
		var sizeList = "";
		var buyList = "";
		var size = document.getElementsByName("size");
		for (var i = 0; i < size.length; i++) {
				sizeList += size[i].value + "/";
				buyList += buyNum[i].value + ",";
		}
		sizeList = sizeList.substring(0, sizeList.lastIndexOf("/"));//맨끝 / 지우기
		buyList = buyList.substring(0, buyList.lastIndexOf(","));//맨끝 / 지우기
		if (size == '') {
			alert("교환할 대상을 선택하세요");
			return false;
		}
		console.log(size);
		if (confirm("교환하시겠습니까?")) {
			location.href = "/semi_project/demand.do?cmd=buychange3&sizeList="
					+ sizeList + "&buyList=" + encodeURI(buyNum);
		}
	}
</script>
</html>