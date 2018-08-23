package jh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mh.UsersDao;
import mh.UsersVo;
@WebServlet("/jh/demand.do")
public class DemandController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("insert")) {
			insert(request,response);
		}
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String email=request.getParameter("email");
		
		UsersDao usersDao=UsersDao.getInstance();
		UsersVo usersvo=usersDao.select(email);
		
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		NoticeBoardVo vo=new NoticeBoardVo(0, usersvo.getName(), email, title, content, 0, null);
		if(dao.insert(vo)>0) {
			listAll(request,response);
		}else {
			System.out.println("작성실패");
		}
	}
}
