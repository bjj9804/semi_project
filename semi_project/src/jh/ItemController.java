package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/jh/item.do")
public class ItemController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("lookInsert")) {
			lookInsert(request,response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("itemInsert")) {
			itemInsert(request,response);
		}else if(cmd!=null && cmd.equals("getLookCode")) {
			getLookCode(request,response);
		}
	}
	private void lookInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		String lookFront=request.getParameter("lookFront");
		String lookBack=request.getParameter("lookBack");
		
		ItemDao dao=ItemDao.getInstance();
		LookVo vo=new LookVo(lookCode, lookFront, lookBack);
		if(dao.lookInsert(vo)>0) {
			System.out.println("look테이블insert성공");
		}else {
			System.out.println("look테이블insert실패");
		}
		
		
	}
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String price=request.getParameter("price");
		String itemName=request.getParameter("itemName");
		String description=request.getParameter("description");
		String isize=request.getParameter("isize");
		String amount=request.getParameter("amount");
		String lookCodeList=request.getParameter("lookCodeList");
		
		System.out.println(lookCodeList);
		
		int fileCount=Integer.parseInt(request.getParameter("fileCount"));
		
		ArrayList<String[]> list=new ArrayList<>();
		for(int i=1;i<=fileCount;i++) {
			String imgSrc=request.getParameter("imgSrc"+i);
			String imgType=request.getParameter("imgType"+i);
			System.out.println(imgSrc+","+imgType);
			String[] img= {imgSrc,imgType};
			list.add(img);
		}
		
		ItemDao dao=ItemDao.getInstance();
		
		
	}
	private void getLookCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao dao=ItemDao.getInstance();
		ArrayList<LookVo> list=dao.getLookCode();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/item/item_insert.jsp").forward(request, response);
		
		
	}
	
}
