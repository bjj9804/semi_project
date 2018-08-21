package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ReviewBoardVo;


@WebServlet("/reviewBoard.do")
public class ReviewBoardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd!=null && cmd.equals("insert")) {
			insert(request, response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request, response);
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(request, response);
		}
	}
	
	private void insert(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String comments = request.getParameter("comments");
		ReviewBoardVo vo = new CommentsVo(0, id, comments);
		CommentsDao dao = CommentsDao.getInstance();
		int n = dao.insert(vo);
		//2. 결과를 XML로 응답하기
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.println("</result>");
		pw.close();
	}
	

	public void list(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		CommentsDao dao = CommentsDao.getInstance();
		ArrayList<CommentsVo> list = dao.list();
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<result>");
		for(CommentsVo vo : list) {
			pw.println("<comm>");
			pw.println("<num>" + vo.getNum() + "</num>");
			pw.println("<id>" + vo.getId() + "</id>");
			pw.println("<comments>" + vo.getComments() + "</comments>");
			pw.println("</comm>");
		}
		pw.println("</result>");
		pw.close();
	}
	
	public void delete(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		CommentsDao dao = CommentsDao.getInstance();
		int n = dao.delete(num);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.println("</result>");
		pw.close();
	}
}
