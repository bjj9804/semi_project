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
      request.setAttribute("n", n);
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
      // String email = vo.getEmail();
      // System.out.println(vo.getEmail()+"111");

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
      PayVo vo = dao.selectview(orderNum);
      String state1 = vo.getState();
      request.setAttribute("list", list);
      request.setAttribute("state1", state1);
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
      request.setAttribute("list", list);
      request.getRequestDispatcher("/admin/returnlist.jsp").forward(request, response);
   }

   // ���������������� buy���� ��ǰ�Ϸ�� ������Ʈ�ϱ�
   protected void refundcon(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      int buyNum = Integer.parseInt(request.getParameter("num"));
      DemandDao dao = DemandDao.getInstance();
      int n = dao.refundup(buyNum);
      PayVo vo1 = dao.ordernumselect(buyNum);
      int orderNum = vo1.getOrderNum();
      int a = dao.payconfirm(orderNum);
      //ArrayList<BuyVo> b= dao.detail(orderNum);
      //buy���̺� order���� ������ �װ͵��� ���¶� ��ȣ�� ������ 
      ArrayList<BuyVo> list = dao.refundlist();
      request.setAttribute("list", list);
      request.setAttribute("n", n);
      request.setAttribute("a", a);
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
	   }
	   for(int b=0; b<size.size(); b++) { //size�� �̿��ؼ� buy���̺��� isize�� ��ۻ��¸� ������Ʈ ��Ų��.
		   int n=dao.resize(size.get(b), buyNumList.get(b));
		   buyNum=buyNumList.get(b);
		   System.out.println(buyNumList.get(b)+","+size.get(b));
	   }
	   PayVo vo=dao.ordernumselect(buyNum);
	   ArrayList<BuyVo> list1 = dao.detail(vo.getOrderNum());
	  request.setAttribute("list", list1);
	   request.getRequestDispatcher("/myshop/mybuy_detail.jsp").forward(request, response);

   }
}