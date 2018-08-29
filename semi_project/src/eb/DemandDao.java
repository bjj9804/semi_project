package eb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import eb.BuyVo;
import jh.ItemVo;
import jh.PayVo;
import semi.db.DBConnection;

public class DemandDao {
	private static DemandDao instance=new DemandDao();
	private DemandDao() {}
	public static DemandDao getInstance() {
		return instance;
	}
	
	public ArrayList<PayVo> list(){
		String sql="select * FROM pay";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			ArrayList<PayVo> list=new ArrayList<>();
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
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
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
	
	
	//주문상세내역보기
	public ArrayList<BuyVo> detail(int num) {
		String sql="select * from buy where ordernum=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);	
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			ArrayList<BuyVo> list=new ArrayList<>();
			while(rs.next()) {
				int buyNum=rs.getInt("buyNum");
				int orderNum=rs.getInt("orderNum");
				String code=rs.getString("code");
				String isize=rs.getString("isize");
				int orderAmount=rs.getInt("orderAmount");
				int price=rs.getInt("price");
				String state=rs.getString("state");
				BuyVo vo=new BuyVo(buyNum,orderNum,code.trim(),isize,orderAmount,price,state);
				list.add(vo);
			}
		return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	//배송상태 업데이트(관리자-배송완료)
	public int update(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConnection();
			String sql="update pay set state=? where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "배송중");
			pstmt.setInt(2, num);
			int n=pstmt.executeUpdate();
			return n;
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
	
	
	//나(회원) 주문확인
	public ArrayList<PayVo> mylist(String email) {
		String sql="select * from pay where email=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);	
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			ArrayList<PayVo> list=new ArrayList<>();
			while(rs.next()) {
				int orderNum=rs.getInt("orderNum");
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
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(ClassNotFoundException cn) {
			System.out.println(cn.getMessage());
			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				if(con!=null) con.close();		
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//배송상태 업데이트(구매자-구매확정)
		public int payconfirm(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=DBConnection.getConnection();
				String sql="update pay set state=? where orderNum=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "구매완료");
				pstmt.setInt(2, num);
				int n=pstmt.executeUpdate();
				return n;
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
	//배송전 주문취소(삭제)
		public int paycancel(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
			con=DBConnection.getConnection();
			String sql="delete from pay where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;				
		}catch(ClassNotFoundException cn) {
			cn.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
		//배송전 주문취소(buy삭제)
		public int buycancel(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
			con=DBConnection.getConnection();
			String sql="delete from buy where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;				
		}catch(ClassNotFoundException cn) {
			cn.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
		
	//넘버로 상세 정보 가져오기
		public PayVo selectview(int orderNum) {
			String sql="select * from pay where ordernum=?";
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=DBConnection.getConnection();
				pstmt=con.prepareStatement(sql);	
				pstmt.setInt(1, orderNum);
				rs=pstmt.executeQuery();
				PayVo vo = null;
				while(rs.next()) {
					Date orderDate=rs.getDate("orderdate");
					String state=rs.getString("state");
					String method=rs.getString("method");
					String addr=rs.getString("addr");
					String email=rs.getString("email");
					int totalPrice=rs.getInt("totalprice");
					int payMoney=rs.getInt("paymoney");
					vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);	
				}
				return vo;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}catch(ClassNotFoundException cn) {
				System.out.println(cn.getMessage());
				return null;
			} catch (NamingException e) {
				e.printStackTrace();
				return null;
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
					if(con!=null) con.close();		
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	//상품 교환
		public int change(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=DBConnection.getConnection();
				String sql="update pay set state=? where orderNum=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "배송중");
				pstmt.setInt(2, num);
				int n=pstmt.executeUpdate();
				return n;
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
	//상품명 뽑아오기
		public ItemVo item(String code) {
			String sql="select * from item where code=?";
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=DBConnection.getConnection();
				pstmt=con.prepareStatement(sql);	
				pstmt.setString(1, code);
				rs=pstmt.executeQuery();
				ItemVo vo = null;
				while(rs.next()) {
					String itemName=rs.getString("itemName");
					String description=rs.getString("description");
					int price=rs.getInt("price");
					vo=new ItemVo(code.trim(), price, itemName, description);	
				}
				return vo;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}catch(ClassNotFoundException cn) {
				System.out.println(cn.getMessage());
				return null;
			} catch (NamingException e) {
				e.printStackTrace();
				return null;
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
					if(con!=null) con.close();		
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		//교환할 상품 buy에서 삭제
		public int buydelete(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
			con=DBConnection.getConnection();
			String sql="delete from buy where orderNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;				
		}catch(ClassNotFoundException cn) {
			cn.printStackTrace();
			return -1;
		} catch (NamingException e) {
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
	//반품신청으로 buy테이블의 상태 변경
		public int buystatechange(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=DBConnection.getConnection();
				String sql="update buy set state=? where buyNum=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "반품대기중");
				pstmt.setInt(2, num);
				int n=pstmt.executeUpdate();
				return n;
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
		//return테이블로 반품상품 insert
		public int returninsert(String reason,int buyNum) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=DBConnection.getConnection();
				String sql="insert into return_buy values(?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, buyNum);
				pstmt.setString(2, reason);
				int n=pstmt.executeUpdate();
				return n;
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
		//buynum으로 pay테이블검색해서 정보뽑아오기
		public PayVo ordernumselect(int buyNum) {
			String sql="select p.*" + 
					"from buy b,pay p" + 
					"where b.ordernum=p.ordernum and b.buyNum=?";
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=DBConnection.getConnection();
				pstmt=con.prepareStatement(sql);	
				pstmt.setInt(1, buyNum);
				rs=pstmt.executeQuery();
				PayVo vo = null;
				while(rs.next()) {
					int orderNum=rs.getInt("orderNum");
					Date orderDate=rs.getDate("orderdate");
					String state=rs.getString("state");
					String method=rs.getString("method");
					String addr=rs.getString("addr");
					String email=rs.getString("email");
					int totalPrice=rs.getInt("totalprice");
					int payMoney=rs.getInt("paymoney");
					vo=new PayVo(orderNum, orderDate, state, method, addr, email, totalPrice, payMoney);	
				}
				return vo;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}catch(ClassNotFoundException cn) {
				System.out.println(cn.getMessage());
				return null;
			} catch (NamingException e) {
				e.printStackTrace();
				return null;
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
					if(con!=null) con.close();		
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		//반품신청하면 pay테이블의 state도 반품대기중으로 변경해줌
		public int refundup1(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=DBConnection.getConnection();
				String sql="update pay set state=? where orderNum=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "반품대기중");
				pstmt.setInt(2, num);
				int n=pstmt.executeUpdate();
				return n;
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
		//pay테이블의 state가 반품대기중인걸 찾아서 buy테이블과 조인해서 정보 뿌려주기
}
	
	
