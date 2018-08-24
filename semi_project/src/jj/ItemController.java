package jj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.NoticeBoardDao;
import jh.NoticeBoardVo;

public class ItemController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("list")) {
			list(request,response);
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
}