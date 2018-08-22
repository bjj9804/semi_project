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
	<c:when test="${empty sessionScope.id }">
		<li><a href="<c:url value='/mh/login.do?cmd=loginform'/>">로그인</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="<c:url value='/mh/login.do?cmd=logoutform'/>">로그아웃</a></li>
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
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		UsersDao dao = UsersDao.getInstance();
		boolean b = dao.login(id,pwd);
		if(b){
			HttpSession session = request.getSession(); //세션객체 얻어오기
			session.setAttribute("id", id);
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(contextPath + "semi_project/main/index.jsp");
		}else {
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 맞지 않아요");
			RequestDispatcher rd = request.getRequestDispatcher("/semi_project/users/login.jsp");
			rd.forward(request, response);
		}
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(contextPath + "semi_project/main/index.jsp");
	}
}
