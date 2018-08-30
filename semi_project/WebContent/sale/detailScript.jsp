<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
			<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<script type="text/javascript">
	function boardModify(bnum){
		location.href="<%=context%>/boardList.do?mod=getInfo&bnum="+bnum;
	}
	
	function boardDelete(bnum){
		location.href="<%=context%>/boardList.do?mod=delete&bnum="+bnum;
	}

</script>
<input type="text" id="sdate" name="sdate" value="${day}">