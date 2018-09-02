package jh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ms.ItemImgVo;
import ms.ReviewBoardDao;
import ms.ReviewBoardVo;
@WebServlet("/jh/product.do")
public class ProductController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("lookList")) {
			lookList(request,response);
		}else if(cmd!=null && cmd.equals("showLookItem")) {
			showLookItem(request,response);
		}else if(cmd!=null && cmd.equals("itemDetail")) {
			itemDetail(request,response);
		}else if(cmd!=null && cmd.equals("showItem")) {
			showItem(request,response);
		}else if(cmd!=null && cmd.equals("itemDetailSearch")) {
			itemDetailSearch(request,response);
		}
		
	}
	
	private void lookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd1=request.getParameter("cmd1");
		ProductDao dao=ProductDao.getInstance();
		ArrayList<LookVo> list=dao.lookList(cmd1);		
		request.setAttribute("cmd", cmd1);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/product/product_look.jsp").forward(request, response);
	}
	private void showLookItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		ItemDao dao=ItemDao.getInstance();
		
		LookVo lvo=dao.getLook(lookCode);
		
		Object[] ob;
		ArrayList<Object[]> obList=new ArrayList<>();
		
		ArrayList<LookItemVo> list=dao.getLookItem(lookCode);
		for(LookItemVo vo:list) {
			ob=new Object[2];
			String code=vo.getCode();
			//ArrayList<ItemImgVo> imgList=dao.getItemImg(code);
			String[] img=dao.getSsum(code);
			ob[0]=code;
			ob[1]=img;
			obList.add(ob);
		}
		request.setAttribute("list", obList);
		request.setAttribute("lvo", lvo);
		
		
		request.getRequestDispatcher("/product/product_lookItem.jsp").forward(request, response);
	}
	private void itemDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		System.out.println(code);
		ItemDao dao=ItemDao.getInstance();
		ItemVo itemVo=dao.getItem(code);
		ArrayList<ItemImgVo> imgList=dao.getItemImg(code);
		ArrayList<ItemsizeVo> sizeList=dao.getItemsize(code);
		
		request.setAttribute("code", code);
		request.setAttribute("item", itemVo);
		request.setAttribute("img", imgList);
		request.setAttribute("size", sizeList);
		
		request.getRequestDispatcher("/product/product_view.jsp").forward(request, response);
		
	
	}
	private void itemDetailSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sheight = request.getParameter("height");
		String sweight = request.getParameter("weight");
		int height = 0;
		int weight = 0;
		if (sheight != null || sweight != null) {
			height = Integer.parseInt(sheight);
			weight = Integer.parseInt(sweight);
		}
		if (request.getParameter("height") == null || request.getParameter("weight") == null) {
			height = 0;
			weight = 0;
		} else if (Integer.parseInt(request.getParameter("height")) != 0 || Integer.parseInt(request.getParameter("weight")) != 0) {
			height = Integer.parseInt(request.getParameter("height"));
			weight = Integer.parseInt(request.getParameter("weight"));
			request.getSession().setAttribute("height", height);
			request.getSession().setAttribute("weight", weight);
		}
		String code=request.getParameter("code");
		System.out.println(code + "11" + height + "11" + weight);
		ReviewBoardDao redao = ReviewBoardDao.getInstance();
		ArrayList<ReviewBoardVo> list = redao.searchlist(1, 10, height, weight, code);
		JSONArray arr = new JSONArray();
		for(ReviewBoardVo vo : list){
			JSONObject obj = new JSONObject();
			obj.put("num", vo.getNum());
			obj.put("name", vo.getName());
			obj.put("title", vo.getTitle());
			obj.put("content", vo.getContent());
			obj.put("height", vo.getHeight());
			obj.put("weight", vo.getWeight());
			obj.put("hit", vo.getHit());
			obj.put("img", vo.getImg());
			obj.put("itemImg", vo.getItemImg());
			obj.put("code", vo.getCode());
			arr.add(obj);
		}
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(arr.toString());
		pw.close();
	
	}
	private void showItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mark=request.getParameter("mark");
		ProductDao pdao=ProductDao.getInstance();
		ArrayList<ItemVo> list=pdao.getItem(mark);
		ItemDao idao=ItemDao.getInstance();
		
		Object[] ob;
		ArrayList<Object[]> obList=new ArrayList<>();
		for(ItemVo vo:list) {
			ob=new Object[2];
			String code=vo.getCode();
			String[] img=idao.getSsum(code);
			ob[0]=code;
			ob[1]=img;
			obList.add(ob);
		}
		
		request.setAttribute("list", obList);
	
		
		request.getRequestDispatcher("/product/product_lookItem.jsp").forward(request, response);
	}
}


















