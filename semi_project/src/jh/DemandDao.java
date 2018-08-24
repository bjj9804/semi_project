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
	
	public int[] getCartNum(String email) {//��ٱ��� ��ȣ ������
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
			int check=1;//��ٱ��������� �ִ�.
			int cartNum=0;
			if(rs.next()) {//pay���̺� �ش�email�� ���� ���� �����ϸ� �ϴ� �� ���� ������. ������ �ϴ� 0�� �޾ƿ´�.
						/////cartNum�� ������ ��ٱ��������� �ִ°�! ������ �ƴϸ� ��ٱ��������� ���°�!
				cartNum=rs.getInt("cartNum");				
				if(cartNum>=0) {//���� ���� 0�̶�� �ƿ� ��ٱ��ϰ� �ֹ��̰� �ƹ��͵� �����ٴ� ��. 
								//���� ���� ������ ��ٱ��ϴ� ������� ���ſ� �ֹ��� �̷�������ٴ°�
					check=0;//��ٱ��������� ����.
					String sql1="select nvl(min(orderNum),-1) as cartNum from pay";//��ٱ��ϰ����� ���� �� ������
					pstmt1=con.prepareStatement(sql1);
					rs1=pstmt1.executeQuery();
					if(rs1.next()) {
						cartNum=rs1.getInt("cartNum");
						if(cartNum>0) {//buy���̺� �ƿ� ��ٱ��������� ������||�ʱ⿡ ��ٱ��ϴ��
							cartNum=-1;
						}
					}
				}
				int[] cartPayTbCh= {check,cartNum};
				return cartPayTbCh;
			}			
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
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
	public int getPrice(int buyNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select price from buy where buyNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, buyNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("price");
			}
			return -1;			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
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
	public int cart(PayVo pvo,BuyVo bvo) {//���� ���ÿ� insert��Ű�� ������ �Ѵ� ���ÿ� �����ؾ��ϴϱ�!
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
			int num=pstmt1.executeUpdate();		
			
			String sql="insert into buy values(buy_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getOrderNum());
			pstmt.setString(2, bvo.getCode());
			pstmt.setString(3, bvo.getIsize());
			pstmt.setInt(4, bvo.getOrderAmount());
			pstmt.setInt(5, bvo.getPrice());
			num+=pstmt.executeUpdate();
			
			con.commit();
			
			return num;
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
	public int buyInsert(BuyVo bvo) {//��ٱ��ϰ� �̹� �����Ǿ��ִ»��¿��� ��ǰ�� �߰��Ҷ�!
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into buy values(buy_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getOrderNum());
			pstmt.setString(2, bvo.getCode());
			pstmt.setString(3, bvo.getIsize());
			pstmt.setInt(4, bvo.getOrderAmount());
			pstmt.setInt(5, bvo.getPrice());			
			
			return pstmt.executeUpdate();
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
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public int buyUpdate(int buyNum,int orderNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update buy set orderNum=? where buyNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.setInt(2, buyNum);
			return pstmt.executeUpdate();			
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
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	public int payInsert(PayVo pvo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			String sql1="insert into pay values(?,sysdate,'����غ���',?,?,?,?,?)";
			pstmt=con.prepareStatement(sql1);
			pstmt.setInt(1, pvo.getOrderNum());
			pstmt.setString(2, pvo.getMethod());
			pstmt.setString(3, pvo.getAddr());
			pstmt.setString(4, pvo.getEmail());
			pstmt.setInt(5, pvo.getTotalPrice());
			pstmt.setInt(6, pvo.getPayMoney());
			return pstmt.executeUpdate();			
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
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
}
