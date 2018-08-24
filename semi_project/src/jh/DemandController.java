package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jj.ItemDao;
import mh.UsersDao;
import mh.UsersVo;
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
		}
	}
	
	private void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		PayVo pvo=null;
		BuyVo bvo=null;
		
		int[] cartPayTbCh=dao.getCartNum(email);//(기존에있었는지없었는지유무,cartNum)
		int check=cartPayTbCh[0];//0이면 장바구니정보 없음. 1이면 장바구니정보 있었음.
								///0이면 payTb에 장바구니 생성.1이면 장바구니에 담긴 총가격 수정
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
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			
			if(dao.cart(pvo,bvo)>0) {//장바구니담기(buyTb,payTb에 insert됨)
				System.out.println("장바구니담기 성공");
			}else {
				System.out.println("장바구니담기 실패!!!");
			}
		}else if(check==1) {///1이면 장바구니 생성하지 않고 가져다쓰기
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			if(dao.buyInsert(bvo)>0) {
				System.out.println("기존에 있던 장바구니에 상품 추가 성공");
			}else {
				System.out.println("기존에 있던 장바구니에 상품 추가 실패!!!");
			}
		}
		////payTb의 총가격은 수정하지 않는걸로! 결제는이루어지지않았으니까 보여질때는 뿌려져서 더하는걸로!
		
		
	}
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();		
		int cartNum=dao.getCartNum(email)[2];
		
		int orderNum=dao.getOrderNum(email);		
		if(orderNum==0) {
			System.out.println("orderNum오류");
		}		
		//String state=request.getParameter("state"); 
		String method=request.getParameter("method");
		String addr=request.getParameter("addr");		
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		int payMoney=Integer.parseInt(request.getParameter("payMoney"));
		
		//buyTb과 payTb 주문번호 update시키기
		PayVo pvo=new PayVo(orderNum, null, null, method, addr, email, totalPrice, payMoney);
		if(dao.payInsert(pvo)>0) {
			System.out.println("payTb에 생성");
		}else {
			System.out.println("payTb에 생성실패!!!!");
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
		
		//결제이후 buyTb에 장바구니에 정보가 남아있는지 확인한후 남아있으면 내버려두고 남아있지 않으면 payTb에서 장바구니정보를 삭제시켜야함
		int check=dao.cartCheck(cartNum);
		if(check==0) {
			if(dao.payDelete(cartNum)>0) {
				System.out.println("payTb에서 장바구니정보삭제");
			}else {
				System.out.println("payTb에서 장바구니정보삭제 실패!!!!");
			}
		}
	}
	private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		ArrayList<Object[]> list=new ArrayList<>();
		ArrayList<BuyVo> blist=dao.getBuyVo(email);
		for(BuyVo bvo:blist) {
			String code=bvo.getCode();
			LookVo lvo=dao.getLookVo(code);
			String lookFront=lvo.getLookFront();
			ItemVo ivo=dao.getItemVo(code);
			String description=ivo.getDescription();
			int price=ivo.getPrice();
			Object[] ob= {bvo,lookFront,description,price};
			list.add(ob);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/demand/cart.jsp").forward(request, response);
		
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	