package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPoolBean;
import vo.ReviewBoardVo;


public class ReviewBoardDao {
	private static ReviewBoardDao instance = new ReviewBoardDao();
	private ReviewBoardDao() {}
	public static ReviewBoardDao getInstance() {
		return instance;
	}
	
	ConnectionPoolBean cp;
	
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = cp.getConnection();
			String sql="select NVL(max(num),0) maxnum from reviewboard";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxnum");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
	
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = cp.getConnection();
			String sql="select NVL(count(num),0) cnt from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
	
	public int insert(ReviewBoardVo vo) {
		Connection con = null;
		Connection con1 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String name = null;
		try {
			con = cp.getConnection();
			con1 = cp.getConnection();
			String sql1 = "select u.name from reviewboard r, users u where r.email=u.email";
			pstmt1 = con1.prepareStatement(sql1);
			rs = pstmt1.executeQuery();
			if(rs.next()) {
				name = rs.getString("u.name");
			}
			String sql = "insert into reviewboard values(review_seq.nextval, ?, ?, ?, ?, ?, 0, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getHeight());
			pstmt.setInt(4, vo.getWeight());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, name);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
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
	
	public ArrayList<ReviewBoardVo> list(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBoardVo> list = new ArrayList<>();
		try {
			con = cp.getConnection();
			String sql = "select * from( select AA.*, rownum rnum from ( select * from board order by num desc) AA) where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String email = rs.getString("email");
				int hit = rs.getInt("hit");
				String name = rs.getString("name");
				ReviewBoardVo vo = new ReviewBoardVo(num, title, content, height, weight, email, name, hit);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public ReviewBoardVo detail(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBoardVo vo = null;
		try {
			con = cp.getConnection();
			String sql = "select * from reviewboard where num=?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String email = rs.getString("email");
				int hit = rs.getInt("hit");
				String name = rs.getString("name");
				vo = new ReviewBoardVo(num, title, content, height, weight, email, name, hit);
			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public int hitup(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = cp.getConnection();
			String sql="update reviewboard set hit = hit+1 where num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
