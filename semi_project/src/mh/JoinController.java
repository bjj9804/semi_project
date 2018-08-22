package mh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join.do")
public class JoinController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email1") + request.getParameter("email2");
		String password = request.getParameter("pwd");
		String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		String addr = request.getParameter("addr");
		String name = request.getParameter("name1") + request.getParameter("name2");
		String coupon = request.getParameter("coupon");
		int flag = Integer.parseInt(request.getParameter("flag"));
		
		UsersDao dao = UsersDao.getInstance();
		UsersVo vo = new UsersVo(email,password,phone,addr,name,null,coupon,flag);
		int n = dao.join(vo);
		
		if(n>0) {
			response.sendRedirect("login.jsp");
		}else {
			request.setAttribute("errMsg", "������ ���� ȸ������ ����");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}
}