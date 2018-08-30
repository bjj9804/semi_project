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
		} else if (cmd != null && cmd.equals("refund2")) {
			refund2(request, response);
		} else if (cmd != null && cmd.equals("returnlist")) {
			returnlist(request, response);
		} else if (cmd != null && cmd.equals("refundcon")) {
			refundcon(request, response);
		}
	}
	//주문관리의 주문리스트
	protected void paylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DemandDao dao = DemandDao.getInstance();
		ArrayList<PayVo> list = dao.list();

		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/demand.jsp").forward(request, response);
	}
	//주문관리에서 주문번호 누르면 나오는 buy
	protected void buylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderNum = Integer.parseInt(request.getParameter("num"));
		DemandDao dao = DemandDao.getInstance();
		ArrayList<BuyVo> list = dao.detail(orderNum);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/demand_detail.jsp").forward(request, response);
	}
	//관리자가 배송완료를 누르면 상태가 바뀜
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
	//구매자가 배송상태 업데이트(구매확정)
	protected void stateconfirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderNum = Integer.parseInt(request.getParameter("num"));
		DemandDao dao = DemandDao.getInstance();
		int n = dao.payconfirm(orderNum);
		request.setAttribute("n", n);
		request.getRequestDispatcher("demand.do?cmd=mylist").forward(request, response);
	}

	// 배송되기전 구매취소버튼
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

	// 배송상태를 구매완료로 확정시켜줌
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

	// -------------------------환불--------------------------------
	// 구매자한테 환불할 상품 뿌려주기
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

	// 구매자가 선택한 환불상품과 이유 가져와서 저장하고 업데이트시키기
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
				System.out.println("상태변경,리턴테이블삽입 모두 성공");
			} else {
				System.out.println("뭔가잘못됨...");
				// dao.buy테이블에서 state를 반품신청으로 바꾸는 메소드(buyNum);
				// dao.return테이블로 인서트하는 메소드(buyNum,reasonArr[i]);
				// pay테이블의 state를 반품대기중으로 바꾸는 메소드

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

	// 관리자페이지에 교환/환불리스트뿌려주기
	protected void returnlist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DemandDao dao = DemandDao.getInstance();
		ArrayList<BuyVo> list = dao.refundlist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/returnlist.jsp").forward(request, response);
	}

	// 관리자페이지에서 buy상태 반품완료로 업데이트하기
	protected void refundcon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int buyNum = Integer.parseInt(request.getParameter("num"));
		DemandDao dao = DemandDao.getInstance();
		int n = dao.refundup(buyNum);
		PayVo vo1 = dao.ordernumselect(buyNum);
		int orderNum = vo1.getOrderNum();
		int a = dao.payconfirm(orderNum);
		ArrayList<BuyVo> list = dao.refundlist();
		request.setAttribute("list", list);
		request.setAttribute("n", n);
		request.setAttribute("a", a);
		request.getRequestDispatcher("/admin/returnlist.jsp").forward(request, response);
	}

	// --------------------교환------------------------
	// 구매자한테 교환 정보 뿌려주기
	protected void buychange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderNum = Integer.parseInt(request.getParameter("num"));
		DemandDao dao = DemandDao.getInstance();
		ArrayList<BuyVo> list = dao.detail(orderNum);
		ArrayList<ItemVo> itemlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			BuyVo name = list.get(i);
			String code = name.getCode();
			ItemVo itemvo = dao.item(code);
			itemlist.add(itemvo);
		}
		request.setAttribute("list", list);
		request.setAttribute("itemlist", itemlist);
		request.getRequestDispatcher("/myshop/mybuy_change.jsp").forward(request, response);
	}

	// 교환상품 받아와서 업데이트
	protected void buychange2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkList = request.getParameter("checkList");
		String[] checkArr = checkList.split(",");
		DemandDao dao = DemandDao.getInstance();
		ArrayList<ItemsizeVo> list = null;
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		for (int i = 0; i < checkArr.length; i++) {
			int buyNum = Integer.parseInt(checkArr[i]);
			list = dao.retrunsize(buyNum);
		}

		request.setAttribute("list", list);
		request.setAttribute("orderNum", orderNum);
		request.getRequestDispatcher("/myshop/mybuy_change_choo.jsp").forward(request, response);
	}

	// 교환상품의 다른 사이즈 뿌려주기
//	protected void buychange3(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {

//	}
}
