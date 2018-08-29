package mh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jh.CouponVo;
import semi.db.DBConnection;

public class UsersDao {
	private static UsersDao instance = new UsersDao();
	
	private UsersDao() {}

	public static UsersDao getInstance() {
		return instance;
	}
	
	//회원가입
	public int join(UsersVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
			String sql = "insert into Users values(?,?,?,?,?,?,?,sysdate,1,1)";
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
	//로그인
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
			return false;
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
	//전체조회(관리자페이지에서)
	public ArrayList<UsersVo> userslist() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from users where flag = 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<UsersVo> list = new ArrayList<>();
			while(rs.next()) {
				String email=rs.getString("email");
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
				list.add(vo);
			}
			return list;
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
	//로그인시 관리자인지 회원인지 구분하려고 만듬
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
	//email체크하려고 만듬
	public boolean checkE(String email) {
		boolean using = false;
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
				using = true;
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
		return using;
	}
	//비밀번호찾기
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
	//이메일찾기
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
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException clfe) {
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
	
	//정보수정하기
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
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException clfe) {
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
	
	//탈퇴하기
	public int delete(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
			String sql = "delete from users where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			return pstmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException clfe) {
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
	
	//관리자에서 유저의 쿠폰갯수세기
	public int couponcnt(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select count(*) cnt from coupon where couponState='사용가능' and email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException clfe) {
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
		return 0;
	}
	//관리자에서 쿠폰 지급하기
	public int coupongift(String email, String couponName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
			String sql = "insert into coupon values(COUPON_SEQ.NEXTVAL,?,?,'사용가능',SYSDATE,ADD_MONTHS(SYSDATE,12))";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, couponName);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException clfe) {
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
	
	public ArrayList<CouponVo> couponlist(String email){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from conpon where email = ?";
			pstmt = 
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		} catch(ClassNotFoundException clfe) {
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
}
