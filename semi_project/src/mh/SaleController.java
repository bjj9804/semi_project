package mh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.PayVo;

@WebServlet("/mh/sale.do")
public class SaleController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("salelist")) {			
			salelist(request,response);
		}else if(cmd != null && cmd.equals("userlist")) {
			userlist(request,response);
		}else if(cmd != null && cmd.equals("monthlist")) {
			monthlist(request,response);
		}else if(cmd != null && cmd.equals("weeklist")) {
			weeklist(request,response);
		}else if(cmd != null && cmd.equals("daylist")) {
			daylist(request,response);
		}else if(cmd != null && cmd.equals("selectlist")) {
			selectlist(request,response);
		}else if(cmd != null && cmd.equals("userdetail")) {
			userdetail(request,response);
		}
	}
	protected void salelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		ArrayList<PayVo> list = dao.salelist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../sale/sale_list.jsp").forward(request, response);
	}
	protected void userlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		ArrayList<SaleUserVo> list = dao.userlist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../sale/sale_userlist.jsp").forward(request, response);
	}
	protected void monthlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		Calendar cal = Calendar.getInstance();
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1 ;
		ArrayList<PayVo> list = dao.monthlist(year,month,endDay);
		DatelistVo vo = dao.totlist(year, month, 1, year, month, endDay);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	protected void weeklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-(cal.get(Calendar.DAY_OF_WEEK)-1));
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1 ;
		int startDay = cal.get(cal.DATE);
		int endDay = cal.get(cal.DATE) + 7;
		ArrayList<PayVo> list = dao.weeklist(year,month,startDay,endDay);
		DatelistVo vo = dao.totlist(year, month, startDay, year, month, endDay);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	protected void daylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1 ;
		int day = cal.get(cal.DATE);
		ArrayList<PayVo> list = dao.daylist(year,month,day);
		DatelistVo vo = dao.totlist(year, month, day, year, month, day);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	protected void selectlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		int startyear = Integer.parseInt(request.getParameter("startyear"));
		int startmonth = Integer.parseInt(request.getParameter("startmonth"));
		int startDay = Integer.parseInt(request.getParameter("startDay"));
		int endyear = Integer.parseInt(request.getParameter("endyear"));
		int endmonth = Integer.parseInt(request.getParameter("endmonth"));
		int endDay = Integer.parseInt(request.getParameter("endDay"));
		ArrayList<PayVo> list = dao.selectlist(startyear, startmonth, startDay, endyear, endmonth, endDay);
		DatelistVo vo = dao.totlist(startyear, startmonth, startDay, endyear, endmonth, endDay);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	protected void userdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		SaleDao dao = SaleDao.getInstance();
		ArrayList<PayVo> list = dao.userdetail(email);
		request.setAttribute("list", list);
		request.setAttribute("email", email);
		request.getRequestDispatcher("../sale/sale_list.jsp").forward(request, response);
	}
}
