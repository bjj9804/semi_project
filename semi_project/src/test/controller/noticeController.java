package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice.do")
public class noticeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String contextPath=getServletContext().getContextPath();
		if(cmd!=null && cmd.equals("list")) {
			listAll(request,response);
		}else if(cmd!=null && cmd.equals("detail")) {
			detail(request,response);
		}		
		
	}
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
