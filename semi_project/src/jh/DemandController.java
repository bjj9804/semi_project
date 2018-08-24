package jh;

import java.io.IOException;

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
		
		if(cmd!=null && cmd.equals("cart")) {
			cart(request,response);
		}else if(cmd!=null && cmd.equals("order")) {
			order(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {
			//delete(request,response);
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
		ItemDao idao=ItemDao.getInstance();
		int o_price=idao.getPrice(code);//상품단품가격가져오기
		int price=o_price*orderAmount;//상품단일가격*수량
		
		
		if(check==0) {///0이면 payTb에 장바구니 생성하고 buyTb에도 상품insert시키기
			pvo=new PayVo(cartNum, null, null, null, null, email, 0, 0);
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			
			dao.cart(pvo,bvo);//장바구니담기(buyTb,payTb에 insert됨)
			
		}else if(check==1) {///1이면 장바구니 생성하지 않고 가져다쓰기
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			dao.buyInsert(bvo);
		}
		////payTb의 총가격은 수정하지 않는걸로! 결제는이루어지지않았으니까 보여질때는 뿌려져서 더하는걸로!
		
		
	}
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		
		dao.getCartNum(email);
		
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
		dao.payInsert(pvo);
		String buyList=request.getParameter("buyList");//buyNum들을 갖고옴
		String[] buyArray=buyList.split(",");		
		int num=0;
		for(int i=0;i<buyArray.length;i++) {
			int buyNum=Integer.parseInt(buyArray[i]);
			num+=dao.buyUpdate(buyNum,orderNum);//buyTb의 buyNum으로 기존 cartNum을 orderNum으로 바꾸기			
		}
		if(buyArray.length!=num) {//결제실패시 혹여나!.....만드는게맘편하지만 굳이 안만들어도되는데 만들어야되나?? 복잡한데..
			
		}
		
		//결제이후 buyTb에 장바구니에 정보가 남아있는지 확인한후 남아있으면 내버려두고 남아있지 않으면 payTb에서 장바구니정보를 삭제시켜야함
		dao.cartCheck()
		
		
	}
}
