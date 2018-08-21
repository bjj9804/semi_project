package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice.do")
public class NoticeBoardController extends HttpServlet{
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
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		ArrayList<NoticeBoardVo> list=dao.list();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/noticeBoard.jsp").forward(request, response);
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		NoticeBoardVo vo=dao.select(num);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("/nb_detail.jsp").forward(request, response);	
		
	}
}
