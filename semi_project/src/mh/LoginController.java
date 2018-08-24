package mh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* 로그인..
<c:choose>
	<c:when test="${empty sessionScope.email }">
		<li><a href="<c:url value='/mh/login.do?cmd=loginform'/>">�α���</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="<c:url value='/mh/login.do?cmd=logoutform'/>">�α׾ƿ�</a></li>
	</c:otherwise>
</c:choose>
 */
@WebServlet("/mh/login.do")
public class LoginController extends HttpServlet{
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
		}else if(cmd != null && cmd.equals("profileView")) {
			profile(request,response);
		}
	}
	protected void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email = (String)request.getSession().getAttribute("email");
		String pwd = request.getParameter("pwd");
		UsersDao dao = UsersDao.getInstance();
		boolean a = dao.login(email,pwd);
		
		if(a){
			UsersVo vo = dao.select(email);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("../myshop/modify.jsp").forward(request, response);
		}else {
			request.setAttribute("errMsg", "비밀번호 확인 실패!");
			RequestDispatcher rd = request.getRequestDispatcher("../myshop/profile.jsp");
			rd.forward(request, response);
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id") + request.getParameter("email");
		String pwd = request.getParameter("pwd");
		UsersDao dao = UsersDao.getInstance();
		boolean b = dao.login(email,pwd);
		if(b){
			HttpSession session = request.getSession(); //세션에 아이디 담기
			session.setAttribute("email", email);
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(contextPath + "/main/index.jsp");
		}else {
			request.setAttribute("errMsg", "로그인 실패");
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
