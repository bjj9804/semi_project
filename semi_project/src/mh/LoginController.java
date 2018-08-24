package mh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* 濡쒓렇�씤..
<c:choose>
	<c:when test="${empty sessionScope.email }">
		<li><a href="<c:url value='/mh/login.do?cmd=loginform'/>">占싸깍옙占쏙옙</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="<c:url value='/mh/login.do?cmd=logoutform'/>">占싸그아울옙</a></li>
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
			request.setAttribute("errMsg", "鍮꾨�踰덊샇 �솗�씤 �떎�뙣!");
			RequestDispatcher rd = request.getRequestDispatcher("../myshop/profile.jsp");
			rd.forward(request, response);
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id") + request.getParameter("email");
		String pwd = request.getParameter("pwd");
		UsersDao dao = UsersDao.getInstance();
		boolean b = dao.login(email,pwd);
		int flag=0;
		if(!email.equals("")) {//로그인을 하고 들어온 경우
			UsersDao usersDao=UsersDao.getInstance();
			UsersVo vo=usersDao.select(email);
			flag=vo.getFlag();//관리자인지 회원인지	
		}else {//로그인을 안하고 들어온경우
			flag=1;
		}				
		//request.setAttribute("flag", flag);		
		if(b){
			HttpSession session = request.getSession(); //�꽭�뀡�뿉 �븘�씠�뵒 �떞湲�
			session.setAttribute("email", email);
			session.setAttribute("flag", flag);
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(contextPath + "/main/index.jsp");
		}else {
			request.setAttribute("errMsg", "濡쒓렇�씤 �떎�뙣");
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
