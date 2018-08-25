package jj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.NoticeBoardDao;
import jh.NoticeBoardVo;
import mh.UsersDao;
import mh.UsersVo;
@WebServlet("/jj/item.do")
public class ItemController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("insert")) {
			insert(request,response);
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		String email = request.getParameter("email");
		System.out.println(email);
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=(pageNum-1)*10+10;		
		ItemDao dao=ItemDao.getInstance();
		int pageCnt=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pageNum-1)/10)*10+1;
		int endPage=startPage+9;
		if(endPage>pageCnt) {
			endPage=pageCnt;
		}		
		ArrayList<ItemVo> list=dao.list(startRow,endRow);
		
		int flag=0;
		if(!email.equals("")) {//�α����� �ϰ� ���� ���
			UsersDao usersDao=UsersDao.getInstance();
			UsersVo vo=usersDao.select(email);
			flag=vo.getFlag();//���������� ȸ������			
		}else {//�α����� ���ϰ� ���°��
			flag=1;
		}
		request.setAttribute("flag", flag);		
		request.setAttribute("list", list);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("email", email);
		request.getRequestDispatcher("/item/item_list.jsp").forward(request, response);		
	}
	

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		int price = Integer.parseInt(request.getParameter("price"));
		String itemName = request.getParameter("itemName");
		String description = request.getParameter("description");
		String imgType = request.getParameter("imgType");
		String isize = request.getParameter("isize");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String lookCode = request.getParameter("lookCode");
		String lookFront = request.getParameter("lookFront");
		String lookBack = request.getParameter("lookBack");

		ItemDao dao=ItemDao.getInstance();
		ItemVo vo = new ItemVo(code, price, itemName, description, imgType, null, isize, amount, 0, lookCode, lookFront, lookBack);
		
		if(dao.insert(vo)>0) {
			list(request,response);
		}else {
			System.out.println("��Ͻ���");
		}
		
		request.getRequestDispatcher("/item/item_list.jsp").forward(request, response);
	}
}