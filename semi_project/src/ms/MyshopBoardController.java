package ms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eb.QnaBoardVo;

@WebServlet("/myshopBoard.do")
public class MyshopBoardController extends HttpServlet {
	//public ServletContext sc = getServletContext();
	static HttpSession session = null;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		session = request.getSession();
		if (cmd != null && cmd.equals("reviewList")) {
			reviewList(request, response);
		}else if(cmd !=null && cmd.equals("reviewDetail")) {
			reviewDetail(request, response);
		}
		else if(cmd !=null && cmd.equals("qnaList")) {
			qnaList(request, response);
		}
	}
	
	//--------------------------------reviewBoard----------------------------------------------
	
	public void reviewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) session.getAttribute("email");
		int flag = (int) session.getAttribute("flag");
		System.out.println(email + " " + flag);
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		// int startRow=(pageNum-1)*10+1;
		// int endRow=startRow+9;
		int startRow = pageNum * 5 - 4;
		int endRow = startRow + 4;
		MyshopBoardDao dao = MyshopBoardDao.getInstance();
		ArrayList<ReviewBoardVo> list = dao.reveiwList(email, startRow, endRow);
		int pageCount = (int) Math.ceil(dao.getReviewCount(email)/ 5.0);
		int startPage = ((pageNum - 1) / 5 * 5) + 1;
		// int startPage = (pageNum/10)*10-9;
		// ����������ȣ
		// int endPage = startPage+pageNum%10-1;
		int endPage = startPage + 4;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		request.setAttribute("email", email);
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		
		MyshopBoardDao dao1 = MyshopBoardDao.getInstance();
		ArrayList<QnaBoardVo> list1=dao.qnaList(email, startRow, endRow);

		request.setAttribute("list1", list1);
		request.setAttribute("email1", email);
		request.setAttribute("pageCount1", pageCount);
		request.setAttribute("startPage1", startPage);
		request.setAttribute("endPage1", endPage);
		request.setAttribute("pageNum1", pageNum);
		request.getRequestDispatcher("/myshop/myreview_list.jsp").forward(request, response);
	}
		//request.getRequestDispatcher("/myshop/myreview_list.jsp").forward(request, response);
	public void reviewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int n=dao.hitup(num);
		ReviewBoardVo vo = dao.detail(num);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("/myshop/myreview_detail.jsp").forward(request, response);
	}
	
	public void ReviewDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String pageNum=request.getParameter("pageNum");
		String checkList=request.getParameter("checkList");
		String[] checkArray=checkList.split(",");
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		boolean bool=true;
		for(int i=0;i<checkArray.length;i++) {
			int num=Integer.parseInt(checkArray[i]);
			if(dao.delete(num)<0) {
				bool=false;
			}
		}		
		if(bool==false) {
			System.out.println("���� ����");
			return;
		}
		request.setAttribute("email", email);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/reviewBoard.do?cmd=list").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBoardVo vo1 = dao.detail(num);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String img = request.getParameter("img");
		
		ReviewBoardVo vo = new ReviewBoardVo(num, vo1.getName(), vo1.getEmail(), title, content, vo1.getHeight(), vo1.getWeight(), vo1.getHit(), vo1.getRegdate(), img, vo1.getItemImg(), vo1.getCode());
		int n = dao.update(vo);
		if(n>0) {
			reviewList(request, response);
		}
	}
	//----------------------------qnaBoard--------------------------------------------------
	
	protected void qnaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		
		
		String email = (String) session.getAttribute("email");
		int flag = (int) session.getAttribute("flag");
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow = pageNum * 5 - 4;
		int endRow = startRow + 4;
		MyshopBoardDao dao = MyshopBoardDao.getInstance();
		ArrayList<QnaBoardVo> list=dao.qnaList(email, startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getQnaCount(email)/5.0);
		int startPage=((pageNum-1)/5*5)+1;
		int endPage=startPage+4;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		request.setAttribute("list1", list);
		request.setAttribute("email1", email);
		request.setAttribute("flag1", flag);
		request.setAttribute("pageCount1", pageCount);
		request.setAttribute("startPage1", startPage);
		request.setAttribute("endPage1", endPage);
		request.setAttribute("pageNum1", pageNum);
		request.getRequestDispatcher("/myshop/myqna_list.jsp").forward(request, response);
	}
}
