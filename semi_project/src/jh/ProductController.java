package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ms.ItemImgVo;
@WebServlet("/jh/product.do")
public class ProductController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("lookList")) {
			lookList(request,response);
		}
		
	}
	
	private void lookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd1=request.getParameter("cmd1");
		ProductDao dao=ProductDao.getInstance();
		ArrayList<LookVo> list=dao.lookList(cmd1);		
		request.setAttribute("cmd", cmd1);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/product/product_look.jsp").forward(request, response);
	}

	
}


















