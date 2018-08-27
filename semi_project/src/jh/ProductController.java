package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jj.ItemDao;
import jj.ItemVo;
@WebServlet("/jh/product.do")
public class ProductController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("detail")) {
			detail(request,response);
		}
		
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao itemdao=ItemDao.getInstance();
//		itemdao.list(startRow, endRow);
		
		
		String spageNum = request.getParameter("pageNum");
//		String email = request.getParameter("email");
//		System.out.println(email);
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=(pageNum-1)*10+10;		
		int pageCnt=(int)Math.ceil(itemdao.getCount()/10.0);
		int startPage=((pageNum-1)/10)*10+1;
		int endPage=startPage+9;
		if(endPage>pageCnt) {
			endPage=pageCnt;
		}		
		ArrayList<ItemVo> list=itemdao.list(startRow,endRow);
		
//		int flag=0;
//		if(!email.equals("")) {//로그인을 하고 들어온 경우
//			UsersDao usersDao=UsersDao.getInstance();
//			UsersVo vo=usersDao.select(email);
//			flag=vo.getFlag();//관리자인지 회원인지			
//		}else {//로그인을 안하고 들어온경우
//			flag=1;
//		}
//		request.setAttribute("flag", flag);		
		request.setAttribute("list", list);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
//		request.setAttribute("email", email);
		request.getRequestDispatcher("/product/product_list.jsp").forward(request, response);		
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	
	}
	
}


















