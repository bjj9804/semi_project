package eb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eb.BuyVo;
import jh.ItemVo;
import jh.ItemsizeVo;
import jh.PayVo;

@WebServlet("/demand.do")
public class DeController extends HttpServlet {
   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String cmd = request.getParameter("cmd");
      if (cmd != null && cmd.equals("paylist")) {
         paylist(request, response);
      } else if (cmd != null && cmd.equals("buylist")) {
         buylist(request, response);
      } else if (cmd != null && cmd.equals("stateadmin")) {
         stateadmin(request, response);
      } else if (cmd != null && cmd.equals("mylist")) {
         mylist(request, response);
      } else if (cmd != null && cmd.equals("stateconfirm")) {
         stateconfirm(request, response);
      }else if (cmd != null && cmd.equals("stateconfirm2")) {
         stateconfirm2(request, response);
       } else if (cmd != null && cmd.equals("cancel")) {
         cancel(request, response);
      } else if (cmd != null && cmd.equals("change")) {
         change(request, response);
      } else if (cmd != null && cmd.equals("mydetail")) {
         mydetail(request, response);
      } else if (cmd != null && cmd.equals("buychange")) {
         buychange(request, response);
      } else if (cmd != null && cmd.equals("refund")) {
         refund(request, response);
      } else if (cmd != null && cmd.equals("buychange2")) {
         buychange2(request, response);
      }else if (cmd != null && cmd.equals("buychange3")) {
          buychange3(request, response);
       } else if (cmd != null && cmd.equals("refund2")) {
         refund2(request, response);
      } else if (cmd != null && cmd.equals("returnlist")) {
         returnlist(request, response);
      } else if (cmd != null && cmd.equals("refundcon")) {
         refundcon(request, response);
      }else if (cmd != null && cmd.equals("refundcon2")) {
          refundcon2(request, response);
       }
   }
   //�ֹ������� �ֹ�����Ʈ
   protected void paylist(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      DemandDao dao = DemandDao.getInstance();
      ArrayList<PayVo> list = dao.list();

      request.setAttribute("list", list);
      request.getRequestDispatcher("/admin/demand.jsp").forward(request, response);
   }
   //�ֹ��������� �ֹ���ȣ ������ ������ buy
   protected void buylist(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      ArrayList<BuyVo> list = dao.detail(orderNum);
      request.setAttribute("list", list);
      request.getRequestDispatcher("/admin/demand_detail.jsp").forward(request, response);
   }
   //�����ڰ� ��ۿϷḦ ������ ���°� �ٲ�
   protected void stateadmin(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      int n = dao.update(orderNum);
      System.out.println(n);
      request.setAttribute("n", n);
      paylist(request, response);
   }

   protected void mylist(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      HttpSession session = request.getSession();
      String email = (String) session.getAttribute("email");
      System.out.println(email);
      // String email=request.getParameter("email");
      DemandDao dao = DemandDao.getInstance();
      ArrayList<PayVo> list = dao.mylist(email);
      request.setAttribute("list", list);
      request.getRequestDispatcher("/myshop/mybuy_list.jsp").forward(request, response);

   }
   //�����ڰ� ��ۻ��� ������Ʈ(����Ȯ��)
   protected void stateconfirm(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      int n = dao.payconfirm(orderNum); 
      int a = 0;
      ArrayList<BuyVo> list=dao.detail(orderNum);
      for(int i=0; i<list.size(); i++) {
    	  String isize = list.get(i).getIsize();
    	  String code = list.get(i).getCode();
    	  int amount= list.get(i).getOrderAmount();
    	  a=dao.amt(amount, code, isize);
      }
      //���⼭ ������ ���ش�
      request.setAttribute("n", n);
      request.setAttribute("a", a);
      request.getRequestDispatcher("demand.do?cmd=mylist").forward(request, response);
   }

   // ��۵Ǳ��� ������ҹ�ư
   protected void cancel(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      HttpSession session = request.getSession();
      int orderNum = Integer.parseInt(request.getParameter("num"));
      System.out.println(orderNum);
      DemandDao dao = DemandDao.getInstance();
      PayVo vo = dao.selectview(orderNum);
      System.out.println(vo.getEmail());
      int a = dao.buycancel(orderNum);
      int n = dao.paycancel(orderNum);
      String email = (String) session.getAttribute("email");
      session.setAttribute("email", email);
      request.setAttribute("email", email);
      request.setAttribute("n", n);
      request.setAttribute("a", a);
      // RequestDispatcher rd =
      // request.getRequestDispatcher("/semi_project/demand.do?cmd=mylist&email="+email);
      // rd.forward(request, response);
      // request.getRequestDispatcher("demand.do?cmd=mylist").forward(request,
      // response);
      mylist(request, response);

   }

   // ��ۻ��¸� ���ſϷ�� Ȯ��������
   protected void change(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      int n = dao.payconfirm(orderNum);
      request.setAttribute("n", n);
      request.getRequestDispatcher("demand.do?cmd=mylist").forward(request, response);
   }

   protected void mydetail(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      ArrayList<BuyVo> list = dao.detail(orderNum);
      //PayVo vo = dao.selectview(orderNum);
     // String state1 = vo.getState();
      request.setAttribute("list", list);
     // request.setAttribute("state1", state1);
      request.getRequestDispatcher("/myshop/mybuy_detail.jsp").forward(request, response);
   }

   // -------------------------ȯ��--------------------------------
   // ���������� ȯ���� ��ǰ �ѷ��ֱ�
   protected void refund(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      ArrayList<BuyVo> list = dao.detail(orderNum);
      ArrayList<ItemVo> itemlist = new ArrayList<>();
      System.out.println(list.size() + "");
      for (int i = 0; i < list.size(); i++) {
         BuyVo name = list.get(i);
         String code = name.getCode();
         ItemVo itemvo = dao.item(code);
         itemlist.add(itemvo);
      }
      request.setAttribute("list", list);
      request.setAttribute("itemlist", itemlist);
      request.setAttribute("orderNum", orderNum);
      request.getRequestDispatcher("/myshop/mybuy_refund.jsp").forward(request, response);
   }

   // �����ڰ� ������ ȯ�һ�ǰ�� ���� �����ͼ� �����ϰ� ������Ʈ��Ű��
   protected void refund2(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String checkList = request.getParameter("checkList");
      System.out.println(checkList);
      String reasonList = request.getParameter("reasonList");
      String[] checkArr = checkList.split(",");
      String[] reasonArr = reasonList.split(",");
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      for (int i = 0; i < checkArr.length; i++) {
         int buyNum = Integer.parseInt(checkArr[i]);
         String reason = reasonArr[i];
         int n = dao.buystatechange(buyNum);
         int a = dao.returninsert(reason, buyNum);
         int b = dao.refundup1(orderNum);
         if (n > 0 & a > 0) {
            System.out.println("���º���,�������̺���� ��� ����");
         } else {
            System.out.println("�����߸���...");
            // dao.buy���̺��� state�� ��ǰ��û���� �ٲٴ� �޼ҵ�(buyNum);
            // dao.return���̺�� �μ�Ʈ�ϴ� �޼ҵ�(buyNum,reasonArr[i]);
            // pay���̺��� state�� ��ǰ��������� �ٲٴ� �޼ҵ�

         }
      }
      // PayVo vo=dao.ordernumselect(buyNum);
      // int orderNum=vo.getOrderNum();
      PayVo vo = dao.selectview(orderNum);
      String state1 = vo.getState();
      ArrayList<BuyVo> list = dao.detail(orderNum);
      request.setAttribute("list", list);
      request.setAttribute("state1", state1);
      request.getRequestDispatcher("/myshop/mybuy_detail.jsp").forward(request, response);
   }

   // �������������� ��ȯ/ȯ�Ҹ���Ʈ�ѷ��ֱ�

