package eb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import semi.db.DBConnection;


public class QnaBoardDao {
	private static QnaBoardDao instance=new QnaBoardDao();
	
	//리스트
	public ArrayList<QnaBoardVo> list(int startRow, int endRow){

		String sql="SELECT * FROM " + "(" + "SELECT AA.*,ROWNUM RNUM FROM " + "(" +
				"SELECT * FROM QNABOARD " + "ORDER BY GRP DESC, STEP ASC" +
				")AA"+ ")" + " WHERE RNUM>=? AND RNUM<=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			
			ArrayList<QnaBoardVo> list=new ArrayList<>();
			while(rs.next()) {
				int num=rs.getInt("num");
				String name=rs.getString("name");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String email=rs.getString("email");
				int grp=rs.getInt("grp");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				int hit=rs.getInt("hit");
				Date regdate=rs.getDate("regdate");
				QnaBoardVo vo=new QnaBoardVo(num, name, email, title, content, grp, lev, step, hit, regdate);
				list.add(vo);
			}
			return list;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}catch(ClassNotFoundException cn) {
				System.out.println(cn.getMessage());
				return null;
			} catch (NamingException e) {
				e.printStackTrace();
				return null;
			}finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();				
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	
	//전체글 갯수
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		con=DBConnection.getConnection();
		String sql="select NVL(count(num),0) cnt from qnaboard";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("cnt");
		}else {
			return 0;
		}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return -1;
		} catch (NamingException e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//가장 큰 글번호 얻어오기
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
		con=DBConnection.getConnection();
		String sql="select NVL(max(num),0) MAXNUM from qnaboard";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("maxnum");
		}else {
			return 0;
		}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			
			return -1;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return -1;
		} catch (NamingException e) {
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();			
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	
	//QnA글 등록
	public int insert(QnaBoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		
		try {
		con=DBConnection.getConnection();
		int boardNum=getMaxNum()+1;//추가될글번호
		int num=vo.getNum();
		int grp=vo.getGrp();
		int lev=vo.getLev();
		int step=vo.getStep();
		if(num==0) {//새글인경우 num=0이라는건 부모글번호가 없다는 것으로 즉 새글.
			grp=boardNum;
			
		}else {//답글인경우
			String sql="update qnaboard set step=step+1 where grp=? and step>?";
			pstmt1=con.prepareStatement(sql);
			pstmt1.setInt(1, grp);
			pstmt1.setInt(2, step);
			pstmt1.executeUpdate();
			lev += 1;
			step += 1;
		}
		String sql="insert into qnaboard values(?,?,?,?,?,?,?,?,?,sysdate)";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, boardNum);
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getTitle());
		pstmt.setString(5, vo.getContent());
		pstmt.setInt(6, grp);
		pstmt.setInt(7, lev);
		pstmt.setInt(8, step);
		pstmt.setInt(9, vo.getHit());

		int n=pstmt.executeUpdate();
		return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		return -1;
		}catch(ClassNotFoundException cn){
			cn.printStackTrace();
			return -1;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(pstmt1!=null) pstmt1.close();
				if(con!=null) con.close();			
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//글 상세보기
	public QnaBoardVo detail(int num) {
		String sql="select * from qnaboard where num=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);	
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String name=rs.getString("name");
				String content=rs.getString("content");
				String email=rs.getString("email");
				String title=rs.getString("title");
				int grp=rs.getInt("grp");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				int hit=rs.getInt("hit");
				Date regdate=rs.getDate("regdate");
				QnaBoardVo vo=new QnaBoardVo(num, name, email, title, content, grp, lev, step, hit, regdate);
				return vo;	
			}
		return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	public int hitup(int num){
		String sql="update qnaboard set hit=hit+1 where num=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);	
			pstmt.setInt(1, num);
			int n=pstmt.executeUpdate();
		return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return -1;
		} catch (NamingException e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}		
	}
}

