package mh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mh/userdelete.do")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		UsersDao dao = UsersDao.getInstance();
		int n = dao.delete(email);
		if(n>0) {
			request.setAttribute("deleteMsg", "success");
		}else {
			request.setAttribute("deleteMsg", "fail");
		}
		request.getRequestDispatcher("../myshop/deleteok.jsp").forward(request, response);
	}
}
