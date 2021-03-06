package jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.DBConnection;

public class NoticeBoardDao {
	
	private static NoticeBoardDao instance=new NoticeBoardDao();
	private NoticeBoardDao() {}
	public static NoticeBoardDao getInstance() {
		return instance;
	}
	
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select NVL(max(num),0) maxnum from noticeboard";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxnum");
			}else {
				return 0;
			}
		}catch(Exception e) {
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
	public int getCount(String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			if(keyword.equals("")) {
				String sql="select NVL(count(num),0) cnt from noticeboard";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
			}else {
				String sql = "select NVL(count(num),0) cnt from noticeboard where "+search+" like '%'||?||'%' ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				rs = pstmt.executeQuery();
			}			
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(Exception e) {
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
	public NoticeBoardVo select(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql1="update noticeboard set hit=hit+1 where num=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, num);
			if(pstmt1.executeUpdate()>0) {
				String sql="select * from NoticeBoard where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					String name=rs.getString("name");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String email=rs.getString("email");
					int hit=rs.getInt("hit");
					Date regdate=rs.getDate("regdate");
					NoticeBoardVo vo=new NoticeBoardVo(num, name, email, title, content, hit, regdate);
					return vo;
				}
			}			
			return null;
		}catch(Exception e) {
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
	public ArrayList<NoticeBoardVo> list(int startRow,int endRow,String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<NoticeBoardVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			if(search.equals("")) {
				String sql="select * from "
						+ "(select AA.*,rownum rnum from "
						+ "(select * from noticeboard order by num desc) AA) "
						+ "where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs=pstmt.executeQuery();
			}else {
				String sql="select * from "
						+ "(select AA.*,rownum rnum from "
						+ "(select * from noticeboard  where "+search+" like '%'||?||'%' order by num desc) AA) "
						+ "where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();				
			}
			while(rs.next()) {
				int num=rs.getInt("num");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String title=rs.getString("title");
				String content=rs.getString("content");
				int hit=rs.getInt("hit");
				Date regdate=rs.getDate("regdate");
				NoticeBoardVo vo=new NoticeBoardVo(num, name, email, title, content, hit, regdate);
				list.add(vo);
			}
			return list;
		}catch(Exception e) {
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
	public int delete(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="delete from NoticeBoard where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public int insert(NoticeBoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			int num=getMaxNum()+1;			
			String sql="insert into noticeboard values(?,?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getContent());
			pstmt.setInt(6, vo.getHit());
			return pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public int update(int num,String title,String content) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update noticeboard set title=?,content=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			return pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
