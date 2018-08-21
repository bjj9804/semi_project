package test.db;

import java.sql.*;
import java.util.*;
/*
 * [[ ���ؼ� Ǯ ]]
 * - DBMS�� ���ӵ� ���ؼǵ��� ������ ����� ����(���ؼ�Ǯ) DB�� �����Ҷ� 
 *   ��������� ����  ���ؼǰ�ü�� ���� ����ϰ� �۾��� ������
 *   ���ؼǰ�ü�� �ٽ� ��ȯ�Ѵ�.
 * - ��������� ���� ����Ѵ�.
 */
public class ConnectionPoolBean{
	String url, usr, pwd;
	HashMap<Connection,Boolean> h; //pool�� �ʹ� ����;�
	int increment = 3;
	
	public ConnectionPoolBean() throws ClassNotFoundException, SQLException{
		Connection con = null;
        try{
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(ClassNotFoundException cnfe){
           System.out.println(cnfe.getMessage());
		}
		url = "jdbc:oracle:thin:@localhost:1521:XE";
		usr = "semi";
		pwd = "4444";
		h = new HashMap<Connection,Boolean>();
		for(int i=0; i<5; i++)
		{
			//���ؼ� ��ü ������
			con = DriverManager.getConnection(url, usr, pwd);
			//���ؼǰ�ü�� Map�� ���(������� �ƴ� ���¶�� ǥ�÷� false����)
			h.put(con,false);
		}
		System.out.println("ConnectionPoolBean created ...");
	}
	//������� �ƴ� ���ؼ� ��ȯ
	public synchronized Connection getConnection()
		throws SQLException{
		Connection con = null;
		Boolean b = null;
		Set<Connection> e = h.keySet();
		Iterator<Connection> it=e.iterator();
		while(it.hasNext()){
			//Map���� ���ؼ� ������
			con = it.next();
			//���� ������(�����:true,������� �ƴ�:false)
            b = h.get(con);
			if(!b){//��������� ������
				//������� ����(true)�� �ٲٰ�
				h.put(con,true);
				//���ؼ� ����
				return con;
			}
		}
		//���ؼ��� ��� ������϶� ���ο� ���ؼ��� 3�� ���� Map�� ����
		for(int i=0; i<increment; i++) {
            h.put(DriverManager.getConnection(url,usr,pwd), false);
		}
        return getConnection();
	}
	public void returnConnection(Connection returnCon)
		throws SQLException {
        Connection con = null;
        Set<Connection> e = h.keySet();
		Iterator<Connection> it=e.iterator();
		while(it.hasNext()){
			con = it.next();
            if(con == returnCon){
				h.put(con, Boolean.FALSE);
				break;
			}
		}
        keepConSu(5);
	}
	//��������� ���� ���ؼ� ��ü�� su��ŭ �����ϴ� �޼ҵ�
	public void keepConSu(int su) throws SQLException{
        Connection con = null;
		Boolean b = null;
		int count = 0;
		Set<Connection> e = h.keySet();
		Iterator<Connection> it=e.iterator();
		while(it.hasNext()){
			con = it.next();
			b = h.get(con);
			if(!b){//���ؼ��� ������� �ƴϸ�
				count++;//��������� ���� ���ؼ� ���� ����
				if(count >su){//��������� ���� ���ؼ��� 5���� ũ��
					h.remove(con);//Map���� �����ϱ�
					con.close();//db���� �����ϱ�
				}
			}
		}
	}
	public void closeAll() throws SQLException{
        Connection con = null;
        Set<Connection> e = h.keySet();
		Iterator<Connection> it=e.iterator();
		while(it.hasNext()){
			con = it.next();
			h.remove(con);
			con.close();
		}
	}
}