protected void returnlist(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      DemandDao dao = DemandDao.getInstance();
      ArrayList<BuyVo> list = dao.refundlist();
      ArrayList<Return_buyVo> realist=new ArrayList<>();
      for(int i=0; i<list.size(); i++){
    	  BuyVo vo2=list.get(i);
    	  int buynum = vo2.getBuyNum();
    		Return_buyVo vo=dao.reason(buynum);
    		realist.add(vo);
      }
      request.setAttribute("list", list);
      request.setAttribute("realist", realist);
      request.getRequestDispatcher("/admin/returnlist.jsp").forward(request, response);
   }

   // ���������������� buy���� ��ǰ�Ϸ�� ������Ʈ�ϱ�
   protected void refundcon(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int buyNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      PayVo vo1 = dao.ordernumselect(buyNum);
      int orderNum = vo1.getOrderNum();
      int n = dao.refundup(buyNum);
      ArrayList<BuyVo> list2=dao.detail(orderNum);
      String buystate="";
      boolean x=true;
      for(int i=0; i<list2.size(); i++) { //�ϳ��� ������ұ�ȯ��ǰ�������� pay�ȹٲ�
    	  buystate=list2.get(i).getState();
    	  if(buystate.equals("�������") || buystate.equals("��ȯ��û��") || buystate.equals("��ǰ��û��") || buystate.equals("��ǰ�Ϸ�")) {
    		  x=false;
    	  }
      }
      if(x=true) {//���� ���� ordernum�� buy���� ���� ��ȯ�Ϸᳪ ���ſϷ��� pay���̺��ǻ��µ� ���ſϷ�ιٲ��ش�.
    	  int a = dao.payconfirm2(orderNum);
      }
      
      
      //ArrayList<BuyVo> b= dao.detail(orderNum);
      //buy���̺� order���� ������ �װ͵��� ���¶� ��ȣ�� ������ 
      ArrayList<BuyVo> list = dao.refundlist();
      request.setAttribute("list", list);
      request.setAttribute("n", n);
      request.getRequestDispatcher("/admin/returnlist.jsp").forward(request, response);
   }

   // --------------------��ȯ------------------------
   // ���������� ��ȯ ���� �ѷ��ֱ�
   protected void buychange(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int orderNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      ArrayList<BuyVo> list = dao.detail(orderNum);
      ArrayList<ItemVo> itemlist = new ArrayList<>();
      System.out.println(list.size() + "");
      for (int i = 0; i < list.size(); i++) {
         BuyVo name = list.get(i);
         String code = name.getCode();
         ItemVo itemvo = dao.item(code);
         itemlist.add(itemvo);
      }
      request.setAttribute("list", list);
      request.setAttribute("itemlist", itemlist);
      request.setAttribute("orderNum", orderNum);
      request.getRequestDispatcher("/myshop/mybuy_change.jsp").forward(request, response);
   }

   // ��ȯ��ǰ�� �ٸ� ������ �ѷ��ֱ�
   protected void buychange2(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String checkList = request.getParameter("checkList");
      String[] checkArr = checkList.split(",");
      DemandDao dao = DemandDao.getInstance();
      ArrayList<ArrayList<ItemsizeVo>> list = new ArrayList<>();
      ArrayList<ArrayList<ItemVo>> list2= new ArrayList<>();
      ArrayList<Integer> buyNum=new ArrayList<>();
      int orderNum = Integer.parseInt(request.getParameter("orderNum"));
      for (int i = 0; i < checkArr.length; i++) {
         int buyNum1 = Integer.parseInt(checkArr[i]);
         list.add(dao.returnsize(buyNum1));
         list2.add(dao.namesch(buyNum1));
         buyNum.add(buyNum1);
      }
      System.out.println(list.size());
      request.setAttribute("list", list);
      request.setAttribute("list2", list2);
      request.setAttribute("checkList", checkList);
      request.setAttribute("orderNum", orderNum);
      request.setAttribute("buyNum", buyNum);
      request.getRequestDispatcher("/myshop/mybuy_change_choo.jsp").forward(request, response);
   }

   // ��ȯ�� ������ �޾ƿͼ� ������Ʈ ��Ű��
   protected void buychange3(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   String sizeList = request.getParameter("sizeList");
	   String buyList = request.getParameter("buyList");
	   
	   String[] sizeArr = sizeList.split("/"); //[ġ��,s] / [����,m] �̷��� /�� �߶���
	   String[] buyArr = buyList.split(", ");

	   buyArr[0]=buyArr[0].substring(1, buyArr[0].length());
	   buyArr[buyArr.length-1]=buyArr[buyArr.length-1].substring(0, buyArr[buyArr.length-1].length()-1);

	   DemandDao dao = DemandDao.getInstance();
	   ArrayList<String> list=new ArrayList<>(); 
	   ArrayList<String> size=new ArrayList<>();
	   ArrayList<String> name=new ArrayList<>();
	   ArrayList<Integer> buyNumList=new ArrayList<>();
	   int buyNum=0;
	   for(int i=0; i< sizeArr.length; i++) { //list�� [ġ��,s] [����,m] �迭�� �־���
		   String nameSize=sizeArr[i];
		   list.add(nameSize);
		   buyNumList.add(Integer.parseInt(buyArr[i]));
	   }
	   for(int a=0; a< list.size(); a++	) {
		   String[] nsArr= list.get(a).split(","); //list�� �ִ� ���� ,�� �����ش� [s] [ġ��] 
			String name1=nsArr[1]; //�ι�° ���� name�迭�� �ִ´�
			   name.add(name1);
			   size.add(nsArr[0]); //ù��° ���� size�迭�� �ִ´�
			   System.out.println(nsArr[0]);
			   System.out.println(nsArr[1]);
			   
	   }
	   PayVo vo=null;
	   BuyVo vo1=null;
	   int amount=0;
	   int n=0;
	   int x=0;
	   String code=null;
	   for(int b=0; b<size.size(); b++) { //size�� �̿��ؼ� buy���̺��� isize�� ��ۻ��¸� ������Ʈ ��Ų��.
		   n=dao.resize(size.get(b), buyNumList.get(b));
		   buyNum=buyNumList.get(b);
		   vo1=dao.buydetail(buyNum);
		   amount=vo1.getOrderAmount();
		   code=vo1.getCode();
		   System.out.println(amount+","+code+","+size.get(b));//�̰ſ־�������?
		   x=dao.reamount2(amount,code,size.get(b));//����ȵ�
		   vo=dao.ordernumselect(buyNum);
		   System.out.println(vo.getAddr()+vo.getState());
	   }
	   ArrayList<BuyVo> list1 = dao.detail(vo.getOrderNum());
	   int a=dao.refundup3(vo.getOrderNum());
	   System.out.println(a+"�̰ŵǳ���?");
	   request.setAttribute("list", list1);
	   request.setAttribute("a", a);
	   request.setAttribute("n", n);
	   request.getRequestDispatcher("/myshop/mybuy_detail.jsp").forward(request, response);
   }
   
