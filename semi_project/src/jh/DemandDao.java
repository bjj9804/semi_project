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
	
	public int cartCheck(int cartNum) {//buyTb에 cartNum를 갖는 장바구니정보가 남아있는지
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
	
	public int[] getCartNum(String email) {//장바구니 번호 얻어오기
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
			int check=1;//일단 그냥 초기값으로 1을 주기는했는데 //1이면 장바구니정보가 있다.로 합시다.
			int cartNum=0;
			int originalCartNum=0;
			if(rs.next()) {//pay테이블에 해당email을 가진 행이 존재하면 일단 그 값을 꺼낸다. 없으면 일단 0을 받아온다.
						/////cartNum이 음수면 장바구니정보가 있는것! 음수가 아니면 장바구니정보가 없는것!
				cartNum=rs.getInt("cartNum");				
				originalCartNum=cartNum;				
				if(cartNum>=0) {//만약 값이 0이라면 아예 장바구니고 주문이고 아무것도 없었다는 것. 
								//만약 값이 양수라면 장바구니는 비었지만 과거에 주문은 이루어졌었다는것
					check=0;//장바구니정보가 없다.
					String sql1="select nvl(min(orderNum),-1) as cartNum from pay";//장바구니값으로 갖을 값 꺼내기
					pstmt1=con.prepareStatement(sql1);
					rs1=pstmt1.executeQuery();
					if(rs1.next()) {
						cartNum=rs1.getInt("cartNum");
						if(cartNum>0) {//buy테이블에 아예 장바구니정보가 없으면||초기에 장바구니담기
							cartNum=-1;
						}
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
	public int getOrderNum(String email) {//주문번호 얻어오기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select max(orderNum) as orderNum from pay where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {//pay테이블에 해당email을 가진 행이 존재하면 일단 그 값을 꺼낸다. 없으면 일단 0을 받아온다.
				int orderNum=rs.getInt("orderNum");
				if(orderNum<0) {//orderNum이 0보다 작다는 것은 주문이 아직 하나도 안들어와있다는것! 
								//어차피 이 처리는 카트이후에 이루어져야하니깐 비어있진 않을것!그러니깐 이 테이블을 사용하려면 max는 항상 존재.
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
	public LookVo getLookVo(String code) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			String sql="select * from look where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int num=rs.getInt("num");
				String lookCode=rs.getString("lookCode");
				String lookFront=rs.getString("lookFront");
				String lookBack=rs.getString("lookBack");
				LookVo vo=new LookVo(num, lookCode, lookCode, lookFront, lookBack);
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
	
	
	
	public int cart(PayVo pvo,BuyVo bvo) {//굳이 동시에 insert시키는 이유는 둘다 동시에 생성해야하니까!
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
	public int buyInsert(BuyVo bvo) {//장바구니가 이미 생성되어있는상태에서 상품을 추가할때!
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
			String sql="select * from buy where orderNum=("
					+ "select orderNum from pay where email=?)";
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
				BuyVo bvo=new BuyVo(buyNum, orderNum, code, isize, orderAmount, price);
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
	public int payInsert(PayVo pvo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="insert into pay values(?,sysdate,'배송준비중',?,?,?,?,?)";
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
