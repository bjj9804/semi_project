package jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ms.ItemImgVo;
import semi.db.DBConnection;

public class ProductDao {
	private static ProductDao instance=new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() {
		return instance;
	}
	
	public ArrayList<LookVo> lookList(String cmd1) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LookVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="";
			if(cmd1.equals("runway")) {
				sql="select * from look where substr(lookcode,1,1)='R'";
			}else if(cmd1.equals("women")) {
				sql="select * from look where substr(lookcode,1,2)='RW'";
			}else if(cmd1.equals("men")) {
				sql="select * from look where substr(lookcode,1,2)='RM'";
			}
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String lookCode = rs.getString("lookCode");
				String lookFront = rs.getString("lookFront");
				String lookBack = rs.getString("lookBack");
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