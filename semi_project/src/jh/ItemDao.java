package jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ms.ItemImgVo;
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
	public int imgDelete(String code,String imgType) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="delete from itemimg where code=? and imgType=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, imgType);
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
	public int lookItemDelete(String code,String lookCode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="delete from lookItem where code=? and lookCode=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, lookCode);
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
	public LookVo getLook(String lookCode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from look where lookCode=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, lookCode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String lookFront=rs.getString("lookFront");
				String lookBack=rs.getString("lookBack");
				LookVo vo=new LookVo(lookCode, lookFront, lookBack);
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
	public int lookUpdate(String lookCode,String lookFront,String lookBack,String check) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="";
			if(check.equals("1")) {//front수정
				sql="update look set lookFront=? where lookCode=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, lookFront);
				pstmt.setString(2, lookCode);
			}else {//back수정
				sql="update look set lookBack=? where lookCode=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, lookBack);
				pstmt.setString(2, lookCode);
			}			
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
	public ArrayList<LookItemVo> getLookItem(String lookCode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LookItemVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from lookitem where lookCode=? order by code asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, lookCode);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int num=rs.getInt("num");
				String code=rs.getString("code");
				LookItemVo vo=new LookItemVo(num, lookCode, code);
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
	public int itemUpdate(String code,String price,String itemName,String description) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update item set price=?,itemName=?,description=? where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, price);
			pstmt.setString(2, itemName);
			pstmt.setString(3, description);
			pstmt.setString(4, code);
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
	
	public int amountUpdate(String code,String isize,String amount) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update itemsize set amount=? where code=? and isize=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, amount);
			pstmt.setString(2, code);
			pstmt.setString(3, isize);
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
	public int itemInsert2(String imgType,String code,String imgScr) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into itemImg values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, imgType);
			pstmt.setString(2, code);
			pstmt.setString(3, imgScr);
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
	public jh.ItemVo getItem(String code) {
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
				int price=rs.getInt("price");
				String itemName=rs.getString("itemName");
				String description=rs.getString("description");
				
				jh.ItemVo vo=new jh.ItemVo(code, price, itemName, description);
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
	public ArrayList<ItemImgVo> getItemImg(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ItemImgVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from itemimg where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String imgType=rs.getString("imgType");
				String imgScr=rs.getString("imgScr");
				
				ItemImgVo vo=new ItemImgVo(imgType, code, imgScr);
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
	
	
	public ArrayList<ItemDto> list() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ItemDto> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from "
					+ "item,itemsize "
					+ "where item.code=itemsize.code "
					+ "order by item.code asc,itemsize.isize";
					pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String code = rs.getString("code");
				int price = rs.getInt("price");
				String itemName = rs.getString("itemName");
				String description = rs.getString("description");
				//String imgType = rs.getString("imgType");
				//String imgScr = rs.getString("imgScr");
				String isize = rs.getString("isize");
				int amount = rs.getInt("amount");

				ItemDto dto=new ItemDto(code, price, itemName, description, null, null, isize, amount);
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
	public ArrayList<LookDto> list1() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LookDto> list1=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from look,lookItem "
					+ "where look.lookCode=lookItem.lookCode "
					+ "order by lookitem.lookCode asc,lookitem.code asc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String lookCode = rs.getString("lookCode");
				String lookFront = rs.getString("lookFront");
				String lookBack = rs.getString("lookBack");
				int num = rs.getInt("num");
				String code = rs.getString("code");
				
				LookDto dto=new LookDto(lookCode, lookFront, lookBack, num, code);
				list1.add(dto);
			}
			return list1;
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
