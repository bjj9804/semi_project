package ms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import eb.QnaBoardVo;
import semi.db.DBConnection;

public class MyshopBoardDao {
	private static MyshopBoardDao instance = new MyshopBoardDao();
	private MyshopBoardDao() {}
	public static MyshopBoardDao getInstance() {
		return instance;
	}
	
	
	public int getReviewCount(String email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = DBConnection.getConnection();
			String sql="select NVL(count(num),0) cnt from reviewboard where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException | ClassNotFoundException | NamingException se) {
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
	
	public int getQnaCount(String email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = DBConnection.getConnection();
			String sql="select NVL(count(num),0) cnt from qnaboard where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException | ClassNotFoundException | NamingException se) {
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
	
	public ArrayList<ReviewBoardVo> reveiwList(String email, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBoardVo> list = new ArrayList<>();
		try {
			con = DBConnection.getConnection();
			String sql = "select * from( select AA.*, rownum rnum from ( select * from reviewboard where email=? order by num desc) AA) where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				int hit = rs.getInt("hit");
				String name = rs.getString("name");
				Date regdate = rs.getDate("regdate");
				String img = rs.getString("img");
				String itemImg = rs.getString("itemImg");
				String code = rs.getString("code");
				ReviewBoardVo vo = new ReviewBoardVo(num, name, email, title, content, height, weight, hit, regdate, img, itemImg, code);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} catch (ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public ArrayList<QnaBoardVo> qnaList(String email, int startRow, int endRow) {
		String sql = "select * from( select AA.*, rownum rnum from ( select * from qnaboard where email=? and lev=0 order by num desc) AA) where rnum>=? and rnum<=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			ArrayList<QnaBoardVo> list = new ArrayList<>();
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int grp = rs.getInt("grp");
				int lev = rs.getInt("lev");
				int step = rs.getInt("step");
				int hit = rs.getInt("hit");
				Date regdate = rs.getDate("regdate");
				QnaBoardVo vo = new QnaBoardVo(num, name, email, title, content, grp, lev, step, hit, regdate);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} catch (ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
