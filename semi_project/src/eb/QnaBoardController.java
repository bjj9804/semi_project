package eb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jh.NoticeBoardDao;
import jh.NoticeBoardVo;
import mh.UsersDao;
import mh.UsersVo;


@WebServlet("/eb/qnalist.do")
public class QnaBoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd!=null && cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("detail")) {
			detail(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(request,response);
		}else if(cmd!=null && cmd.equals("update")) {
			update(request,response);
		}
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//ServletContext sc= getServletContext();
		String spageNum=request.getParameter("pageNum");
		String originalEmail=request.getParameter("email");
		UsersDao dao1=UsersDao.getInstance();
		UsersVo vo=null;
		String name="";
		int flag=0;
		if(!originalEmail.equals("")) {
			vo=dao1.select(originalEmail);
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
		request.setAttribute("originalEmail", originalEmail);
		request.setAttribute("name", name);
		request.setAttribute("flag", flag);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("../board/qna_list.jsp").forward(request, response);
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		UsersDao dao1=UsersDao.getInstance();
		String email=request.getParameter("email");
		UsersVo vo1=dao1.select(email);
		String name=vo1.getName();
		String snum=request.getParameter("num");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
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
		QnaBoardDao dao=QnaBoardDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			list(request, response);
		}
	}
	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		int num=Integer.parseInt(request.getParameter("num"));
		String email=request.getParameter("email");
		String writer=request.getParameter("writer");
		System.out.println(email+","+writer);
		int flag=Integer.parseInt(request.getParameter("flag"));
		if(flag==0||writer.equals(email)) {//관리자이거나 작성자와 email이 같다면 상세내용 보여주기
			QnaBoardDao dao=QnaBoardDao.getInstance();
			QnaBoardVo vo=dao.detail(num);
			if(dao.hitup(num)>0) {
				System.out.println("상세내용보기 성공");
			}else {
				System.out.println("상세내용보기 실패!!");
			}
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("../board/qna_detail.jsp").forward(request, response);	
		}else {//제3자가 들어왔을때 되돌아가게하기
			request.getRequestDispatcher("/eb/qnalist.do?cmd=list&email="+email).forward(request, response);
		}
		
	}	
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		int num=Integer.parseInt(request.getParameter("num"));
		QnaBoardDao dao=QnaBoardDao.getInstance();
		int n=dao.delete(num);
		
		//ServletContext sc=getServletContext();
		String email=dao.detail(num).getEmail();
		System.out.println(email);
		if(n>0) {
			request.getRequestDispatcher("/eb/qnalist.do?cmd=list&email="+email).forward(request, response);
		}
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		int num=Integer.parseInt(request.getParameter("num"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		QnaBoardDao dao = QnaBoardDao.getInstance();
		QnaBoardVo vo = dao.detail(num);
		String email = vo.getEmail(); 
		//ServletContext sc=getServletContext();
		//String email=(String)sc.getAttribute("email");
		if(dao.update(num,title,content)>0) {
			request.getRequestDispatcher("/eb/qnalist.do?cmd=list&email="+email).forward(request, response);
		}else {
			System.out.println("수정실패");
		}
	}
}