// ���������������� buy���� ��ȯ�Ϸ�� ������Ʈ�ϱ�
   protected void refundcon2(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int buyNum = Integer.parseInt(request.getParameter("num"));
      
      String reason = request.getParameter("reason");
      DemandDao dao = DemandDao.getInstance();
      PayVo vo1 = dao.ordernumselect(buyNum);
      int orderNum = vo1.getOrderNum();
      int b=dao.refundup2(buyNum);//buy���̺��ǻ��¸� ��ȯ�Ϸ�ιٲ�
      //buy���̺��� buynum���� ���α�ȯ�� ������ �ڵ�� ������� ������ �̾ƿͼ�
      //itemsize���̺��� ������ ���ش�.
      //�ش� buynum�� ordernum�� ���� buynum���� ã�Ƽ� �װ͵��� ���¸� �̾ƿͼ� ��� '��ȯ�Ϸ�'��� pay���̺��� ���¸� ���ſϷ�� �Ѵ�.
      ArrayList<BuyVo> list2=dao.detail(orderNum);
      String buystate="";
      boolean x=true;
      for(int i=0; i<list2.size(); i++) { //�ϳ��� ������ұ�ȯ��ǰ�������� pay�ȹٲ�
    	  buystate=list2.get(i).getState();
    	  if(buystate.equals("�������") || buystate.equals("��ȯ��û��") || buystate.equals("��ǰ��û��") || buystate.equals("��ǰ�Ϸ�")) {
    		  x=false;
    	  }
      }
      if(x=true) {//���� ���� ordernum�� buy���� ���� ��ȯ�Ϸᳪ ���ſϷ��� pay���̺��ǻ��µ� ���ſϷ�ιٲ��ش�.
    	  int a = dao.payconfirm2(orderNum);
      }
      ArrayList<BuyVo> list = dao.refundlist();
      ArrayList<Return_buyVo> realist=new ArrayList<>();
      for(int i=0; i<list.size(); i++){
    	  BuyVo vo2=list.get(i);
    	  int buynum = vo2.getBuyNum();
    		Return_buyVo vo=dao.reason(buynum);
    		realist.add(vo);
      }
      
      //ArrayList<BuyVo> list = dao.refundlist();
      request.setAttribute("list", list);
      request.setAttribute("b", b);
      request.setAttribute("realist", realist);
      request.getRequestDispatcher("/admin/returnlist.jsp").forward(request, response);
   }
 //�����ڰ� ��ۻ��� ������Ʈ(����Ȯ��)-buy��//�׸��� pay�� ���ſϷ�
   protected void stateconfirm2(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int buyNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      int n = dao.payconfirm4(buyNum);
      //buy������Ʈ�ϸ鼭 ����üũ�ؼ���� ���ſϷᳪ ��ȯ�Ϸ�� pay�� ���ſϷ�� ������Ʈ�����ֱ�.
      PayVo vo1 = dao.ordernumselect(buyNum);
      int orderNum = vo1.getOrderNum();
      ArrayList<BuyVo> list2=dao.detail(orderNum);
      String buystate="";
      int x=0;
      for(int i=0; i<list2.size(); i++) { //�ϳ��� ������ұ�ȯ��ǰ�������� pay�ȹٲ�
    	  buystate=list2.get(i).getState();
    	  System.out.println(list2.get(i).getState()+"/");
    	  if(buystate.equals("�������") || buystate.equals("��ȯ��û��") || buystate.equals("�����") || buystate.equals("��ǰ��û��") || buystate.equals("��ǰ�Ϸ�")) {
    		  x=1;
    	  }
      }
      if(x==0) {//���� ���� ordernum�� buy���� ���� ��ȯ�Ϸᳪ ���ſϷ��� pay���̺��ǻ��µ� ���ſϷ�ιٲ��ش�.
    	  int a = dao.payconfirm2(orderNum);
      }
      request.setAttribute("n", n);
      request.getRequestDispatcher("demand.do?cmd=mylist").forward(request, response);
   }
   
}