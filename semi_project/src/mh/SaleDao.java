package mh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jh.PayVo;
import semi.db.DBConnection;

public class SaleDao {
	private static SaleDao instance = new SaleDao();
	private SaleDao() {}
	public static SaleDao getInstance() {
		return instance;
	}
	
	public ArrayList<PayVo> salelist(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='구매완료'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<PayVo> list = new ArrayList<>();
			System.out.println(list);
			System.out.println(rs.next());
			while(rs.next()) {
				System.out.println(rs.getInt("orderNum"));
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
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<SaleUserVo> userlist(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select email, sum(payMoney) tot, count(email) cnt from pay where state='구매완료' group by email order by tot desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<SaleUserVo> list=new ArrayList<>();
			while(rs.next()) {
				String email = rs.getString("email");
				int tot = rs.getInt("tot");
				int cnt = rs.getInt("cnt");
				SaleUserVo vo = new SaleUserVo(email,tot,cnt);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}
	
}
