package eb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeController_admin
 */
@WebServlet("/demand_admin.do")
public class DeController_admin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String cmd=request.getParameter("cmd");
	if(cmd!=null && cmd.equals("paylist")) {
		paylist(request,response);
	}else if(cmd!=null && cmd.equals("buylist")) {
		buylist(request,response);
	}else if(cmd!=null && cmd.equals("stateadmin")) {
		stateadmin(request,response);
	}else if(cmd!=null && cmd.equals("mylist")) {
		mylist(request,response);
	}else if(cmd!=null && cmd.equals("stateconfirm")) {
		stateconfirm(request,response);
	}else if(cmd!=null && cmd.equals("cancel")) {
		cancel(request,response);
	}else if(cmd!=null && cmd.equals("change")) {
		change(request,response);
	}else if(cmd!=null && cmd.equals("mydetail")) {
		mydetail(request,response);
	}else if(cmd!=null && cmd.equals("buychange")) {
		buychange(request,response);
	}else if(cmd!=null && cmd.equals("refund")) {
		refund(request,response);
	}else if(cmd!=null && cmd.equals("buychange2")) {
		buychange2(request,response);
	}else if(cmd!=null && cmd.equals("refund2")) {
		refund2(request,response);
	}
	}
	
	protected void stateadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderNum=Integer.parseInt(request.getParameter("num"));
		DemandDao dao=DemandDao.getInstance();
		int n=dao.update(orderNum);
		request.setAttribute("n", n);
		paylist(request, response);	
}
}
