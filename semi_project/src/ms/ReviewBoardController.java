package ms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mh.UsersDao;
import mh.UsersVo;


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
		}else if (cmd != null && cmd.equals("update")) {
			update(request, response);
		}else if (cmd != null && cmd.equals("delete")) {
			delete(request, response);
		}
	}

	/*private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		UsersDao udao = UsersDao.getInstance();
		UsersVo uvo = udao.select(email);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int height = Integer.parseInt(request.getParameter("height"));
		int weight = Integer.parseInt(request.getParameter("weight"));
		String img = request.getParameter("img");
		System.out.println(email + title + content + height + weight + img);
		ReviewBoardVo vo = new ReviewBoardVo(0, uvo.getName(), email, title, content, height, weight, 0, null, img);
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int n = dao.insert(vo);
		if (n > 0) {
			list(request, response);
		}
	}*/
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext(); // 어플리케이션에 대한 정보를 ServletContext 객체가 갖음
		String saveDir = context.getRealPath("Upload"); // 절대경로를 가져옴
		System.out.println("절대경로 >> " + saveDir);

		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";

		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());
			String img = mr.getFilesystemName("img");
			String email = mr.getParameter("email");
			UsersDao udao = UsersDao.getInstance();
			UsersVo uvo = udao.select(email);
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			int height = Integer.parseInt(mr.getParameter("height"));
			int weight = Integer.parseInt(mr.getParameter("weight"));
			String code = mr.getParameter("code");
			ReviewBoardDao dao = ReviewBoardDao.getInstance();
			ItemImgVo ivo = dao.getItemImg(code);
			String itemImg = ivo.getImgSrc();
			ReviewBoardVo vo = new ReviewBoardVo(0, uvo.getName(), email, title, content, height, weight, 0, null, img, itemImg, code);
			int n = dao.insert(vo);
			if (n > 0) {
				list(request, response);
			}else {
				System.out.println("안됨");
			}
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		System.out.println(spageNum);
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
		int pageCount = (int) Math.ceil(dao.getCount() / 10.0);
		int startPage = ((pageNum - 1) / 10 * 10) + 1;
		// int startPage = (pageNum/10)*10-9;
		// 끝페이지번호
		// int endPage = startPage+pageNum%10-1;
		int endPage = startPage + 9;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		String email = request.getParameter("email");
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
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/board/review_list.jsp").forward(request, response);
	}
	
	public void searchlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int height = 0;
		int weight = 0;
		if(Integer.parseInt(request.getParameter("height"))!=0 || Integer.parseInt(request.getParameter("weight"))!=0){
			height = Integer.parseInt(request.getParameter("height"));
			weight = Integer.parseInt(request.getParameter("weight"));
			request.getSession().setAttribute("height", height);
			request.getSession().setAttribute("weight", weight);
		}else if(Integer.parseInt(request.getParameter("height"))!=0 || Integer.parseInt(request.getParameter("height"))!=0){
			height = (Integer)request.getSession().getAttribute("height");
			weight = (Integer)request.getSession().getAttribute("weight");
		}
		System.out.println(height  + " " + weight);
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
		request.getRequestDispatcher("/board/review_searchList.jsp").forward(request, response);
	}

	public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String cmd1 = request.getParameter("cmd1");
		int flag=Integer.parseInt(request.getParameter("flag"));
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int n=dao.hitup(num);
		ReviewBoardVo vo = dao.detail(num);
		request.setAttribute("vo", vo);
		request.setAttribute("flag", flag);
		request.setAttribute("pageNum", request.getAttribute("pageNum"));
		if(cmd1.equals("update")) {
			request.getRequestDispatcher("/board/review_update.jsp").forward(request, response);
		}else if(cmd1.equals("myboard")) {
			
		}else {
			request.getRequestDispatcher("/board/review_detail.jsp").forward(request, response);
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			System.out.println("삭제 실패");
			return;
		}
		request.setAttribute("email", email);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/reviewBoard.do?cmd=list").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext(); // 어플리케이션에 대한 정보를 ServletContext 객체가 갖음
		String saveDir = context.getRealPath("Upload"); // 절대경로를 가져옴
		System.out.println("절대경로 >> " + saveDir);

		int maxSize = 3 * 1024 * 1024;
		String encoding = "utf-8";

		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());
			ReviewBoardDao dao = ReviewBoardDao.getInstance();
			int num = Integer.parseInt(request.getParameter("num"));
			ReviewBoardVo vo1 = dao.detail(num);
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String img = mr.getFilesystemName("img");
			System.out.println(num + " " + title + " " + content + " " + img);
			ReviewBoardVo vo = new ReviewBoardVo(num, vo1.getName(), vo1.getEmail(), title, content, vo1.getHeight(), vo1.getWeight(), vo1.getHit(), vo1.getRegdate(), img, vo1.getItemImg(), vo1.getCode());
			int n = dao.update(vo);
			if (n > 0) {
				File f = new File(saveDir + "/" + vo.getImg());
				if(f.exists()) {
					if(f.delete())
						list(request, response);
				}
			}else {
				System.out.println("안됨");
			}
		}
	}
	/*public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewBoardDao dao = ReviewBoardDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBoardVo vo1 = dao.detail(num);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String img = request.getParameter("img");
		System.out.println(num + " " + title + " " + content + " " + img);
		ReviewBoardVo vo = new ReviewBoardVo(num, vo1.getName(), vo1.getEmail(), title, content, vo1.getHeight(), vo1.getWeight(), vo1.getHit(), vo1.getRegdate(), img, vo1.getItemImg(), vo1.getCode());
		int n = dao.update(vo);
		if(n>0) {
			list(request, response);
		}
	}*/
	
}
