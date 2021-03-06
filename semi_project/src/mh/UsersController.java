package mh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import jh.CouponVo;

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
		}else if(cmd != null && cmd.equals("emailcheck")) {
			emailcheck(request,response);
		}else if(cmd != null && cmd.equals("userslist")) {
			userslist(request,response);
		}else if(cmd != null && cmd.equals("coupongift")) {
			coupongift(request,response);
		}else if(cmd != null && cmd.equals("couponcnt")) {
			couponcnt(request,response);
		}else if(cmd != null && cmd.equals("couponlist")) {
			couponlist(request,response);
		}
	}
	//庭肉軒什闘
	protected void couponlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = (String)request.getSession().getAttribute("email");
		UsersDao dao = UsersDao.getInstance();
		ArrayList<CouponVo> list = dao.couponlist(email);
		System.out.println(email);
		request.setAttribute("list", list);
		request.getRequestDispatcher("../myshop/mycoupon.jsp").forward(request, response);
	}
	//庭肉姐呪
	protected void couponcnt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		UsersDao dao = UsersDao.getInstance();
		int cnt = dao.couponcnt(email);
		dao.couponcnt(email);
		request.setAttribute("cnt", cnt);
		request.getRequestDispatcher("../mh/users.do?cmd=userslist").forward(request, response);
	}
	//庭肉走厭
	protected void coupongift(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String couponName = request.getParameter("couponGift");
		UsersDao dao = UsersDao.getInstance();
		dao.coupongift(email, couponName);
		request.getRequestDispatcher("../mh/users.do?cmd=userslist").forward(request, response);
	}
	//淫軒切税 噺据淫軒
	protected void userslist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UsersDao dao = UsersDao.getInstance();
		ArrayList<UsersVo> list = dao.userslist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../admin/userlist.jsp").forward(request, response);
	}
	//戚五析 紫遂食採
	protected void emailcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		response.setContentType("text/xml;charset=utf-8");
		UsersDao dao = UsersDao.getInstance();
		boolean using = dao.checkE(email);
		JSONObject obj = new JSONObject();
		obj.put("using", using);
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(obj.toString());
		pw.close();
	}
	//戚五析達奄
	protected void findid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		UsersDao dao = UsersDao.getInstance();
		String email = dao.findEmail(name, phone, question, answer);
		if(email != null) {
			request.setAttribute("findMsg", "噺据還税 戚五析精" + email + "脊艦陥");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("findMsg", "背雁 戚五析戚 蒸柔艦陥");
			request.getRequestDispatcher("../users/login.jsp").forward(request, response);
		}
	}
	//搾腰達奄
	protected void findpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id")+request.getParameter("email");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		UsersDao dao = UsersDao.getInstance();
		String pwd = dao.findPwd(email, phone, question, answer);
		if(pwd != null) {
			request.setAttribute("findMsg", "噺据還税 搾腔腰硲澗" + pwd + "脊艦陥");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("findMsg", "背雁 搾腔腰硲亜 蒸柔艦陥");
			request.getRequestDispatcher("../users/login.jsp").forward(request, response);
		}
	}
	//噺据亜脊
	protected void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + email2;
		String password = request.getParameter("pwd");
		int question = Integer.parseInt(request.getParameter("question"));
		String answer = request.getParameter("answer");
		String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		String addr = request.getParameter("addr1") + request.getParameter("addr2");
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
	//稽益昔
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String email1 = request.getParameter("email");
		String email = id + email1;
		String pwd = request.getParameter("pwd");
		String autoCheck = request.getParameter("autoCheck");
		String idCheck = request.getParameter("idCheck");
		UsersDao dao = UsersDao.getInstance();
		boolean b = dao.login(email,pwd);
		int flag=0;
		if(!email.equals("")) {//稽益昔聖 馬壱 級嬢紳 井酔
			UsersDao usersDao=UsersDao.getInstance();
			UsersVo vo=usersDao.select(email);
			if(vo != null) {
				flag=vo.getFlag();//淫軒切昔走 噺据昔走
			}
		}else {//稽益昔聖 照馬壱 級嬢紳井酔 
			flag=1;
		}
		//request.setAttribute("flag", flag);
		//室芝拭 煽舌馬奄
		if(b){
			if(autoCheck != null) {
				Cookie cook = new Cookie("email", email);
				cook.setMaxAge(60*60*24*365);//析鰍疑照 切政戚遂ぞぞ
				cook.setPath("/");
				response.addCookie(cook);
			}else {
				Cookie cook = new Cookie("email", null);
				cook.setMaxAge(0);//析鰍疑照 切政戚遂ぞぞ
				cook.setPath("/");
				response.addCookie(cook);
			}
			if(idCheck != null) {
				Cookie cookid = new Cookie("id", id);
				cookid.setMaxAge(60*60*24*365);//析鰍疑照 切政戚遂ぞぞ
				cookid.setPath("/");
				response.addCookie(cookid);
				Cookie cookemail = new Cookie("email1", email1);
				cookemail.setMaxAge(60*60*24*365);//析鰍疑照 切政戚遂ぞぞ
				cookemail.setPath("/");
				response.addCookie(cookemail);
			}else {
				Cookie cookid = new Cookie("id", null);
				cookid.setMaxAge(0);//析鰍疑照 切政戚遂ぞぞ
				cookid.setPath("/");
				response.addCookie(cookid);
				Cookie cookemail = new Cookie("email1", null);
				cookemail.setMaxAge(0);//析鰍疑照 切政戚遂ぞぞ
				cookemail.setPath("/");
				response.addCookie(cookemail);
			}
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("flag", flag);
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(contextPath + "/main/index.jsp");
		}else {
			request.setAttribute("errMsg", "焼戚巨 暁澗 搾腔腰硲亜 析帖馬走 省柔艦陥.");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}
	}
	//稽益焼数
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout");
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie cook = new Cookie("email", null);
		cook.setMaxAge(0);//析鰍疑照 切政戚遂ぞぞ
		cook.setPath("/");
		response.addCookie(cook);
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(contextPath + "/main/index.jsp");
	}
}
