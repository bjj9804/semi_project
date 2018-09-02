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
   
   
   //�ֹ��󼼳�������
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
   
   
   //��ۻ��� ������Ʈ(������-��ۿϷ�)
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
         pstmt.setString(1, "�����");
         pstmt.setInt(2, num);
         pstmt1.setString(1, "�����");
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
   
   
   //��(ȸ��) �ֹ�Ȯ��
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
   
   //��ۻ��� ������Ʈ(������-����Ȯ��)
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
            pstmt.setString(1, "���ſϷ�");
            pstmt.setInt(2, num);
            pstmt1.setString(1, "���ſϷ�");
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
    //��ۻ��� ������Ʈ(pay���̺�.)
      public int payconfirm2(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         PreparedStatement pstmt1=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "���ſϷ�");
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
      //��ۻ��� ������Ʈ(buy���̺�.)
      public int payconfirm4(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buynum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "���ſϷ�");
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
    //��ۻ��� ������Ʈ(pay���̺�.) ��ǰ�Ϸ��.
      public int payconfirm3(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "��ǰ�Ϸ�");
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
   //����� �ֹ����(����)
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
      //����� �ֹ����(buy����)
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
      
   //�ѹ��� �� ���� ��������
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
   //��ǰ �����
      public int change(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "�����");
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
   //��ǰ�� �̾ƿ���
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
      //��ȯ�� ��ǰ buy���� ����
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
   //��ǰ��û���� buy���̺��� ���� ����
      public int buystatechange(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "��ǰ��û��");
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
      //��ȯ��û���� buy���̺��� ���� ����
            public int buystatechange2(int num) {
               Connection con=null;
               PreparedStatement pstmt=null;
               try {
                  con=DBConnection.getConnection();
                  String sql="update buy set state=? where buyNum=?";
                  pstmt=con.prepareStatement(sql);
                  pstmt.setString(1, "��ȯ��û��");
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
      
      //return���̺�� ��ǰ��ǰ insert
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
      //buynum�� ������ ��ǰ������ ��������.
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
      //buynum���� pay���̺�˻��ؼ� �����̾ƿ���
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
      //��ǰ��û�ϸ� pay���̺��� state�� ��ǰ��������� ��������
      public int refundup1(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update pay set state=? where orderNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "��ǰ��û��");
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
      //pay���̺��� state�� ��ǰ������ΰ� ã�Ƽ� buy���̺�� �����ؼ� ���� �ѷ��ֱ�]
      
      public ArrayList<BuyVo> refundlist() {
         String sql="select * from buy where state='��ǰ��û��' or state='��ǰ�Ϸ�' or state='��ȯ��û��' or state='��ȯ�Ϸ�'";
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
      //�����ڰ� ��ǰ�Ϸ�� state������Ʈ�ϱ�
      public int refundup(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "��ǰ�Ϸ�");
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
      //������-��ȯ��û������ ���̱�pay
      public int refundup3(int num) {
          Connection con=null;
          PreparedStatement pstmt=null;
          try {
             con=DBConnection.getConnection();
             String sql="update pay set state=? where orderNum=?";
             pstmt=con.prepareStatement(sql);
             pstmt.setString(1, "��ȯ��û��");
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
      //buy�ѹ� �޾ƿͼ� �ش��ǰ�� �ٸ� ������ �ѷ��ֱ�
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
      
      //buyNum���� �ֹ���ǰ�� �����۸� ��������
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
      
      
      //������ �޾ƿͼ� buy���� ������ �����ϱ�
      public int resize(String isize, int buyNum) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=?,isize=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "��ȯ��û��");
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
      
      //��ȯ�� ������ �����ͼ� buy���̺� ������Ʈ�ϱ�
      public int rebuy(String isize, int buyNum) {
          Connection con=null;
          PreparedStatement pstmt=null;
          try {
             con=DBConnection.getConnection();
             String sql="update buy set state=?,isize=? where buyNum=?";
             pstmt=con.prepareStatement(sql);
             pstmt.setString(1, "��ȯ��û��");
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
    //�����ڰ� ��ȯ�Ϸ�� state������Ʈ�ϱ�
      public int refundup2(int num) {
         Connection con=null;
         PreparedStatement pstmt=null;
         try {
            con=DBConnection.getConnection();
            String sql="update buy set state=? where buyNum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "��ȯ�Ϸ�");
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
   
   