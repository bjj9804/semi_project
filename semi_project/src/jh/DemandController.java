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
		
		if(cmd!=null && cmd.equals("cart")) {//������������ ��ٱ��ϴ�� ������ �޼ҵ�ȣ��!
			cart(request,response);
		}else if(cmd!=null && cmd.equals("order")) {//orderForm���� �����ϱ� ������ �޼ҵ�ȣ��!
			order(request,response);
		}else if(cmd!=null && cmd.equals("showCart")) {//��ٱ��Ϻ��� ������ �޼ҵ�ȣ��!
			showCart(request,response);
		}else if(cmd!=null && cmd.equals("showOrderForm")) {//��ٱ��Ϻ��� ������ �޼ҵ�ȣ��!
			showOrderForm(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {//��ٱ��Ͽ��� ��ǰ����
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
			
			int[] cartPayTbCh=dao.getCartNum(email);//(�������־�����������������,cartNum)
			int check=cartPayTbCh[0];//0�̸� ��ٱ������� ����. 1�̸� ��ٱ������� �־���.
									///0�̸� payTb�� ��ٱ��� ����.1�̸� ��ٱ��Ϲ�ȣ������ insert�ϱ�
			int cartNum=cartPayTbCh[1];		
			if(cartNum==0) {
				System.out.println("cartNum����");
			}
			String code=request.getParameter("code");
			String isize=request.getParameter("isize");
			int orderAmount=Integer.parseInt(request.getParameter("orderAmount"));
			int o_price=dao.getItemVo(code).getPrice();//��ǰ��ǰ���ݰ�������
			int price=o_price*orderAmount;//��ǰ���ϰ���*����
			
			if(check==0) {///0�̸� payTb�� ��ٱ��� �����ϰ� buyTb���� ��ǰinsert��Ű��
				pvo=new PayVo(cartNum, null, null, null, null, email, 0, 0);
				bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price, null);
				
				if(dao.cart(pvo,bvo)>0) {//��ٱ��ϴ��(buyTb,payTb�� insert��)
					System.out.println("��ٱ��ϴ�� ����");
				}else {
					System.out.println("��ٱ��ϴ�� ����!!!");
				}
			}else if(check==1) {///1�̸� ��ٱ��� �������� �ʰ� �����پ���
				bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price, null);
				if(dao.buyInsert(bvo)>0) {
					System.out.println("������ �ִ� ��ٱ��Ͽ� ��ǰ �߰� ����");
				}else {
					System.out.println("������ �ִ� ��ٱ��Ͽ� ��ǰ �߰� ����!!!");
				}
			}
			////payTb�� �Ѱ����� �������� �ʴ°ɷ�! �������̷�������ʾ����ϱ� ���������� �ѷ����� ���ϴ°ɷ�!
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
			System.out.println("orderNum����");
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
		//buyTb�� payTb �ֹ���ȣ update��Ű��
		PayVo pvo=new PayVo(orderNum, null, null, method, addr, email, totalPrice, payMoney);
		if(dao.payInsert(pvo)>0) {
			System.out.println("payTb�� ����");
		}else {
			System.out.println("payTb�� ��������!!!!");
			return;
		}
		String buyList=request.getParameter("buyList");//buyNum���� �����
		String[] buyArray=buyList.split(",");		
		int num=0;
		for(int i=0;i<buyArray.length;i++) {
			int buyNum=Integer.parseInt(buyArray[i]);
			num+=dao.buyUpdate(buyNum,orderNum);//buyTb�� buyNum���� ���� cartNum�� orderNum���� �ٲٱ�			
		}
		if(buyArray.length!=num) {//�������н� Ȥ����!.....����°Ը��������� ���� �ȸ����Ǵµ� �����ߵǳ�?? �����ѵ�..
			System.out.println("buyTb�� buyNum���� ���� cartNum�� orderNum���� �ٲٱ� ����!!");
		}
		
		if(!couponNumSt.equals("0%")) {
			int couponNum=Integer.parseInt(couponNumSt);
			if(dao.couponUpdate(couponNum)>0) {
				System.out.println("�������º���Ϸ�");
			}else {
				System.out.println("�����������!!");
			}
		}		
		
		//�������� buyTb�� ��ٱ��Ͽ� ������ �����ִ��� Ȯ������ ���������� �������ΰ� �������� ������ payTb���� ��ٱ��������� �������Ѿ���
		int check=dao.cartCheck(cartNum);
		if(check==0) {
			if(dao.payDelete(cartNum)>0) {
				System.out.println("payTb���� ��ٱ�����������");				
			}else {
				System.out.println("payTb���� ��ٱ����������� ����!!!!");
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	