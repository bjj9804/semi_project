package jh;

import java.io.IOException;

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
		}else if(cmd!=null && cmd.equals("itemInsert")) {
			itemInsert(request,response);
		}
	}
	private void lookInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		String lookFront=request.getParameter("lookFront");
		String lookBack=request.getParameter("lookBack");
		
		System.out.println(lookCode+","+lookFront+","+lookBack);
		
		
	}
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String price=request.getParameter("price");
		String itemName=request.getParameter("itemName");
		String description=request.getParameter("description");
		String isize=request.getParameter("isize");
		String amount=request.getParameter("amount");
		String lookCode=request.getParameter("lookCode");
		String imgSrc=request.getParameter("imgSrc");
		String imgType=request.getParameter("imgType");
		
		System.out.println(code+","+price+","+itemName+","+description+","+isize+","+amount+","+lookCode);
		System.out.println(imgSrc);
		System.out.println(imgType);
		
	}
	
	
}
