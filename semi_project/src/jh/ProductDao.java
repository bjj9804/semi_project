package jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jj.ItemVo;
import ms.ItemImgVo;
import semi.db.DBConnection;

public class ProductDao {
	private static ProductDao instance=new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() {
		return instance;
	}
	
	
	public jh.ItemVo getItemVo(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from item where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int price = rs.getInt("price");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				jh.ItemVo vo = new jh.ItemVo(code, price, itemName, description);
				return vo;
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
	public ItemImgVo getItemImgVo(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from itemimg where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String imgType = rs.getString("imgType");
				String imgSrc = rs.getString("imgSrc");
				ItemImgVo vo = new ItemImgVo(imgType, code, imgSrc);
				return vo;
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
	public ItemsizeVo getItemsizeVo(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from itemsize where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String isize = rs.getString("isize");
				int amount = rs.getInt("amount");
				ItemsizeVo vo=new ItemsizeVo(isize, code, amount);
				return vo;				
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
	public LookVo getLookVo(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from look where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String lookCode = rs.getString("lookCode");
				String lookFront = rs.getString("lookFront");
				String lookBack = rs.getString("lookBack");
				LookVo vo=new LookVo(num, lookCode, lookCode, lookFront, lookBack);
				return vo;				
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
}
