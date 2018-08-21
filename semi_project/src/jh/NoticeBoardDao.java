package jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPoolBean;

public class NoticeBoardDao {
	ConnectionPoolBean cp;
	private static NoticeBoardDao instance=new NoticeBoardDao();
	private NoticeBoardDao() {}

	public static NoticeBoardDao getInstance() {
		return instance;
	}
	
	public NoticeBoardVo select(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=cp.getConnection();
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
				NoticeBoardVo vo=new NoticeBoardVo(num, name, title, content, email, hit);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) cp.returnConnection(con);				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public ArrayList<NoticeBoardVo> list() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<NoticeBoardVo> list=new ArrayList<>();
		try {
			con=cp.getConnection();
			String sql="select * from NoticeBoard";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int num=rs.getInt("num");
				String name=rs.getString("name");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String email=rs.getString("email");
				int hit=rs.getInt("hit");
				NoticeBoardVo vo=new NoticeBoardVo(num, name, title, content, email, hit);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) cp.returnConnection(con);				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public int delete(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=cp.getConnection();
			String sql="delete from NoticeBoard where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) cp.returnConnection(con);				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
