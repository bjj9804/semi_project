<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/inc/header.jsp"/>
</head>
<body>
	<jsp:include page="/inc/gnb.jsp"/>
	<div id="content">
		<div class="inner">
			<h2>NOTICE BOARD</h2>
			<!-- 관리자가 들어왔을때 -->
			<c:if test="${param.flag==1}">
				<input type="button" value="삭제" onclick="delete1()">
				<table border="1" width="800">
					<tr><th><input type="checkbox" name="check" onclick="checkAll()"></th><th>글번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td><input type="checkbox" name="check" value="${vo.num }" onclick="check1()"></td>
							<td>${vo.num }</td>
							<td><a href="jh/notice.do?num=${vo.num }&cmd='detail'">${vo.title }</a></td>
							<td>관리자</td>
							<td>${vo.hit }</td>		
						</tr>
					</c:forEach>
				</table>
			
			
			</c:if>
			
			<!-- 일반인이 들어왔을때 -->
			<c:if test="${param.flag!=1}">
			<table border="1" width="800">
				<tr><th>글번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.num }</td>
						<td><a href="jh/notice.do?num=${vo.num }&cmd='detail'">${vo.title }</a></td>
						<td>관리자</td>
						<td>${vo.hit }</td>		
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</div>
	</div>
	<jsp:include page="/inc/footer.jsp"/>
</body>

	<script type="text/javascript">
	
	function checkAll(){
		var chk=document.getElementsByName("check");
		if(chk[0].checked==true){
			for(var i=1;i<chk.length;i++){
				chk[i].checked=true;				
			}			
		}else{
			for(var i=1;i<chk.length;i++){
				chk[i].checked=false;				
			}
		}
	}
	function check1(){
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==false){
				chk[0].checked=false;
			}				
		}		
	}
	function delete1(){
		var checkList="";
		var chk=document.getElementsByName("check");
		for(var i=1;i<chk.length;i++){
			if(chk[i].checked==true){
				checkList+=chk[i].value+",";
			}
		}
		checkList=checkList.substring(0,checkList.lastIndexOf(","));//맨끝 콤마 지우기
		
		if(checkList==''){
			alert("삭제할 대상을 선택하세요");
			return false;
		}
		console.log(checkList);
		if(confirm("삭제하시겠습니까?")){
			location.href="jh/notice.do?checkList="+checkList+"&cmd='delete'";
		}
		
	}
	</script>
</html>