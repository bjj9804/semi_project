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
	
	public int insert(ReviewBoardVo vo) {
		Connection con = null;
		Connection con1 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con1 = cp.getConnection();
			String sql1 = "select u.name from reviewboard r, users u where r.email=u.email";
			pstmt1 = con1.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.getInt("u.name");
			}
			String sql = "insert into reviewboard values(review_seq.nextval, ?, ?, ?, ?, email, 0, name)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.);
			pstmt.setString(2, vo.getComments());
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
	
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "delete from comments where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public ArrayList<CommentsVo> list() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommentsVo> list = new ArrayList<>();
		try {
			con = DBConnection.getConn();
			String sql = "select * from comments order by num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String comments = rs.getString("comments");
				CommentsVo vo = new CommentsVo(num, id, comments);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
}
