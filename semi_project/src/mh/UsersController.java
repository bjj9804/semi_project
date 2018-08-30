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
	//��������Ʈ
	protected void couponlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = (String)request.getSession().getAttribute("email");
		UsersDao dao = UsersDao.getInstance();
		ArrayList<CouponVo> list = dao.couponlist(email);
		System.out.println(email);
		request.setAttribute("list", list);
		request.getRequestDispatcher("../myshop/mycoupon.jsp").forward(request, response);
	}
	//��������
	protected void couponcnt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		UsersDao dao = UsersDao.getInstance();
		int cnt = dao.couponcnt(email);
		dao.couponcnt(email);
		request.setAttribute("cnt", cnt);
		request.getRequestDispatcher("../mh/users.do?cmd=userslist").forward(request, response);
	}
	//��������
	protected void coupongift(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String couponName = request.getParameter("couponGift");
		UsersDao dao = UsersDao.getInstance();
		dao.coupongift(email, couponName);
		request.getRequestDispatcher("../mh/users.do?cmd=userslist").forward(request, response);
	}
	//�������� ȸ������
	protected void userslist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UsersDao dao = UsersDao.getInstance();
		ArrayList<UsersVo> list = dao.userslist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../admin/userlist.jsp").forward(request, response);
	}
	//�̸��� ��뿩��
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
	//�̸���ã��
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
	//���ã��
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
	//ȸ������
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
	//�α���
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String email1 = request.getParameter("email");
		String email = id + email1;
		String pwd = request.getParameter("pwd");
		String auto = request.getParameter("auto");
		UsersDao dao = UsersDao.getInstance();
		boolean b = dao.login(email,pwd);
		int flag=0;
		if(!email.equals("")) {//�α����� �ϰ� ���� ���
			UsersDao usersDao=UsersDao.getInstance();
			UsersVo vo=usersDao.select(email);
			if(vo != null) {
				flag=vo.getFlag();//���������� ȸ������
			}
		}else {//�α����� ���ϰ� ���°�� 
			flag=1;
		}
		//request.setAttribute("flag", flag);
		//���ǿ� �����ϱ�
		if(b){
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("flag", flag);
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(contextPath + "/main/index.jsp");
		}else {
			request.setAttribute("errMsg", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			RequestDispatcher rd = request.getRequestDispatcher("../users/login.jsp");
			rd.forward(request, response);
		}
	}
	//�α׾ƿ�
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(contextPath + "/main/index.jsp");
	}
}
