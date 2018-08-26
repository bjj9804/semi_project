package mh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mh/users.do")
public class UsersController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String contextPath = getServletContext().getContextPath();
		if(cmd != null && cmd.equals("loginform")) {
			response.sendRedirect(contextPath + "/users/login.jsp");
		}else if(cmd != null && cmd.equals("login")) {			
			login(request,response);
		}else if(cmd != null && cmd.equals("logoutform")) {
			logout(request,response);
		}else if(cmd != null && cmd.equals("joinform")) {
			join(request,response);
		}else if(cmd != null && cmd.equals("findidform")) {
			findid(request,response);
		}else if(cmd != null && cmd.equals("findpwdform")) {
			findpwd(request,response);
		}
	}
	protected void findid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		UsersDao dao = UsersDao.getInstance();
		String email = dao.findEmail(name, phone, question, answer);
		if(email != null) {
			request.setAttribute("findMsg", "ȸ������ �̸�����" + email + "�Դϴ�");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("findMsg", "�ش� �̸����� �����ϴ�");
			request.getRequestDispatcher("../users/login.jsp").forward(request, response);
		}
	}
	protected void findpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id")+request.getParameter("email");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		UsersDao dao = UsersDao.getInstance();
		String pwd = dao.findPwd(email, phone, question, answer);
		if(pwd != null) {
			request.setAttribute("findMsg", "ȸ������ ��й�ȣ��" + pwd + "�Դϴ�");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("findMsg", "�ش� ��й�ȣ�� �����ϴ�");
			request.getRequestDispatcher("../users/login.jsp").forward(request, response);
		}
	}
	protected void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email1") + request.getParameter("email2");
		String password = request.getParameter("pwd");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		String addr = request.getParameter("addr");
		String name = request.getParameter("name1") + request.getParameter("name2");
		
		UsersDao dao = UsersDao.getInstance();
		UsersVo vo = new UsersVo(email,password,question,answer,phone,addr,name,null,0,0);
		int n = dao.join(vo);
		if(n>0) {
			request.setAttribute("code", "success");
		}else {
			request.setAttribute("code", "fail");
		}
		request.getRequestDispatcher("../users/joinCon.jsp").forward(request, response);
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id") + request.getParameter("email");
		String pwd = request.getParameter("pwd");
		UsersDao dao = UsersDao.getInstance();
		boolean b = dao.login(email,pwd);
		int flag=0;
		if(!email.equals("")) {//�α����� �ϰ� ���� ���
			UsersDao usersDao=UsersDao.getInstance();
			UsersVo vo=usersDao.select(email);
			flag=vo.getFlag();//���������� ȸ������	
		}else {//�α����� ���ϰ� ���°��
			flag=1;
		}				
		//request.setAttribute("flag", flag);		
		if(b){
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("flag", flag);
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(contextPath + "/main/index.jsp");
		}else {
			request.setAttribute("errMsg", "���̵�� ��й�ȣ�� ���� �ʽ��ϴ�.");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(contextPath + "/main/index.jsp");
	}
}
