package mh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mh/modify.do")
public class ModifyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String name = request.getParameter("name");
		System.out.println(email);
		UsersDao dao = UsersDao.getInstance();
		UsersVo vo = new UsersVo(email,password,0,null,phone,addr,name,null,0,0);
		int n = dao.update(vo);
		System.out.println(n);
		if(n>0) {
			request.setAttribute("updateMsg", "success");
		}else {
			request.setAttribute("updateMsg", "fail");
		}
		request.getRequestDispatcher("../myshop/modifyok.jsp").forward(request, response);
	}
}
