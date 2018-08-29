package mh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import jh.PayVo;
import semi.db.DBConnection;

public class SaleDao {
	private static SaleDao instance = new SaleDao();
	private SaleDao() {}
	public static SaleDao getInstance() {
		return instance;
	}
	//��ü��ȸ
	public ArrayList<PayVo> salelist(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='���ſϷ�' and ordernum in (select ordernum from buy where state is null)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<PayVo> list = new ArrayList<>();
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
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//ȸ�����Ǹ���ȸ
	public ArrayList<SaleUserVo> userlist(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select email, sum(payMoney) tot, count(email) cnt from pay where state='���ſϷ�' and ordernum in (select ordernum from buy where state is null) group by email order by tot desc";
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
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//ȸ�����Ǹ���ȸ ������
	public ArrayList<PayVo> userdetail(String email){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where email=? and state='���ſϷ�' and ordernum in (select ordernum from buy where state is null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			ArrayList<PayVo> list = new ArrayList<>();
			while(rs.next()) {
				int orderNum = rs.getInt("orderNum");
				Date orderDate=rs.getDate("orderDate");
				String state=rs.getString("state");
				String method=rs.getString("method");
				String addr=rs.getString("addr");
				int totalPrice=rs.getInt("totalPrice");
				int payMoney=rs.getInt("payMoney");
				PayVo vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//�̹���
	public ArrayList<PayVo> monthlist(int year, int month, int endDay){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='���ſϷ�' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state is null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, year+"/"+month+"/"+1);
			pstmt.setString(2, year+"/"+month+"/"+endDay);
			rs = pstmt.executeQuery();
			ArrayList<PayVo> list = new ArrayList<>();
			while(rs.next()) {
				int orderNum = rs.getInt("orderNum");
				Date orderDate=rs.getDate("orderDate");
				String state=rs.getString("state");
				String method=rs.getString("method");
				String email=rs.getString("email");
				String addr=rs.getString("addr");
				int totalPrice=rs.getInt("totalPrice");
				int payMoney=rs.getInt("payMoney");
				PayVo vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//�̹���
	public ArrayList<PayVo> weeklist(int year, int month, int startDay, int endDay){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='���ſϷ�' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state is null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, year+"/"+month+"/"+startDay);
			pstmt.setString(2, year+"/"+month+"/"+endDay);
			rs = pstmt.executeQuery();
			ArrayList<PayVo> list = new ArrayList<>();
			while(rs.next()) {
				int orderNum = rs.getInt("orderNum");
				Date orderDate=rs.getDate("orderDate");
				String state=rs.getString("state");
				String method=rs.getString("method");
				String email=rs.getString("email");
				String addr=rs.getString("addr");
				int totalPrice=rs.getInt("totalPrice");
				int payMoney=rs.getInt("payMoney");
				PayVo vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//����
	public ArrayList<PayVo> daylist(int year, int month, int day){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='���ſϷ�' and orderdate >= ? and ordernum in (select ordernum from buy where state is null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, year+"/"+month+"/"+day);
			rs = pstmt.executeQuery();
			ArrayList<PayVo> list = new ArrayList<>();
			while(rs.next()) {
				int orderNum = rs.getInt("orderNum");
				Date orderDate=rs.getDate("orderDate");
				String state=rs.getString("state");
				String method=rs.getString("method");
				String email=rs.getString("email");
				String addr=rs.getString("addr");
				int totalPrice=rs.getInt("totalPrice");
				int payMoney=rs.getInt("payMoney");
				PayVo vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//���ó�¥
	public ArrayList<PayVo> selectlist(int startyear, int startmonth, int startDay, int endyear, int endmonth, int endDay){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='���ſϷ�' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state is null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startyear+"/"+startmonth+"/"+startDay);
			pstmt.setString(2, endyear+"/"+endmonth+"/"+endDay);
			rs = pstmt.executeQuery();
			ArrayList<PayVo> list = new ArrayList<>();
			while(rs.next()) {
				int orderNum = rs.getInt("orderNum");
				Date orderDate=rs.getDate("orderDate");
				String state=rs.getString("state");
				String method=rs.getString("method");
				String email=rs.getString("email");
				String addr=rs.getString("addr");
				int totalPrice=rs.getInt("totalPrice");
				int payMoney=rs.getInt("payMoney");
				PayVo vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
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
	//cnt,tot ��ȸ
	public DatelistVo totlist(int startyear, int startmonth, int startDay, int endyear, int endmonth, int endDay) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select sum(payMoney) tot, count(email) cnt from pay where state='���ſϷ�' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state is null) order by orderdate desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startyear+"/"+startmonth+"/"+startDay);
			pstmt.setString(2, endyear+"/"+endmonth+"/"+endDay);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int tot = rs.getInt("tot");
				int cnt = rs.getInt("cnt");
				DatelistVo vo=new DatelistVo(tot,cnt);
				return vo;
			}
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}
	//�����ۺ���ȸ
	public ArrayList<ItemlistVo> itemlist() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select AA.*, item.itemname\r\n" + 
						"from (select code, count(*) cnt, sum(price) tot" + 
								"from buy" + 
								"where state is null and ordernum in (select ordernum" + 
																	"from pay" + 
																	"where state='���ſϷ�')" + 
								"group by code)AA,itemn" + 
						"where aa.code = item.code" + 
						"order by cnt desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemlistVo> list = new ArrayList<>();
			while(rs.next()) {
				String code = rs.getString("code");
				int tot = rs.getInt("tot");
				int cnt = rs.getInt("cnt");
				String itemname = rs.getString("itemname");
				ItemlistVo vo=new ItemlistVo(code,cnt,tot,itemname);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}
}
