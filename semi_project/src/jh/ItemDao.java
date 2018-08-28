package jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.DBConnection;

public class ItemDao {
	private static ItemDao instance=new ItemDao();
	private ItemDao() {}
	public static ItemDao getInstance() {
		return instance;
	}
	
	public int lookInsert(LookVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into look values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getLookCode());
			pstmt.setString(2, vo.getLookFront());
			pstmt.setString(3, vo.getLookBack());
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
	public ArrayList<LookVo> getLookCode() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LookVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from look";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String lookCode=rs.getString("lookCode");
				String lookFront=rs.getString("lookFront");
				String lookBack=rs.getString("lookBack");
				LookVo vo=new LookVo(lookCode, lookFront, lookBack);
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
}
