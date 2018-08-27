package ms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import semi.db.DBConnection;

public class ReviewBoardDao {
	
	private static ReviewBoardDao instance = new ReviewBoardDao();
	private ReviewBoardDao() {}
	public static ReviewBoardDao getInstance() {
		return instance;
	}
	
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = DBConnection.getConnection();
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
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
	
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = DBConnection.getConnection();
			String sql="select NVL(count(num),0) cnt from reviewboard";
			pstmt=con.prepareStatement(sql);
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
	public int getSearchCount(int height, int weight) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = DBConnection.getConnection();
			String sql="select NVL(count(num),0) cnt from reviewboard where height between" + 
						height +" and " + height+10 + " and weight between "+weight+" and "+ weight+10;
			pstmt=con.prepareStatement(sql);
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
	public ItemImgVo getItemImg(String code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemImgVo vo = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from itemImg where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String imgType = rs.getString("imgtype");
				String imgSrc = rs.getString("imgSrc");
				vo = new ItemImgVo(imgType, code, imgSrc);
			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
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
	public int insert(ReviewBoardVo vo) {
		int maxNum = getMaxNum();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
			String sql = "insert into reviewboard values(?, ?, ?, ?, ?, ?, ?, 0, sysdate, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maxNum+1);
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getName());
			pstmt.setInt(6, vo.getHeight());
			pstmt.setInt(7, vo.getWeight());
			pstmt.setString(8, vo.getImg());
			pstmt.setString(9, vo.getItemImg());
			pstmt.setString(10, vo.getCode());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
	
	public ArrayList<ReviewBoardVo> list(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBoardVo> list = new ArrayList<>();
		try {
			con = DBConnection.getConnection();
			String sql = "select * from( select AA.*, rownum rnum from ( select * from reviewboard order by num desc) AA) where rnum>=? and rnum<=?";
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
				Date regdate = rs.getDate("regdate");
				String img = rs.getString("img");
				String itemImg = rs.getString("itemImg");
				String code = rs.getString("code");
				ReviewBoardVo vo = new ReviewBoardVo(num, name, email, title, content, height, weight, hit, regdate, img, itemImg, code);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
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
	
	public ArrayList<ReviewBoardVo> searchlist(int startRow, int endRow, int height, int weight) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBoardVo> list = new ArrayList<>();
		try {
			con = DBConnection.getConnection();
			String sql = "select * from( select AA.*, rownum rnum from ( select * from reviewboard where height between " +height+" and "+height+10 
					    + " and " + "weight between " +weight+" and "+weight+10+ "order by num desc) AA) where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String email = rs.getString("email");
				int hit = rs.getInt("hit");
				String name = rs.getString("name");
				Date regdate = rs.getDate("regdate");
				String img = rs.getString("img");
				String itemImg = rs.getString("itemImg");
				String code = rs.getString("code");
				ReviewBoardVo vo = new ReviewBoardVo(num, name, email, title, content, height, weight, hit, regdate, img, code, code);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
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
			con = DBConnection.getConnection();
			String sql = "select * from reviewboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String email = rs.getString("email");
				int hit = rs.getInt("hit");
				String name = rs.getString("name");
				Date regdate = rs.getDate("regdate");
				String img = rs.getString("img");
				String itemImg = rs.getString("itemImg");
				String code = rs.getString("code");
				vo = new ReviewBoardVo(num, name, email, title, content, height, weight, hit, regdate, img, itemImg, code);
			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
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
		try {
			con = DBConnection.getConnection();
			String sql="update reviewboard set hit = hit+1 where num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
	
	public int delete(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con = DBConnection.getConnection();
			String sql="delete from reviewboard where num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
	
	public int update(ReviewBoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con = DBConnection.getConnection();
			String sql="update reviewboard set title = ?, content = ? where num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
