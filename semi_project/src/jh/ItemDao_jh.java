package jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.DBConnection;

public class ItemDao_jh {
	private static ItemDao_jh instance=new ItemDao_jh();
	private ItemDao_jh() {}
	public static ItemDao_jh getInstance() {
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
			String sql="select * from look order by lookCode asc";
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
	public int itemInsert(String code,String price,String itemName,String description,String isize,String amount) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into item values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, price);
			pstmt.setString(3, itemName);
			pstmt.setString(4, description);
			pstmt.executeUpdate();
			String sql1="insert into itemsize values(?,?,?)";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setString(1, isize);
			pstmt1.setString(2, code);
			pstmt1.setString(3, amount);
			pstmt1.executeUpdate();
			return 1;
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
	public int itemInsert2(String imgType,String code,String imgSrc) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into itemImg values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, imgType);
			pstmt.setString(2, code);
			pstmt.setString(3, imgSrc);
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
	public int itemInsert3(String lookCode,String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into lookitem values(lookitem_seq.nextval,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, lookCode);
			pstmt.setString(2, code);
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
	public ArrayList<ItemDto> list() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ItemDto> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from "
					+ "item,itemimg,itemsize,lookItem "
					+ "where item.code=itemimg.code "
					+ "and item.code=itemsize.code "
					+ "and item.code=lookItem.code ";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String code = rs.getString("code");
				int price = rs.getInt("price");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				String imgType = rs.getString("imgType");
				String imgScr = rs.getString("imgScr");
				String isize = rs.getString("isize");
				int amount = rs.getInt("amount");
				//int num = rs.getInt("num");
				//String lookCode = rs.getString("lookCode");
				
				ItemDto dto=new ItemDto(code, price, itemName, description, imgType, imgScr, isize, amount);
				list.add(dto);
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
