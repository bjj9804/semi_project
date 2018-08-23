package jh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mh.UsersDao;
import mh.UsersVo;
@WebServlet("/jh/notice.do")
public class NoticeBoardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");		
		String contextPath=getServletContext().getContextPath();
		
		if(cmd!=null && cmd.equals("list")) {
			listAll(request,response);
		}else if(cmd!=null && cmd.equals("detail")) {
			detail(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(request,response);
		}else if(cmd!=null && cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd!=null && cmd.equals("update")) {
			update(request,response);
		}
		
	}
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=(pageNum-1)*10+10;		
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		int pageCnt=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pageNum-1)/10)*10+1;
		int endPage=startPage+9;
		if(endPage>pageCnt) {
			endPage=pageCnt;
		}		
		ArrayList<NoticeBoardVo> list=dao.list(startRow,endRow);
		
		int flag=0;
		if(!email.equals("")) {//로그인을 하고 들어온 경우
			UsersDao usersDao=UsersDao.getInstance();
			UsersVo vo=usersDao.select(email);
			flag=vo.getFlag();//관리자인지 회원인지			
		}else {//로그인을 안하고 들어온경우
			flag=1;
		}		
		request.setAttribute("flag", flag);		
		request.setAttribute("list", list);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("email", email);
		request.getRequestDispatcher("/board/notice_list.jsp").forward(request, response);
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		int flag=Integer.parseInt(request.getParameter("flag"));
		String cmd1=request.getParameter("cmd1");	
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		NoticeBoardVo vo=dao.select(num);
		request.setAttribute("vo", vo);
		request.setAttribute("flag", flag);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		if(cmd1.equals("det_update")) {
			request.getRequestDispatcher("/board/notice_update.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/board/notice_detail.jsp").forward(request, response);
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String pageNum=request.getParameter("pageNum");
		String checkList=request.getParameter("checkList");
		String[] checkArray=checkList.split(",");
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		boolean bool=true;
		for(int i=0;i<checkArray.length;i++) {
			int num=Integer.parseInt(checkArray[i]);
			if(dao.delete(num)<0) {
				bool=false;
			}
		}		
		if(bool==false) {//뭔가삭제에 실패했다......
			System.out.println("삭제 실패");
			return;
		}
		request.setAttribute("email", email);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/jh/notice.do?cmd=list").forward(request, response);
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String email=request.getParameter("email");
		
		UsersDao usersDao=UsersDao.getInstance();
		UsersVo usersvo=usersDao.select(email);
		
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		NoticeBoardVo vo=new NoticeBoardVo(0, usersvo.getName(), email, title, content, 0, null);
		if(dao.insert(vo)>0) {
			listAll(request,response);
		}else {
			System.out.println("작성실패");
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		int flag=Integer.parseInt(request.getParameter("flag"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		NoticeBoardDao dao=NoticeBoardDao.getInstance();
		if(dao.update(num,title,content)>0) {			
			request.getRequestDispatcher("/jh/notice.do?cmd=detail&cmd1='detail'&flag="+flag+"&pageNum="+pageNum).forward(request, response);
		}else {
			System.out.println("작성실패");
		}
		
	}
}
