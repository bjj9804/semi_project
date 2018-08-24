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
		
		if(cmd!=null && cmd.equals("cart")) {//������������ ��ٱ��ϴ�� ������ �޼ҵ�ȣ��!
			cart(request,response);
		}else if(cmd!=null && cmd.equals("order")) {//orderForm���� �����ϱ� ������ �޼ҵ�ȣ��!
			order(request,response);
		}else if(cmd!=null && cmd.equals("showCart")) {//��ٱ��Ϻ��� ������ �޼ҵ�ȣ��!
			showCart(request,response);
		}
	}
	
	private void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		PayVo pvo=null;
		BuyVo bvo=null;
		
		int[] cartPayTbCh=dao.getCartNum(email);//(�������־�����������������,cartNum)
		int check=cartPayTbCh[0];//0�̸� ��ٱ������� ����. 1�̸� ��ٱ������� �־���.
								///0�̸� payTb�� ��ٱ��� ����.1�̸� ��ٱ��Ͽ� ��� �Ѱ��� ����
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
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			
			if(dao.cart(pvo,bvo)>0) {//��ٱ��ϴ��(buyTb,payTb�� insert��)
				System.out.println("��ٱ��ϴ�� ����");
			}else {
				System.out.println("��ٱ��ϴ�� ����!!!");
			}
		}else if(check==1) {///1�̸� ��ٱ��� �������� �ʰ� �����پ���
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			if(dao.buyInsert(bvo)>0) {
				System.out.println("������ �ִ� ��ٱ��Ͽ� ��ǰ �߰� ����");
			}else {
				System.out.println("������ �ִ� ��ٱ��Ͽ� ��ǰ �߰� ����!!!");
			}
		}
		////payTb�� �Ѱ����� �������� �ʴ°ɷ�! �������̷�������ʾ����ϱ� ���������� �ѷ����� ���ϴ°ɷ�!
		
		
	}
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();		
		int cartNum=dao.getCartNum(email)[2];
		
		int orderNum=dao.getOrderNum(email);		
		if(orderNum==0) {
			System.out.println("orderNum����");
		}		
		//String state=request.getParameter("state"); 
		String method=request.getParameter("method");
		String addr=request.getParameter("addr");		
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		int payMoney=Integer.parseInt(request.getParameter("payMoney"));
		
		//buyTb�� payTb �ֹ���ȣ update��Ű��
		PayVo pvo=new PayVo(orderNum, null, null, method, addr, email, totalPrice, payMoney);
		if(dao.payInsert(pvo)>0) {
			System.out.println("payTb�� ����");
		}else {
			System.out.println("payTb�� ��������!!!!");
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
		
		//�������� buyTb�� ��ٱ��Ͽ� ������ �����ִ��� Ȯ������ ���������� �������ΰ� �������� ������ payTb���� ��ٱ��������� �������Ѿ���
		int check=dao.cartCheck(cartNum);
		if(check==0) {
			if(dao.payDelete(cartNum)>0) {
				System.out.println("payTb���� ��ٱ�����������");
			}else {
				System.out.println("payTb���� ��ٱ����������� ����!!!!");
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	