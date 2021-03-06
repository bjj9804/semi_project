package mh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import eb.BuyVo;
import jh.PayVo;
import semi.db.DBConnection;

public class SaleDao {
	private static SaleDao instance = new SaleDao();
	private SaleDao() {}
	public static SaleDao getInstance() {
		return instance;
	}
	//전체조회
	public ArrayList<PayVo> salelist(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='구매완료' and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료')";
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
	//회원별판매조회
	public ArrayList<SaleUserVo> userlist(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select email, sum(payMoney) tot, count(email) cnt from pay where state='구매완료' and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료') group by email order by tot desc";
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
	//회원별판매조회 디테일
	public ArrayList<PayVo> userdetail(String email){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where email=? and state='구매완료' and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료')";
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
	//이번달
	public ArrayList<PayVo> monthlist(int year, int month, int endDay){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='구매완료' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료')";
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
	//이번주
	public ArrayList<PayVo> weeklist(int year, int month, int startDay, int endDay){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='구매완료' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료')";
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
	//오늘
	public ArrayList<PayVo> daylist(int year, int month, int day){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='구매완료' and orderdate >= ? and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료')";
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
	//선택날짜
	public ArrayList<PayVo> selectlist(int startyear, int startmonth, int startDay, int endyear, int endmonth, int endDay){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from pay where state='구매완료' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startyear+"/"+startmonth+"/"+startDay);
			pstmt.setString(2, endyear+"/"+endmonth+"/"+endDay);
			System.out.println(""+ startyear + startmonth + startDay + endyear + endmonth + endDay+"");
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
	//cnt,tot 조회
	public DatelistVo totlist(int startyear, int startmonth, int startDay, int endyear, int endmonth, int endDay) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select sum(payMoney) tot, count(email) cnt from pay where state='구매완료' and orderdate >= ? and orderdate <= ? and ordernum in (select ordernum from buy where state = '구매완료' or state = '교환완료') order by orderdate desc";
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
	//아이템별조회  or state = '교환완료'**
	public ArrayList<ItemlistVo> itemlist() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select AA.code, item.itemname, AA.cnt, AA.tot from (select code, count(code) cnt, sum(price) tot from buy where (state = '구매완료' or state = '교환완료') and ordernum in (select ordernum from pay where state='구매완료') group by code) AA,item where AA.code = item.code order by cnt desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemlistVo> list = new ArrayList<>();
			while(rs.next()) {
				String code = rs.getString("code");
				String itemname = rs.getString("itemname");
				int tot = rs.getInt("tot");
				int cnt = rs.getInt("cnt");
				ItemlistVo vo=new ItemlistVo(code,itemname,cnt,tot);
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
	//판매상세내역  or state = '교환완료'**
	public ArrayList<BuyVo> orderlist(int orderNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from buy where orderNum = ? and (state = '구매완료' or state = '교환완료') and ordernum in (select ordernum from pay where state='구매완료')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			rs = pstmt.executeQuery();
			ArrayList<BuyVo> list = new ArrayList<>();
			while(rs.next()) {
				int buyNum = rs.getInt("buyNum");
				String code = rs.getString("code");
				String isize = rs.getString("isize");
				int orderAmount = rs.getInt("orderAmount");
				int price = rs.getInt("price");
				String state = rs.getString("state");
				BuyVo vo= new BuyVo(buyNum,orderNum,code,isize,orderAmount,price,state);
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
