package jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import eb.BuyVo;
import ms.ItemImgVo;
import semi.db.DBConnection;

public class DemandDao {
	private static DemandDao instance=new DemandDao();
	private DemandDao() {}
	public static DemandDao getInstance() {
		return instance;
	}
	public ArrayList<CouponVo> getCoupon(String email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<CouponVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from coupon where email=? and couponState='��밡��'";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int couponNum=rs.getInt("couponNum");
				String couponName=rs.getString("couponName");
				String couponState=rs.getString("couponState");
				Date offerDate=rs.getDate("offerDate");
				Date endDate=rs.getDate("endDate");
				CouponVo vo=new CouponVo(couponNum, couponName, email, couponState, offerDate, endDate);
				list.add(vo);
			}
			return list;
		}catch(Exception e) {
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
	public int couponUpdate(int couponNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update coupon set couponState='���Ϸ�' where couponNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, couponNum);
			return pstmt.executeUpdate();
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
	
	public int cartCheck(int cartNum) {//buyTb�� cartNum�� ���� ��ٱ��������� �����ִ���
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select count(*) as cnt from buy where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cartNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt("cnt");
				return cnt;
			}
			return 0;
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
			int check=1;//�ϴ� �׳� �ʱⰪ���� 1�� �ֱ���ߴµ� //1�̸� ��ٱ��������� �ִ�.�� �սô�.
			int cartNum=0;
			int originalCartNum=0;
			if(rs.next()) {//pay���̺� �ش�email�� ���� ���� �����ϸ� �ϴ� �� ���� ������. ������ �ϴ� 0�� �޾ƿ´�.
						/////cartNum�� ������ ��ٱ��������� �ִ°�! ������ �ƴϸ� ��ٱ��������� ���°�!
				cartNum=rs.getInt("cartNum");				
				originalCartNum=cartNum;				
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
						cartNum-=1;
					}
				}
				int[] cartPayTbCh= {check,cartNum,originalCartNum};
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
		PreparedStatement pstmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
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
				String sql1="select max(orderNum) as orderNum from pay";
				pstmt1=con.prepareStatement(sql1);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					orderNum=rs1.getInt("orderNum");
					if(orderNum<0) {
						orderNum=1;
						return orderNum;
					}
					return orderNum+1;
				}				
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
	public String getType(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select imgScr from itemImg where code=? and imgType=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, "front");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String imgScr=rs.getString("imgScr");
				return imgScr;
			}
			return null;
		}catch(Exception e) {
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
	public ItemVo getItemVo(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from item where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int price=rs.getInt("price");
				String itemName=rs.getString("itemName");
				String description=rs.getString("description");
				ItemVo vo=new ItemVo(code, price, itemName, description);
				return vo;
			}
			return null;
		}catch(Exception e) {
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
			
			String sql="insert into buy values(buy_seq.nextval,?,?,?,?,?,'cart')";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getOrderNum());
			pstmt.setString(2, bvo.getCode());
			pstmt.setString(3, bvo.getIsize());
			pstmt.setInt(4, bvo.getOrderAmount());
			pstmt.setInt(5, bvo.getPrice());
			int num1=pstmt.executeUpdate();
			
			con.commit();
			
			return num+num1;
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
			String sql="insert into buy values(buy_seq.nextval,?,?,?,?,?,'cart')";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getOrderNum());
			pstmt.setString(2, bvo.getCode());
			pstmt.setString(3, bvo.getIsize());
			pstmt.setInt(4, bvo.getOrderAmount());
			pstmt.setInt(5, bvo.getPrice());			
			
			return pstmt.executeUpdate();
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
	public int buyDelete(int buyNum) {//��ٱ��Ͽ� ��� ��ǰ ����
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		try {
			con=DBConnection.getConnection();
			String sql1="select orderNum from buy where buyNum=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, buyNum);
			rs1=pstmt1.executeQuery();
			int orderNum=0;
			if(rs1.next()) {
				orderNum=rs1.getInt("orderNum");
			}
			String sql="delete from buy where buyNum=?";
			pstmt=con.prepareStatement(sql);			
			pstmt.setInt(1, buyNum);
			if(pstmt.executeUpdate()>0) {
				String sql2="select * from buy where orderNum=?";
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, orderNum);
				rs2=pstmt2.executeQuery();
				if(rs2.next()) {
					return 1;
				}else {
					String sql3="delete from pay where orderNum=?";
					pstmt3=con.prepareStatement(sql3);
					pstmt3.setInt(1, orderNum);
					return pstmt3.executeUpdate();
				}
			}
			return 0;			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {				
				if(rs2!=null) rs2.close();
				if(rs1!=null) rs1.close();
				if(pstmt3!=null) pstmt3.close();
				if(pstmt2!=null) pstmt2.close();
				if(pstmt1!=null) pstmt1.close();
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
			String sql="update buy set orderNum=?,state='����غ���' where buyNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.setInt(2, buyNum);
			return pstmt.executeUpdate();			
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
	public ArrayList<BuyVo> getBuyVo(String email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BuyVo> list=new ArrayList<>();
		try {
			con=DBConnection.getConnection();
			String sql="select * from buy where orderNum =("
					+ "select orderNum from pay where email=? and orderNum<0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int buyNum=rs.getInt("buyNum");
				int orderNum=rs.getInt("orderNum");
				String code=rs.getString("code");
				String isize=rs.getString("isize");
				int orderAmount=rs.getInt("orderAmount");
				int price=rs.getInt("Price");
				BuyVo bvo=new BuyVo(buyNum, orderNum, code, isize, orderAmount, price, null);
				list.add(bvo);				
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public BuyVo getBuyVo(int buyNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from buy where buyNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, buyNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int orderNum=rs.getInt("orderNum");
				String code=rs.getString("code");
				String isize=rs.getString("isize");
				int orderAmount=rs.getInt("orderAmount");
				int price=rs.getInt("Price");
				BuyVo bvo=new BuyVo(buyNum, orderNum, code, isize, orderAmount, price, null);
				return bvo;				
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
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
			String sql="insert into pay values(?,sysdate,'����غ���',?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, pvo.getOrderNum());
			pstmt.setString(2, pvo.getMethod());
			pstmt.setString(3, pvo.getAddr());
			pstmt.setString(4, pvo.getEmail());
			pstmt.setInt(5, pvo.getTotalPrice());
			pstmt.setInt(6, pvo.getPayMoney());
			return pstmt.executeUpdate();			
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
	public int payDelete(int cartNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="delete from pay where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cartNum);			
			return pstmt.executeUpdate();			
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
