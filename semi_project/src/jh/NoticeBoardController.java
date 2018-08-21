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
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(request,response);
		}
		
	}
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		ArrayList<NoticeBoardVo> list=dao.list();
		request.setAttribute("list", list);
		request.getRequestDispatcher("board/notice_list.jsp").forward(request, response);
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		NoticeBoardVo vo=dao.select(num);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("board/notice_detail.jsp").forward(request, response);			
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkList=request.getParameter("checkList");
		String[] checkArray=checkList.split(",");
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		boolean bool=true;
		for(int i=0;i<checkArray.length;i++) {
			int num=Integer.parseInt(checkArray[i]);
			if(dao.delete(num)<0) {
				bool=false;
			}
		}
		if(bool==false) {//���������� �����ߴ�.......
			return;
		}
		listAll(request,response);
	}
}