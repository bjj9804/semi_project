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
	
	public int getCartNum(String email) {//��ٱ��� ��ȣ ������
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try {
			con=DBConnection.getConnection();
			String sql="select nvl(min(orderNum),0) as cartNum from pay where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {//pay���̺� �ش�email�� ���� ���� �����ϸ� �ϴ� �� ���� ������. ������ �ϴ� 0�� �޾ƿ´�.
				int cartNum=rs.getInt("cartNum");
				if(cartNum>=0) {//���� ���� 0�̶�� �ƿ� ��ٱ��ϰ� �ֹ��̰� �ƹ��͵� �����ٴ� ��. 
								//���� ���� ������ ��ٱ��ϴ� ������� ���ſ� �ֹ��� �̷�������ٴ°�
					String sql1="select nvl(min(orderNum),-1) as cartNum from pay";
					pstmt1=con.prepareStatement(sql1);
					rs1=pstmt1.executeQuery();
					if(rs1.next()) {
						cartNum=rs1.getInt("cartNum");
						if(cartNum>0) {
							cartNum=-1;
						}
					}
				}
				return cartNum-1;
			}			
			return 0;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {
				if(rs1!=null) rs1.close();
				if(pstmt1!=null) pstmt1.close();
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public int getOrderNum(String email) {//�ֹ���ȣ ������
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select max(orderNum) as orderNum from pay where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {//pay���̺� �ش�email�� ���� ���� �����ϸ� �ϴ� �� ���� ������. ������ �ϴ� 0�� �޾ƿ´�.
				int orderNum=rs.getInt("orderNum");
				if(orderNum<0) {//orderNum�� 0���� �۴ٴ� ���� �ֹ��� ���� �ϳ��� �ȵ����ִٴ°�! 
								//������ �� ó���� īƮ���Ŀ� �̷�������ϴϱ� ������� ������!�׷��ϱ� �� ���̺��� ����Ϸ��� max�� �׻� ����.
					orderNum=0;
				}
				return orderNum+1;
			}			
			return 0;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
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
	
	public int cart(BuyVo bvo,PayVo pvo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			String sql1="insert into pay(orderNum,orderDate,state,email) values(?,sysdate,'cart',?)";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, pvo.getOrderNum());			
			pstmt1.setString(2, pvo.getEmail());			
			pstmt1.executeUpdate();		
			
			String sql="insert into buy values(buy_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getOrderNum());
			pstmt.setString(2, bvo.getCode());
			pstmt.setString(3, bvo.getIsize());
			pstmt.setInt(4, bvo.getOrderAmount());
			pstmt.setInt(5, bvo.getPrice());
			pstmt.executeUpdate();
			
			con.commit();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		}finally {
			try {
				if(pstmt1!=null) pstmt1.close();
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
