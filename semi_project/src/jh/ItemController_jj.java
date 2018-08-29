package jh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jh/item.do")
public class ItemController_jj extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("lookInsert")) {
			lookInsert(request,response);
		}else if(cmd!=null && cmd.equals("itemInsert")) {
			itemInsert(request,response);
		}else if(cmd!=null && cmd.equals("lookCode")) {
			lookCode(request,response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}
	}
	private void lookInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		String lookFront=request.getParameter("lookFront");
		String lookBack=request.getParameter("lookBack");
		
		ItemDao_jh dao=ItemDao_jh.getInstance();
		LookVo vo=new LookVo(lookCode, lookFront, lookBack);
		
		if(dao.lookInsert(vo)>0) {
			System.out.println("lookInsert성공");
		}else {
			System.out.println("lookInsert실패");
		}
		
		lookCode(request,response);
		//request.getRequestDispatcher("/item/item_insert.jsp").forward(request, response);
		
		
		
	}
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext(); // 어플리케이션에 대한 정보를 ServletContext 객체가 갖음
		String saveDir = context.getRealPath("Upload"); // 절대경로를 가져옴
		System.out.println("절대경로 >> " + saveDir);

		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";		
		
		
		String code=request.getParameter("code");
		String price=request.getParameter("price");
		String itemName=request.getParameter("itemName");
		String description=request.getParameter("description");
		String isize=request.getParameter("isize");
		String amount=request.getParameter("amount");				
		String lookList=request.getParameter("lookList");
		String[] lookArray=lookList.split(",");	
		
		
		ItemDao_jh dao=ItemDao_jh.getInstance();
		dao.itemInsert(code,price,itemName,description,isize,amount);
		
		int fileCount=Integer.parseInt(request.getParameter("fileCount"));
		for(int i=1;i<=fileCount;i++) {			
			String imgType=request.getParameter("imgType"+i);
			String imgSrc=request.getParameter("imgSrc"+i);
			dao.itemInsert2(imgType,code,imgSrc);			
		}
		
		for(int i=0;i<lookArray.length;i++) {
			dao.itemInsert3(lookArray[i],code);
		}
		
		list(request,response);
		
		
	}
	private void lookCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		jh.ItemDao_jh dao=ItemDao_jh.getInstance();
		ArrayList<LookVo> list=dao.getLookCode();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/item/item_insert.jsp").forward(request, response);
		
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao_jh dao=ItemDao_jh.getInstance();
		ArrayList<ItemDto> list=dao.list();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/item/item_list.jsp").forward(request, response);
		
		
	}
}
