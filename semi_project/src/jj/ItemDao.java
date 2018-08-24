package jj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String sql="select NVL(max(num),0) maxnum from item";
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

	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select NVL(count(code),0) cnt from item";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
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
	
	
	public ArrayList<ItemVo> list(int startRow,int endRow) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ItemVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from "
					+ "(select AA.*,rownum rnum from "
					+ "(select * from item order by num desc) AA) "
					+ "where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String code = rs.getString("code");
				int price = rs.getInt("price");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				String imgType = rs.getString("imgType");
				String imgSrc = rs.getString("imgSrc");
				String isize = rs.getString("isize");
				int amount = rs.getInt("amount");
				int num = rs.getInt("num");
				String lookCode = rs.getString("lookCode");
				String lookFront = rs.getString("lookFront");
				String lookBack = rs.getString("lookBack");
				
				ItemVo vo = new ItemVo(code, price, itemName, description, imgType, imgSrc, isize, amount, num, lookCode, lookFront, lookBack);
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

			con.commit();
			int a = pstmtItem.executeUpdate();
			int b = pstmtImg.executeUpdate();
			int c = pstmtSize.executeUpdate();
			int d = pstmtLook.executeUpdate();
			int n = a+b+c+d;
			return n;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
			}
			return -1;
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
