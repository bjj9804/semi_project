package eb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/qnalist.do")
public class QnaBoardController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		QnaBoardDao dao=new QnaBoardDao();
		ArrayList<QnaBoardVo> list=dao.list(startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}
}
