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
		ItemDao idao=ItemDao.getInstance();
		int o_price=idao.getPrice(code);//��ǰ��ǰ���ݰ�������
		int price=o_price*orderAmount;//��ǰ���ϰ���*����
		
		
		if(check==0) {///0�̸� payTb�� ��ٱ��� �����ϰ� buyTb���� ��ǰinsert��Ű��
			pvo=new PayVo(cartNum, null, null, null, null, email, 0, 0);
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			
			dao.cart(pvo,bvo);//��ٱ��ϴ��(buyTb,payTb�� insert��)
			
		}else if(check==1) {///1�̸� ��ٱ��� �������� �ʰ� �����پ���
			bvo=new BuyVo(0, cartNum, code, isize, orderAmount, price);
			dao.buyInsert(bvo);
		}
		////payTb�� �Ѱ����� �������� �ʴ°ɷ�! �������̷�������ʾ����ϱ� ���������� �ѷ����� ���ϴ°ɷ�!
		
		
	}
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DemandDao dao=DemandDao.getInstance();
		
		dao.getCartNum(email);
		
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
		dao.payInsert(pvo);
		String buyList=request.getParameter("buyList");//buyNum���� �����
		String[] buyArray=buyList.split(",");		
		int num=0;
		for(int i=0;i<buyArray.length;i++) {
			int buyNum=Integer.parseInt(buyArray[i]);
			num+=dao.buyUpdate(buyNum,orderNum);//buyTb�� buyNum���� ���� cartNum�� orderNum���� �ٲٱ�			
		}
		if(buyArray.length!=num) {//�������н� Ȥ����!.....����°Ը��������� ���� �ȸ����Ǵµ� �����ߵǳ�?? �����ѵ�..
			
		}
		
		//�������� buyTb�� ��ٱ��Ͽ� ������ �����ִ��� Ȯ������ ���������� �������ΰ� �������� ������ payTb���� ��ٱ��������� �������Ѿ���
		dao.cartCheck()
		
		
	}
}
