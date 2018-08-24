package mh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import semi.db.DBConnection;

public class UsersDao {
	private static UsersDao instance = new UsersDao();
	
	private UsersDao() {}

	public static UsersDao getInstance() {
		return instance;
	}
	
	//회원가입 정보 받아오기
	public int join(UsersVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
			String sql = "insert into Users values(?,?,?,?,?,?,?,sysdate,1,1)";// ������ �ʱⰪ�� 1������ �ְ� flag�� �����ڴ� �ʱⰪ 1������
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			pstmt.setInt(3, vo.getQuestion());
			pstmt.setString(4, vo.getAnswer());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getAddr());
			pstmt.setString(7, vo.getName());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return -1;
	}
	
	//로그인시 이메일/비밀번호 확인하기
	public boolean login(String email,String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from users where email=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return false;
	}
	
	public UsersVo select(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from users where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String password=rs.getString("password");
				int question = rs.getInt("question");
				String answer = rs.getString("answer");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				String name=rs.getString("name");
				Date regdate=rs.getDate("regdate");
				int coupon=rs.getInt("coupon");
				int flag=rs.getInt("flag");
				UsersVo vo=new UsersVo(email, password, question, answer, phone, addr, name, regdate, coupon, flag);
				return vo;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
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
	
	//비밀번호 찾기
	public String findPwd(String email, String phone, int question, String answer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBConnection.getConnection();
			String sql = "select password from users where email=? and phone=? and question=? and answer=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phone);
			pstmt.setInt(3, question);
			pstmt.setString(4, answer);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("password");
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
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
	
	//이메일받기
	public String findEmail(String name, String phone, int question, String answer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBConnection.getConnection();
			String sql = "select email from users where name=? and phone=? and question=? and answer=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setInt(3, question);
			pstmt.setString(4, answer);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("email");
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
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
	public int update(UsersVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
			String sql = "update users set password=?,name=?,phone=?,addr=? where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getEmail());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(ClassNotFoundException clfe) {
			clfe.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return -1;
	}
}
