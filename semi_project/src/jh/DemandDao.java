package jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import semi.db.DBConnection;

public class DemandDao {
	private static DemandDao instance=new DemandDao();
	private DemandDao() {}
	public static DemandDao getInstance() {
		return instance;
	}
	
	public int cart(BuyVo bvo,PayVo pvo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into buy values(buy_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getOrderNum());
			pstmt.setString(2, bvo.getCode());
			pstmt.setString(3, bvo.getIsize());
			pstmt.setInt(4, bvo.getOrderAmount());
			pstmt.setInt(5, bvo.getPrice());
			pstmt.executeUpdate();
			
			String sql1="insert into pay values(?,sysdate,?,?,?,?,?,?)";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, pvo.getOrderNum());
			pstmt1.setString(2, pvo.getState());
			pstmt1.setString(3, pvo.getMethod());
			pstmt1.setString(4, pvo.getAddr());
			pstmt1.setString(5, pvo.getEmail());
			pstmt1.setInt(6, pvo.getTotalPrice());
			pstmt1.setInt(7, pvo.getPayMoney());
			pstmt1.executeUpdate();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public int order(BuyVo bvo,PayVo pvo) {
		
	}
	
}
