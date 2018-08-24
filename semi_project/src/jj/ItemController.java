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
			System.out.println("등록실패");
		}
		
		request.getRequestDispatcher("/item/item_list.jsp").forward(request, response);
	}
}