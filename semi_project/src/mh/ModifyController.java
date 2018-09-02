package mh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mh/myshopmodify.do")
public class ModifyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String contextPath = getServletContext().getContextPath();
		if(cmd != null && cmd.equals("modify")) {			
			modify(request,response);
		}else if(cmd != null && cmd.equals("profileView")) {
			profile(request,response);
		}else if(cmd != null && cmd.equals("delete")) {
			delete(request,response);
		}else if(cmd != null && cmd.equals("admindelete")) {
			admindelete(request,response);
		}
	}
	//Ż���ϱ�
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		UsersDao dao = UsersDao.getInstance();
		int n = dao.delete(email);
		if(n>0) {
			request.setAttribute("deleteMsg", "success");
		}else {
			request.setAttribute("deleteMsg", "fail");
		}
		HttpSession session = request.getSession();
		session.invalidate();
		String contextPath = getServletContext().getContextPath();
		request.getRequestDispatcher(contextPath + "/myshop/deleteok.jsp").forward(request, response);
	}
	//���������������� Ż���Ű��
	protected void admindelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		UsersDao dao = UsersDao.getInstance();
		int n = dao.delete(email);
		if(n>0) {
			request.setAttribute("deleteMsg", "success");
		}else {
			request.setAttribute("deleteMsg", "fail");
		}
		HttpSession session = request.getSession();
		session.invalidate();
		String contextPath = getServletContext().getContextPath();
		request.getRequestDispatcher("../mh/users.do?cmd=userslist").forward(request, response);
	}
	//�����ʺ������� ��й�ȣ Ȯ���ϱ�
	protected void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String)request.getSession().getAttribute("email");
		String pwd = request.getParameter("pwd");
		UsersDao dao = UsersDao.getInstance();
		boolean a = dao.login(email,pwd);
		
		if(a){
			UsersVo vo = dao.select(email);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("../myshop/modify.jsp").forward(request, response);
		}else {
			request.setAttribute("errMsg", "��й�ȣ�� ���� �ʽ��ϴ�!");
			RequestDispatcher rd = request.getRequestDispatcher("../myshop/profile.jsp");
			rd.forward(request, response);
		}
	}
	protected void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String name = request.getParameter("name");
		UsersDao dao = UsersDao.getInstance();
		UsersVo vo = new UsersVo(email,password,0,null,phone,addr,name,null,0,0);
		int n = dao.update(vo);
		if(n>0) {
			request.setAttribute("updateMsg", "success");
		}else {
			request.setAttribute("updateMsg", "fail");
		}
		request.getRequestDispatcher("../myshop/myshop_index.jsp").forward(request, response);
	}
}
