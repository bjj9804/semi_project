package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.ItemDao_jh;
import jj.ItemVo;
import ms.ItemImgVo;
@WebServlet("/jh/product.do")
public class ProductController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("lookList")) {
			lookList(request,response);
		}else if(cmd!=null && cmd.equals("detail")) {
			detail(request,response);
		}
		
	}
	
	private void lookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao_jh itemdao=ItemDao_jh.getInstance();
		
		ArrayList<ItemDto> list = itemdao.list();
			 
		request.setAttribute("list", list);
		request.getRequestDispatcher("/product/product_look.jsp").forward(request, response);
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		ProductDao pdao=ProductDao.getInstance();
		jh.ItemVo itemvo=pdao.getItemVo(code);
		ItemImgVo itemimgvo=pdao.getItemImgVo(code);
		ItemsizeVo itemsizevo=pdao.getItemsizeVo(code);
		LookVo lookvo=pdao.getLookVo(code);
		
		request.setAttribute("itemvo", itemvo);
		request.setAttribute("itemimgvo", itemimgvo);
		request.setAttribute("itemsizevo", itemsizevo);
		request.setAttribute("lookvo", lookvo);
		request.getRequestDispatcher("/product/item_detail.jsp").forward(request, response);		

	}
	
}


















