package jh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mh.UsersDao;
import mh.UsersVo;
@WebServlet("/jh/demand.do")
public class DemandController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("cart")) {
			cart(request,response);
		}else if(cmd!=null && cmd.equals("order")) {
			order(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(request,response);
		}
	}
	
	private void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		
		int cartNum=dao.getCartNum(email);
		if(cartNum==0) {
			System.out.println("cartNum오류");
		}
		String code=request.getParameter("code");
		String isize=request.getParameter("isize");
		int orderAmount=Integer.parseInt(request.getParameter("orderAmount"));
		int o_price=dao.getPrice(code);
		int price=o_price*orderAmount;
		
		//String state="cart";
		//String method=request.getParameter("method");
		//String addr=request.getParameter("addr");
		//int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		//int payMoney=Integer.parseInt(request.getParameter("payMoney"));
		
		BuyVo bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
		PayVo pvo=new PayVo(cartNum, null, null, null, null, email, 0, 0);
		
		int check=dao.cart(bvo,pvo);//장바구니담기(buyTb,payTb에 insert됨)
		if(check>0) {
			System.out.println("장바구니담기 성공");
		}else {
			System.out.println("장바구니담기 실패");
		}
	}
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		
		int orderNum=dao.getOrderNum(email);		
		if(orderNum==0) {
			System.out.println("orderNum오류");
		}		
		//String state=request.getParameter("state"); 
		String method=request.getParameter("method");
		String addr=request.getParameter("addr");
		
		String buyList=request.getParameter("buyList");//buyNum들을 갖고옴
		String[] buyArray=buyList.split(",");
		int totalPrice=0;
		for(int i=0;i<buyArray.length;i++) {
			dao.getPrice(buyArray[i]);
		}
		
		
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		int payMoney=Integer.parseInt(request.getParameter("payMoney"));
		
		String buyList=request.getParameter("buyList");
		String[] buyArray=buyList.split(",");
		
		
		UsersDao usersDao=UsersDao.getInstance();
		UsersVo usersvo=usersDao.select(email);
		
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		NoticeBoardVo vo=new NoticeBoardVo(0, usersvo.getName(), email, title, content, 0, null);
		if(dao.insert(vo)>0) {
			listAll(request,response);
		}else {
			System.out.println("작성실패");
		}
	}
}
