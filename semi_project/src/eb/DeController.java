package eb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.BuyVo;
import jh.PayVo;
import mh.UsersDao;
import mh.UsersVo;

@WebServlet("/demand.do")
public class DeController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd!=null && cmd.equals("paylist")) {
			paylist(request,response);
		}else if(cmd!=null && cmd.equals("buylist")) {
			buylist(request,response);
		}else if(cmd!=null && cmd.equals("stateadmin")) {
			stateadmin(request,response);
		}else if(cmd!=null && cmd.equals("mylist")) {
			mylist(request,response);
		}else if(cmd!=null && cmd.equals("stateconfirm")) {
			stateconfirm(request,response);
		}
	}
		protected void paylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			DemandDao dao=DemandDao.getInstance();
			ArrayList<PayVo> list=dao.list();
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/demand.jsp").forward(request, response);
		}
		protected void buylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int orderNum=Integer.parseInt(request.getParameter("num"));
			DemandDao dao=DemandDao.getInstance();
			ArrayList<BuyVo> list=dao.detail(orderNum);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/demand_detail.jsp").forward(request, response);	
	}
		protected void stateadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int orderNum=Integer.parseInt(request.getParameter("num"));
			DemandDao dao=DemandDao.getInstance();
			int n=dao.update(orderNum);
			request.setAttribute("n", n);
			request.getRequestDispatcher("demand.do?cmd=paylist").forward(request, response);	
	}
		protected void mylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String email=request.getParameter("email");
			DemandDao dao=DemandDao.getInstance();
			ArrayList<PayVo> list=dao.mylist(email);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/myshop/mybuy_list.jsp").forward(request, response);
			
}
		
		protected void stateconfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int orderNum=Integer.parseInt(request.getParameter("num"));
			DemandDao dao=DemandDao.getInstance();
			int n=dao.payconfirm(orderNum);
			request.setAttribute("n", n);
			request.getRequestDispatcher("demand.do?cmd=mylist").forward(request, response);	
	}
}

