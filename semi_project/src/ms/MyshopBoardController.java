package ms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eb.QnaBoardDao;
import eb.QnaBoardVo;
import mh.UsersDao;
import mh.UsersVo;

@WebServlet("/myshopBoard")
public class MyshopBoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if (cmd != null && cmd.equals("insert")) {
			reviewList(request, response);
		}else if (cmd != null && cmd.equals("insert")) {
			qnaList(request, response);
		}
	}
	
	public void reviewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		// int startRow=(pageNum-1)*10+1;
		// int endRow=startRow+9;
		int startRow = pageNum * 5 - 4;
		int endRow = startRow + 4;
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		ArrayList<ReviewBoardVo> list = dao.list(startRow, endRow);
		int pageCount = (int) Math.ceil(dao.getCount() / 5.0);
		int startPage = ((pageNum - 1) / 5 * 5) + 1;
		// int startPage = (pageNum/10)*10-9;
		// 끝페이지번호
		// int endPage = startPage+pageNum%10-1;
		int endPage = startPage + 4;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		ServletContext sc = getServletContext();
		String email = (String) sc.getAttribute("email");
		request.setAttribute("email", email);
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/board/review_list.jsp").forward(request, response);
	}
	
	protected void qnaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//ServletContext sc= getServletContext();
		String spageNum=request.getParameter("pageNum");
		String email=request.getParameter("email");
		UsersDao dao1=UsersDao.getInstance();
		UsersVo vo=null;
		String name="";
		int flag=0;
		if(!email.equals("")) {
			vo=dao1.select(email);
			name=vo.getName();
			flag=vo.getFlag();
		}
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		QnaBoardDao dao=QnaBoardDao.getInstance();
		ArrayList<QnaBoardVo> list=dao.list(startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		//sc.setAttribute("email", email);
		request.setAttribute("list", list);
		request.setAttribute("email", email);
		request.setAttribute("name", name);
		request.setAttribute("flag", flag);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("../board/qna_list.jsp").forward(request, response);
	}
}
