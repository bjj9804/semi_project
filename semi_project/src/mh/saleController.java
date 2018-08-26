package mh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.PayVo;

@WebServlet("/mh/sale.do")
public class saleController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String contextPath = getServletContext().getContextPath();
		if(cmd != null && cmd.equals("salelist")) {			
			salelist(request,response);
		}else if(cmd != null && cmd.equals("userlist")) {
			userlist(request,response);
		}
	}
	protected void salelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saleDao dao = saleDao.getInstance();
		ArrayList<PayVo> list = dao.alllist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/sale/sale_list.jsp").forward(request, response);
	}
	protected void userlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
