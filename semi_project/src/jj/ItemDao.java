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
		String sqlItem = "";
		String sqlImg = "";
		String sqlSize = "";
		String sqlLook = "";
		try {
			pstmtItem= con.prepareStatement(sqlItem);
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
