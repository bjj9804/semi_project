package jj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.DBConnection;

public class ItemDao{
	
	private static ItemDao instance=new ItemDao();
	private ItemDao() {}
	public static ItemDao getInstance() {
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

	public int insert(ItemVo vo) {
		Connection con = null;
		PreparedStatement pstmtItem = null;
		PreparedStatement pstmtImg = null;
		PreparedStatement pstmtSize = null;
		PreparedStatement pstmtLook = null;
		String sqlItem = "insert into item values(?,?,?,?)";
		String sqlImg  = "insert into itemimg values(?,?,?)";
		String sqlSize = "insert into itemsize values(?,?,?)";		
		String sqlLook = "insert into look values(look_seq.nextval,?,?,?,?)";
		try {
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			pstmtItem = con.prepareStatement(sqlItem);
			pstmtItem.setString(1, vo.getCode());
			pstmtItem.setInt(2, vo.getPrice());
			pstmtItem.setString(3, vo.getItemName());
			pstmtItem.setString(4, vo.getDescription());
			
			pstmtImg = con.prepareStatement(sqlImg);
			pstmtImg.setString(1, vo.getImgType());
			pstmtImg.setString(2, vo.getCode());
			pstmtImg.setString(3, vo.getImgSrc());
			
			pstmtSize = con.prepareStatement(sqlSize);
			pstmtSize.setString(1, vo.getIsize());
			pstmtSize.setString(2, vo.getCode());
			pstmtSize.setInt(3, vo.getAmount());
			
			pstmtLook = con.prepareStatement(sqlLook);
			pstmtLook.setInt(1, vo.getNum());
			pstmtLook.setString(2, vo.getLookCode());
			pstmtLook.setString(3, vo.getCode());
			pstmtLook.setString(4, vo.getLookFront());
			pstmtLook.setString(5, vo.getLookBack());
			
			return pstmtItem.executeUpdate();
			return pstmtImg.executeUpdate();
			return pstmtSize.executeUpdate();
			return pstmtLook.executeUpdate();
			con.commit();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
			}
		}finally {
			try {
				if(con != null) con.close();
				if(pstmtItem != null) pstmtItem.close();
				if(pstmtImg != null) pstmtImg.close();
				if(pstmtSize != null) pstmtSize.close();
				if(pstmtLook != null) pstmtLook.close();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}
}
