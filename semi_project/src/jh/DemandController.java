package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eb.BuyVo;

import mh.UsersDao;
import mh.UsersVo;
import ms.ItemImgVo;
@WebServlet("/jh/demand.do")
public class DemandController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("cart")) {//상세페이지에서 장바구니담기 누르면 메소드호출!
			cart(request,response);
		}else if(cmd!=null && cmd.equals("order")) {//orderForm에서 결제하기 누르면 메소드호출!
			order(request,response);
		}else if(cmd!=null && cmd.equals("showCart")) {//장바구니보기 누르면 메소드호출!
			showCart(request,response);
		}else if(cmd!=null && cmd.equals("showOrderForm")) {//장바구니보기 누르면 메소드호출!
			showOrderForm(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {//장바구니에서 상품삭제
			delete(request,response);
		}
	}	
	
	private void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath=getServletContext().getContextPath();
		String email=request.getParameter("email");
		if(email==null||email.equals("")) {
			response.sendRedirect(contextPath + "/users/login.jsp");			
		
		}else {		
			DemandDao dao=DemandDao.getInstance();
			PayVo pvo=null;
			BuyVo bvo=null;
			
			int[] cartPayTbCh=dao.getCartNum(email);//(기존에있었는지없었는지유무,cartNum)
			int check=cartPayTbCh[0];//0이면 장바구니정보 없음. 1이면 장바구니정보 있었음.
									///0이면 payTb에 장바구니 생성.1이면 장바구니번호가져와 insert하기
			int cartNum=cartPayTbCh[1];		
			if(cartNum==0) {
				System.out.println("cartNum오류");
			}
			String code=request.getParameter("code");
			String isize=request.getParameter("isize");
			int orderAmount=Integer.parseInt(request.getParameter("orderAmount"));
			int o_price=dao.getItemVo(code).getPrice();//상품단품가격가져오기
			int price=o_price*orderAmount;//상품단일가격*수량
			
			if(check==0) {///0이면 payTb에 장바구니 생성하고 buyTb에도 상품insert시키기
				pvo=new PayVo(cartNum, null, null, null, null, email, 0, 0);
				bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price, null);
				
				if(dao.cart(pvo,bvo)>0) {//장바구니담기(buyTb,payTb에 insert됨)
					System.out.println("장바구니담기 성공");
				}else {
					System.out.println("장바구니담기 실패!!!");
				}
			}else if(check==1) {///1이면 장바구니 생성하지 않고 가져다쓰기
				bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price, null);
				if(dao.buyInsert(bvo)>0) {
					System.out.println("기존에 있던 장바구니에 상품 추가 성공");
				}else {
					System.out.println("기존에 있던 장바구니에 상품 추가 실패!!!");
				}
			}
			////payTb의 총가격은 수정하지 않는걸로! 결제는이루어지지않았으니까 보여질때는 뿌려져서 더하는걸로!
			request.setAttribute("email", email);
			showCart(request, response);
		}
	}
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String couponNumSt=request.getParameter("couponNum");
		System.out.println(couponNumSt);
		DemandDao dao=DemandDao.getInstance();		
		int cartNum=dao.getCartNum(email)[2];
		
		int orderNum=dao.getOrderNum(email);		
		if(orderNum==0) {
			System.out.println("orderNum오류");
		}		
		//String state=request.getParameter("state"); 
		String method=request.getParameter("method");
		String addr=request.getParameter("addr1") + request.getParameter("addr2");		
		String payMoney1=request.getParameter("payMoney");	
		int payMoney=0;
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		if(payMoney1==null||payMoney1.equals("")) {
			payMoney=totalPrice;
		}else {
			payMoney=Integer.parseInt(payMoney1);					
		}
		System.out.println(orderNum);
		//buyTb과 payTb 주문번호 update시키기
		PayVo pvo=new PayVo(orderNum, null, null, method, addr, email, totalPrice, payMoney);
		if(dao.payInsert(pvo)>0) {
			System.out.println("payTb에 생성");
		}else {
			System.out.println("payTb에 생성실패!!!!");
			return;
		}
		String buyList=request.getParameter("buyList");//buyNum들을 갖고옴
		String[] buyArray=buyList.split(",");		
		int num=0;
		for(int i=0;i<buyArray.length;i++) {
			int buyNum=Integer.parseInt(buyArray[i]);
			num+=dao.buyUpdate(buyNum,orderNum);//buyTb의 buyNum으로 기존 cartNum을 orderNum으로 바꾸기			
		}
		if(buyArray.length!=num) {//결제실패시 혹여나!.....만드는게맘편하지만 굳이 안만들어도되는데 만들어야되나?? 복잡한데..
			System.out.println("buyTb의 buyNum으로 기존 cartNum을 orderNum으로 바꾸기 실패!!");
		}
		
		if(!couponNumSt.equals("0%")) {
			int couponNum=Integer.parseInt(couponNumSt);
			if(dao.couponUpdate(couponNum)>0) {
				System.out.println("쿠폰상태변경완료");
			}else {
				System.out.println("쿠폰변경실패!!");
			}
		}		
		
		//결제이후 buyTb에 장바구니에 정보가 남아있는지 확인한후 남아있으면 내버려두고 남아있지 않으면 payTb에서 장바구니정보를 삭제시켜야함
		int check=dao.cartCheck(cartNum);
		if(check==0) {
			if(dao.payDelete(cartNum)>0) {
				System.out.println("payTb에서 장바구니정보삭제");				
			}else {
				System.out.println("payTb에서 장바구니정보삭제 실패!!!!");
			}
		}
		eb.DemandDao dmDao=eb.DemandDao.getInstance();
		ArrayList<PayVo> list=dmDao.mylist(email);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/myshop/mybuy_list.jsp").forward(request, response);
	}
	
	
	private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath=getServletContext().getContextPath();
		String email=request.getParameter("email");
		if(email==null||email.equals("")) {
			response.sendRedirect(contextPath + "/users/login.jsp");
		}else {
			DemandDao dao=DemandDao.getInstance();
			ArrayList<Object[]> list=new ArrayList<>();
			ArrayList<BuyVo> blist=dao.getBuyVo(email);
			for(BuyVo bvo:blist) {//buyVo==>buyNum,orderNum,code,isize,orderAmount,price
				String code=bvo.getCode();
				String imgFront=dao.getType(code);				
				ItemVo ivo=dao.getItemVo(code);
				String description=ivo.getDescription();
				int price=ivo.getPrice();
				Object[] ob= {bvo,imgFront,description,price};
				list.add(ob);
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/demand/cart.jsp").forward(request, response);
		}
	}	
	
	private void showOrderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String buyList=request.getParameter("buyList");
		String totalPrice=request.getParameter("totalPrice");
		
		ArrayList<Object[]> list=new ArrayList<>();
		DemandDao dao=DemandDao.getInstance();
		String[] buyArray=buyList.split(",");
		for(int i=0;i<buyArray.length;i++) {
			int buyNum=Integer.parseInt(buyArray[i]);
			BuyVo bvo=dao.getBuyVo(buyNum);
			String code=bvo.getCode();
			String imgFront=dao.getType(code);	
			ItemVo ivo=dao.getItemVo(code);
			String description=ivo.getDescription();
			int price=ivo.getPrice();
			Object[] ob= {bvo,imgFront,description,price};
			list.add(ob);
		}		
		ArrayList<CouponVo> cvo=dao.getCoupon(email);		
		
		UsersVo usersvo=UsersDao.getInstance().select(email);
		
		request.setAttribute("cvo", cvo);
		request.setAttribute("buyList", buyList);
		request.setAttribute("email", email);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("usersvo", usersvo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/demand/orderForm.jsp").forward(request, response);
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String checkList=request.getParameter("checkList");
		String[] checkArray=checkList.split(",");
		DemandDao dao=DemandDao.getInstance();
		for(int i=0;i<checkArray.length;i++) {
			int buyNum=Integer.parseInt(checkArray[i]);
			dao.buyDelete(buyNum);
		}
		
		request.getRequestDispatcher("/jh/demand.do?cmd=showCart&email="+email).forward(request, response);
		
	
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	