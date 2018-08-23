package ms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewBoard.do")
public class ReviewBoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if (cmd != null && cmd.equals("insert")) {
			insert(request, response);
		} else if (cmd != null && cmd.equals("list")) {
			list(request, response);
		} else if (cmd != null && cmd.equals("detail")) {
			detail(request, response);
		}else if (cmd != null && cmd.equals("searchlist")) {
			searchlist(request, response);
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String name = request.getParameter("name");
		int height = Integer.parseInt(request.getParameter("height"));
		int weight = Integer.parseInt(request.getParameter("weight"));
		String email = request.getParameter("email");
		ReviewBoardVo vo = new ReviewBoardVo(0, name, email, title, content, height, weight, 0, null);
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int n = dao.insert(vo);
		if (n > 0) {
			list(request, response);
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		// int startRow=(pageNum-1)*10+1;
		// int endRow=startRow+9;
		int startRow = pageNum * 10 - 9;
		int endRow = startRow + 9;
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		ArrayList<ReviewBoardVo> list = dao.list(startRow, endRow);
		System.out.print("dbÀÚ·á °¡²¿¿È " + list.size());
		int pageCount = (int) Math.ceil(dao.getCount() / 10.0);
		int startPage = ((pageNum - 1) / 10 * 10) + 1;
		// int startPage = (pageNum/10)*10-9;
		// ³¡ÆäÀÌÁö¹øÈ£
		// int endPage = startPage+pageNum%10-1;
		int endPage = startPage + 9;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		System.out.println("¿©±â¿È");
		request.getRequestDispatcher("/board/review_list.jsp").forward(request, response);
	}
	
	public void searchlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int height = 0;
		int weight = 0;
		if(request.getParameter("height")!=null || request.getParameter("weight")!=null){
			height = Integer.parseInt(request.getParameter("height"));
			weight = Integer.parseInt(request.getParameter("weight"));
			request.getSession().setAttribute("height", height);
			request.getSession().setAttribute("weight", weight);
		}else if(request.getSession().getAttribute("height") != null || request.getSession().getAttribute("weight") != null){
			height = (Integer)request.getSession().getAttribute("height");
			weight = (Integer)request.getSession().getAttribute("weight");
		}
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = pageNum * 10 - 9;
		int endRow = startRow + 9;
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		ArrayList<ReviewBoardVo> list = dao.searchlist(startRow, endRow, height, weight);
		int pageCount = 0;
		if(height!=0 || weight!=0) {
			pageCount = (int)Math.ceil(dao.getSearchCount(height, weight)/10.0);
		}else {
			pageCount = (int)Math.ceil(dao.getCount()/10.0);
		}
		int startPage = ((pageNum - 1) / 10 * 10) + 1;
		int endPage = startPage + 9;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		System.out.println("¿©±â¿È");
		request.getRequestDispatcher("/board/review_search.jsp").forward(request, response);
	}

	public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int n=dao.hitup(num);
		ReviewBoardVo vo = dao.detail(num);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("/board/review_detail.jsp").forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int n = dao.delete(num);
		if(n>0) {
			list(request, response);
		}
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBoardVo vo = dao.detail(num);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int n = dao.update();
		if(n>0) {
			list(request, response);
		}
	}
	
}
