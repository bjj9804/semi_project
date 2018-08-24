package eb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jh.BuyVo;
import jh.PayVo;
import semi.db.DBConnection;

public class DemandDao {
	private static DemandDao instance=new DemandDao();
	private DemandDao() {}
	public static DemandDao getInstance() {
		return instance;
	}
	
	public ArrayList<PayVo> list(){
		String sql="select * FROM pay";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			ArrayList<PayVo> list=new ArrayList<>();
			while(rs.next()) {
				int orderNum = rs.getInt("orderNum");
				Date orderDate=rs.getDate("orderDate");
				String state=rs.getString("state");
				String method=rs.getString("method");
				String addr=rs.getString("addr");
				String email=rs.getString("email");
				int totalPrice=rs.getInt("totalPrice");
				int payMoney=rs.getInt("payMoney");
				PayVo vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
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
	
	
	//주문상세내역보기
	public ArrayList<BuyVo> detail(int num) {
		String sql="select * from buy where ordernum=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);	
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			ArrayList<BuyVo> list=new ArrayList<>();
			while(rs.next()) {
				int buyNum=rs.getInt("buyNum");
				int orderNum=rs.getInt("orderNum");
				String code=rs.getString("code");
				String isize=rs.getString("isize");
				int orderAmount=rs.getInt("orderAmount");
				int price=rs.getInt("price");
				BuyVo vo=new BuyVo(buyNum,orderNum,code,isize,orderAmount,price);
				list.add(vo);
			}
		return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	//배송상태 업데이트
	public int update(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update pay set state=? where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "배송중");
			pstmt.setInt(2, num);
			int n=pstmt.executeUpdate();
			return n;
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
	}
	
	
