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
import jh.ItemsizeVo;
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
      PreparedStatement pstmt1=null;
      try {
         con=DBConnection.getConnection();
         String sql="update pay set state=? where orderNum=?";
         String sql1="update buy set state=? where orderNum=?";
         pstmt=con.prepareStatement(sql);
         pstmt1=con.prepareStatement(sql1);
         pstmt.setString(1, "배송중");
         pstmt.setInt(2, num);
         pstmt1.setString(1, "배송중");
         pstmt1.setInt(2, num);
         int n=pstmt.executeUpdate();
         int a=pstmt1.executeUpdate();
         return n & a;
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
      String sql="select * from pay where email=? and ordernum>0";
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
         PreparedStatement pstmt1=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            String sql1="update buy set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt1=con.prepareStatement(sql1);
            pstmt.setString(1, "구매완료");
            pstmt.setInt(2, num);
            pstmt1.setString(1, "구매완료");
            pstmt1.setInt(2, num);
            int n=pstmt.executeUpdate();
            int a=pstmt1.executeUpdate();
            return n & a;
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
    //배송상태 업데이트(pay테이블만.)
      public int payconfirm2(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         PreparedStatement pstmt1=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "구매완료");
            pstmt.setInt(2, num);
            int n=pstmt.executeUpdate();
            return n ;
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
      //배송상태 업데이트(buy테이블만.)
      public int payconfirm4(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buynum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "구매완료");
            pstmt.setInt(2, num);
            int n=pstmt.executeUpdate();
            return n ;
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
    //배송상태 업데이트(pay테이블만.) 반품완료로.
      public int payconfirm3(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "반품완료");
            pstmt.setInt(2, num);
            int n=pstmt.executeUpdate();
            return n ;
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
   //상품 배송중
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
            pstmt.setString(1, "반품신청중");
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
      //교환신청으로 buy테이블의 상태 변경
            public int buystatechange2(int num) {
               Connection con=null;
               PreparedStatement pstmt=null;
               try {
                  con=DBConnection.getConnection();
                  String sql="update buy set state=? where buyNum=?";
                  pstmt=con.prepareStatement(sql);
                  pstmt.setString(1, "교환신청중");
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
      //buynum을 넣으면 반품사유를 내보내줌.
      public Return_buyVo reason(int buyNum) {
          String sql="select * from return_buy where buynum=?";
          Connection con=null;
          PreparedStatement pstmt=null;
          ResultSet rs=null;
          Return_buyVo vo=null;
          try {
             con=DBConnection.getConnection();
             pstmt=con.prepareStatement(sql);   
             pstmt.setInt(1, buyNum);
             rs=pstmt.executeQuery();
             while(rs.next()) {
             String reason=rs.getString("reason");
             vo=new Return_buyVo(buyNum, reason);
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
      //buynum으로 pay테이블검색해서 정보뽑아오기
      public PayVo ordernumselect(int buyNum) {
         String sql="select p.* " + 
               "from buy b,pay p " + 
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
            pstmt.setString(1, "반품신청중");
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
      //pay테이블의 state가 반품대기중인걸 찾아서 buy테이블과 조인해서 정보 뿌려주기]
      
      public ArrayList<BuyVo> refundlist() {
         String sql="select * from buy where state='반품신청중' or state='반품완료' or state='교환신청중' or state='교환완료'";
         Connection con=null;
         PreparedStatement pstmt=null;
         ResultSet rs=null;
         try {
            con=DBConnection.getConnection();
            pstmt=con.prepareStatement(sql);   
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
      //관리자가 반품완료로 state업데이트하기
      public int refundup(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "반품완료");
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
      //구매자-교환신청중으로 보이기pay
      public int refundup3(int num) {
          Connection con=null;
          PreparedStatement pstmt=null;
          try {
             con=DBConnection.getConnection();
             String sql="update pay set state=? where orderNum=?";
             pstmt=con.prepareStatement(sql);
             pstmt.setString(1, "교환신청중");
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
      //buy넘버 받아와서 해당상품의 다른 사이즈 뿌려주기
      public ArrayList<ItemsizeVo> returnsize(int buyNum) {
         Connection con=null;
         PreparedStatement pstmt=null;
         ResultSet rs=null;
         try {
            con=DBConnection.getConnection();
            String sql="select i.* " + 
                  "from (select code from buy where buynum=?) b, itemsize i " + 
                  "where b.code=i.code and i.isize!=(select i.isize from itemsize i, (select * " + 
                  "from buy where buynum=?) b where b.isize=i.isize and b.code=i.code)";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, buyNum);
            pstmt.setInt(2, buyNum);
            rs=pstmt.executeQuery();
            ArrayList<ItemsizeVo> list=new ArrayList<>();
            while(rs.next()) {
               String isize=rs.getString("isize");
               String code=rs.getString("code");
               int amount=rs.getInt("amount");
               ItemsizeVo vo=new ItemsizeVo(isize, code, amount);
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
      
      //buyNum으로 주문상품의 아이템명 가져오기
      public ArrayList<ItemVo> namesch(int buyNum){
         Connection con=null;
         PreparedStatement pstmt=null;
         ResultSet rs=null;
         try {
            con=DBConnection.getConnection();
            String sql="select i.*" + 
                  " from" + 
                  " (select i.code" + 
                  " from buy b, item i" + 
                  " where b.code=i.code and b.buynum=?) a, item i" + 
                  " where a.code=i.code";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, buyNum);
            rs=pstmt.executeQuery();
            ArrayList<ItemVo> list=new ArrayList<>();
            while(rs.next()) {
               String itemName=rs.getString("itemname");
               String code=rs.getString("code");
               String description=rs.getString("description");
               int price=rs.getInt("price");
               ItemVo vo=new ItemVo(code, price, itemName, description);
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
      
      
      //사이즈 받아와서 buy에서 사이즈 수정하기
      public int resize(String isize, int buyNum) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=?,isize=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "교환신청중");
            pstmt.setString(2, isize);
            pstmt.setInt(3, buyNum);
            
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
      
      //교환할 사이즈 가져와서 buy테이블 업데이트하기
      public int rebuy(String isize, int buyNum) {
          Connection con=null;
          PreparedStatement pstmt=null;
          try {
             con=DBConnection.getConnection();
             String sql="update buy set state=?,isize=? where buyNum=?";
             pstmt=con.prepareStatement(sql);
             pstmt.setString(1, "교환신청중");
             pstmt.setString(2, isize);
             pstmt.setInt(3, buyNum);
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
    //관리자가 교환완료로 state업데이트하기
      public int refundup2(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "교환완료");
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
      
}
   
   