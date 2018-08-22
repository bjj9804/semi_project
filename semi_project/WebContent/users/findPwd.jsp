<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="semi.db.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String password = null;
	boolean find = false;
	try{
		con = DBConnection.getConnection();
		String sql = "select password from users where email=? and phone=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, phone);
		rs = pstmt.executeQuery();
		if(rs.next()){
			password = rs.getString("password");
			find = true;
		}
	}catch(SQLException se) {
		System.out.println(se.getMessage());
	}catch(ClassNotFoundException clfe) {
		clfe.printStackTrace();
	} catch (NamingException e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	response.setContentType("text/plain;charset=utf-8");
	if (find == true){
%>
	<h1>회원님의 비밀번호는 <%=password %></h1>
<%
	}else{
%>
	<h1>조회된 정보가 없습니다</h1>
	<a href="pwdfind.jsp">비밀번호 다시찾기</a><br>
<%
	}
%>