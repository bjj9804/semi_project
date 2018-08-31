package jh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ms.ItemImgVo;
@WebServlet("/jh/item.do")
public class ItemController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");	
		
		
		if(cmd!=null && cmd.equals("lookInsert")) {
			lookInsert(request,response);
		}else if(cmd!=null && cmd.equals("itemInsert")) {
			itemInsert(request,response);
		}else if(cmd!=null && cmd.equals("itemDetail")) {
			itemDetail(request,response);
		}else if(cmd!=null && cmd.equals("itemUpdate")) {
			itemUpdate(request,response);
		}else if(cmd!=null && cmd.equals("imgUpdate")) {
			imgUpdate(request,response);
		}else if(cmd!=null && cmd.equals("imgDelete")) {
			imgDelete(request,response);
		}else if(cmd!=null && cmd.equals("amountUpdate")) {
			amountUpdate(request,response);
		}else if(cmd!=null && cmd.equals("lookCode")) {
			lookCode(request,response);
		}else if(cmd!=null && cmd.equals("lookDetail")) {
			lookDetail(request,response);
		}else if(cmd!=null && cmd.equals("lookDelete")) {
			lookDelete(request,response);
		}else if(cmd!=null && cmd.equals("runway")) {
			runway(request,response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("lookItemDelete")) {
			lookItemDelete(request,response);
		}else if(cmd!=null && cmd.equals("lookItemInsert")) {
			lookItemInsert(request,response);
		}else if(cmd!=null && cmd.equals("lookImgUpdate")) {
			lookImgUpdate(request,response);
		}else if(cmd!=null && cmd.equals("checkCode")) {
			checkCode(request,response);
		}else if(cmd!=null && cmd.equals("lookCodeUpdate")) {
			lookCodeUpdate(request,response);
		}
	}
	private void lookCodeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		String lookCode1=request.getParameter("lookCode1");
		ItemDao dao=ItemDao.getInstance();
		int n=dao.lookCodeUpdate(lookCode,lookCode1);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();		
					
		
	}
	
	private void runway(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		ItemDao dao=ItemDao.getInstance();
		int n=dao.runway(lookCode);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();		
					
		
	}
	private void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		ItemDao dao=ItemDao.getInstance();
		String str=dao.checkCode(code);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(str.equals("notExist")) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();		
					
		
	}
	private void lookInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir=getServletContext().getRealPath("/itemFile");
		
		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";

		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());
		
			String lookCode=mr.getParameter("lookCode");
			String lookFront=mr.getFilesystemName("lookFront");
			String lookBack=mr.getFilesystemName("lookBack");
			
			ItemDao dao=ItemDao.getInstance();
			LookVo vo=new LookVo(lookCode, lookFront, lookBack);
			
			if(dao.lookInsert(vo)>0) {
				System.out.println("lookInsert성공");
			}else {
				System.out.println("lookInsert실패");
			}
			
			lookCode(request,response);
			//request.getRequestDispatcher("/item/item_insert.jsp").forward(request, response);
					
		}
	}
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir=getServletContext().getRealPath("itemFile");
		
		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";

		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());		
		
			String code=mr.getParameter("code");
			String price=mr.getParameter("price");
			String itemName=mr.getParameter("itemName");
			String description=mr.getParameter("description");
			String isize=mr.getParameter("isize");
			String amount=mr.getParameter("amount");				
			String lookList=mr.getParameter("lookList");
			String[] lookArray=lookList.split(",");	
			
			
			ItemDao dao=ItemDao.getInstance();
			dao.itemInsert(code,price,itemName,description,isize,amount);
			
			int fileCount=Integer.parseInt(mr.getParameter("fileCount"));
			for(int i=1;i<=fileCount;i++) {			
				String imgType=mr.getParameter("imgType"+i);
				String imgScr=mr.getFilesystemName("imgScr"+i);
				dao.itemInsert2(imgType,code,imgScr);			
			}
			
			if(!lookList.equals("")&& lookList!=null) {
				for(int i=0;i<lookArray.length;i++) {
					dao.itemInsert3(lookArray[i],code);
				}
			}
			list(request,response);
		
		}
	}
	private void lookCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		jh.ItemDao dao=ItemDao.getInstance();
		ArrayList<LookVo> list=dao.getLookCode();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/item/item_insert.jsp").forward(request, response);
		
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao dao=ItemDao.getInstance();
		ArrayList<ItemDto> list=dao.list();
		ArrayList<LookVo> list1=dao.getLookCode();
		request.setAttribute("list", list);
		request.setAttribute("list1", list1);
		request.getRequestDispatcher("/item/item_list.jsp").forward(request, response);
		
		
	}
	private void itemDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		System.out.println(code);
		ItemDao dao=ItemDao.getInstance();
		ItemVo itemvo= dao.getItem(code);
		ArrayList<ItemImgVo> imgList=dao.getItemImg(code);		
		
		request.setAttribute("itemvo", itemvo);
		request.setAttribute("imgList", imgList);
		request.getRequestDispatcher("/item/item_update.jsp").forward(request, response);
		
		
	}
	private void itemUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String code1=request.getParameter("code1");
		String price=request.getParameter("price");
		String itemName=request.getParameter("itemName");
		String description=request.getParameter("description");
		
		ItemDao dao=ItemDao.getInstance();
		int n=dao.itemUpdate(code,code1,price,itemName,description);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();
		
	}
	private void imgUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String saveDir=getServletContext().getRealPath("itemFile");
		
		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";
		
		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());	
		
			//String code=mr.getParameter("code");
			int fileCount=Integer.parseInt(mr.getParameter("fileCount"));
			ItemDao dao=ItemDao.getInstance();
			for(int i=1;i<=fileCount;i++) {			
				String imgType=mr.getParameter("imgType"+i);
				String imgScr= mr.getFilesystemName("imgScr"+i);
				dao.itemInsert2(imgType,code,imgScr);			
			}
			request.setAttribute("code", code);
			itemDetail(request,response);
		}
		
	}
	private void amountUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String isize=request.getParameter("isize");
		String amount=request.getParameter("amount");
		
		ItemDao dao=ItemDao.getInstance();
		int n=dao.amountUpdate(code,isize,amount);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();
		
	}
	
	private void imgDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String imgType=request.getParameter("imgType");
		
		ItemDao dao=ItemDao.getInstance();
		dao.imgDelete(code,imgType);
		
		request.setAttribute("code", code);
		itemDetail(request,response);
			
	}
	private void lookDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		ItemDao dao=ItemDao.getInstance();
		LookVo lvo=dao.getLook(lookCode);
		ArrayList<LookItemVo> list=dao.getLookItem(lookCode);
		
		request.setAttribute("lvo", lvo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/item/look_update.jsp").forward(request, response);
			
	}
	private void lookDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		System.out.println(lookCode);
		
		ItemDao dao=ItemDao.getInstance();
		int n=dao.lookDelete(lookCode);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();
			
	}
	private void lookItemDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String lookCode=request.getParameter("lookCode");
		
		ItemDao dao=ItemDao.getInstance();
		int n=dao.lookItemDelete(code,lookCode);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();
			
	}
	
	private void lookItemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String lookCode=request.getParameter("lookCode");
		
		ItemDao dao=ItemDao.getInstance();
		int n=dao.itemInsert3(lookCode,code);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8'?>");
		pw.print("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();
			
	}
	private void lookImgUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookCode=request.getParameter("lookCode");
		String saveDir=getServletContext().getRealPath("itemFile");
		
		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";

		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());	
		
			String lookFront=mr.getFilesystemName("lookFront");
			String lookBack=mr.getFilesystemName("lookBack");
			String check=mr.getParameter("check");
			ItemDao dao=ItemDao.getInstance();
			
			if(check.equals("1")) {//lookFront가 들어온 경우
				lookBack="";
			}else {//lookFront가 들어온 경우
				lookFront="";
			}
			dao.lookUpdate(lookCode,lookFront,lookBack,check);
			
			request.setAttribute("lookCode", lookCode);
			lookDetail(request, response);
		}
	}
}
