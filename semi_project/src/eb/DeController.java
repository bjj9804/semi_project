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
			DemandDao dao=DemandDao.getInstance();
			ArrayList<DemandVo> list=dao.list();
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("../admin/demand.jsp").forward(request, response);
		}
	}

