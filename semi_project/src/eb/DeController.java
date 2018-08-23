package eb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mh.UsersDao;
import mh.UsersVo;

@WebServlet("/Demand.do")
public class DeController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd!=null && cmd.equals("delist")) {
			delist(request,response);
		}
	}
		protected void delist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			String spageNum=request.getParameter("pageNum");
			String email=request.getParameter("email");
			System.out.println(email+"111");
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
			request.getRequestDispatcher("../board/demand.jsp").forward(request, response);
		}
		
	}

