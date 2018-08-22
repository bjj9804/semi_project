package eb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.db.ConnectionPoolBean;


@WebServlet("/qnalist.do")
public class QnaBoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd!=null && cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("detail")) {
			detail(request,response);
		}
	}
	ConnectionPoolBean cp = null;
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
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
		request.getRequestDispatcher("/qna_list.jsp").forward(request, response);
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		request.setCharacterEncoding("utf-8");
		String snum=request.getParameter("num");
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String email=request.getParameter("email");
		int num=0;
		int grp=0;
		int lev=0;
		int step=0;
		int hit=0;
		if(snum!=null && !snum.equals("")) {//NUM이 있다는 소리
			num=Integer.parseInt(snum);
			grp=Integer.parseInt(request.getParameter("grp"));
			lev=Integer.parseInt(request.getParameter("lev"));
			step=Integer.parseInt(request.getParameter("step"));
		}
		QnaBoardVo vo=new QnaBoardVo(num, name, email, title, content, grp, lev, step, hit, null);
		QnaBoardDao dao=new QnaBoardDao();
		int n=dao.insert(vo);
		if(n>0) {
			request.getRequestDispatcher("/board/qna_list.jsp").forward(request, response);
		}
	}


	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 



	}	
}
