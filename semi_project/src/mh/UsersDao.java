package mh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import semi.db.ConnectionPoolBean;

public class UsersDao {
	ConnectionPoolBean cp;
	private static UsersDao instance = new UsersDao();
	
	private UsersDao() {}

	public static UsersDao getInstance() {
		return instance;
	}
	
	public int join(UsersVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			cp = new ConnectionPoolBean();
			con = cp.getConnection();
			String sql = "insert into Users values(?,?,?,?,?,sysdate,1,1);";// 쿠폰은 초기값에 1장으로 넣고 flag도 구매자는 초기값 1번으로
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getName());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) cp.returnConnection(con);				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
