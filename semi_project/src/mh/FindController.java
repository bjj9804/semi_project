package mh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mh/find.do")
public class FindController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String contextPath = getServletContext().getContextPath();
		if(cmd != null && cmd.equals("findidform")) {
			findid(request,response);
		}else if(cmd != null && cmd.equals("findpwdform")) {
			findpwd(request,response);
		}
	}
	protected void findid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		UsersDao dao = UsersDao.getInstance();
		String pwd = dao.findEmail(phone, question, answer);
		if(pwd != null) {
			request.setAttribute("findPwdMsg", "회원님의 이메일은" + pwd + "입니다");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("findPwdMsg", "해당 이메일이 없습니다");
			request.getRequestDispatcher("../users/login.jsp").forward(request, response);
		}
	}
	protected void findpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		UsersDao dao = UsersDao.getInstance();
		String pwd = dao.findPwd(email, phone, question, answer);
		if(pwd != null) {
			request.setAttribute("findPwdMsg", "회원님의 비밀번호는" + pwd + "입니다");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("findPwdMsg", "해당 비밀번호가 없습니다");
			request.getRequestDispatcher("../users/login.jsp").forward(request, response);
		}
	}
}
