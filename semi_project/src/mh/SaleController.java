package mh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eb.BuyVo;
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
		}else if(cmd != null && cmd.equals("itemlist")) {
			itemlist(request,response);
		}else if(cmd != null && cmd.equals("orderlist")) {
			orderlist(request,response);
		}
	}
	//전체판매조회
	protected void salelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		ArrayList<PayVo> list = dao.salelist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../sale/sale_list.jsp").forward(request, response);
	}
	//회원별 판매조회
	protected void userlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		ArrayList<SaleUserVo> list = dao.userlist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../sale/sale_userlist.jsp").forward(request, response);
	}
	//월별 판매조회
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
	//주별 판매조회
	protected void weeklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-(cal.get(Calendar.DAY_OF_WEEK)-1));
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1 ;
		int startDay = cal.get(cal.DATE);
		int endDay = startDay + 7;
		int[] arr = {31,28,31,30,31,30,31,31,30,31,30,31};
		if(endDay > arr[month-1]) {
			endDay = arr[month-1];
		}
		ArrayList<PayVo> list = dao.weeklist(year,month,startDay,endDay);
		DatelistVo vo = dao.totlist(year, month, startDay, year, month, endDay);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	//오늘 판매조회
	protected void daylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1 ;
		int day = cal.get(cal.DATE);
		int endday = day + 1;
		ArrayList<PayVo> list = dao.daylist(year,month,day);
		DatelistVo vo = dao.totlist(year, month, day, year, month, endday);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	//선택날짜 판매조회
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
		System.out.println(""+ startyear + startmonth + startDay + endyear + endmonth + endDay+"");
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("../sale/sale_datelist.jsp").forward(request, response);
	}
	//회원별 판매조회 자세히 보기
	protected void userdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		SaleDao dao = SaleDao.getInstance(); 
		ArrayList<PayVo> list = dao.userdetail(email);
		request.setAttribute("list", list);
		request.setAttribute("email", email);
		request.getRequestDispatcher("../sale/sale_list.jsp").forward(request, response);
	}
	//상품별판매조회
	protected void itemlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SaleDao dao = SaleDao.getInstance();
		ArrayList<ItemlistVo> list = dao.itemlist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../sale/sale_itemlist.jsp").forward(request, response);
	}
	//주문상품내역
	protected void orderlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		SaleDao dao = SaleDao.getInstance();
		ArrayList<BuyVo> list = dao.orderlist(orderNum);
		request.setAttribute("list", list);
		request.getRequestDispatcher("../sale/sale_orderlist.jsp").forward(request, response);
	}
}
