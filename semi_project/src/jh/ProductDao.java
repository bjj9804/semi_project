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
				sql="select * from look "
					+ "where lookCode in "
					+ "(select lookcode from "						
					+ "(select substr(lookcode,1,1) sub, lookcode from look) "
					+ "where sub='R') order by lookcode desc";
			}else if(cmd1.equals("women")) {
				sql="select * from look "
					+ "where lookCode in "
					+ "(select lookcode from look where lookcode like '%W%') ";					
			}else if(cmd1.equals("men")) {
				sql="select * from look "
					+ "where lookCode in "
					+ "(select lookcode from look where lookcode like '%M%') ";				
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
	public ArrayList<ItemVo> getItem(String mark) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ItemVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="";
			if(mark.equals("w")) {
				sql="select * from item "
					+ "where code in "
					+ "(select code from "						
					+ "(select substr(code,1,1) sub, code from item) "
					+ "where sub='W')";
			}else if(mark.equals("wtop")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('WTE','WSH','WKN','WSW'))";
			}else if(mark.equals("wpants")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('WPT'))";
			}else if(mark.equals("wskirts")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('WSK'))";
			}else if(mark.equals("wouter")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('WJK','WCO','WCD'))";
			}else if(mark.equals("wdresses")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('WDR'))";
			}else if(mark.equals("m")) {
				sql="select * from item "
					+ "where code in "
					+ "(select code from "						
					+ "(select substr(code,1,1) sub, code from item) "
					+ "where sub='M')";
			}else if(mark.equals("mtop")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('MTE','MSH','MKN','MSW'))";
			}else if(mark.equals("mpants")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('MPT'))";
			}else if(mark.equals("mouter")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,1,3) sub, code from item) "
						+ "where sub in ('MJK','MCO','MCD'))";
			}else if(mark.equals("ac")) {
				sql="select * from item "
					+ "where code in "
					+ "(select code from "						
					+ "(select substr(code,2,2) sub, code from item) "
					+ "where sub='AC')";
			}else if(mark.equals("bg")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,2,2) sub, code from item) "
						+ "where sub='BG')";
			}else if(mark.equals("su")) {
				sql="select * from item "
						+ "where code in "
						+ "(select code from "						
						+ "(select substr(code,2,2) sub, code from item) "
						+ "where sub='SU')";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String code = rs.getString("code");
				int price = rs.getInt("price");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				ItemVo vo=new ItemVo(code, price, itemName, description);
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