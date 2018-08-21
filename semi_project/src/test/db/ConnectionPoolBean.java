package test.db;

import java.sql.*;
import java.util.*;
/*
 * [[ 컨넥션 풀 ]]
 * - DBMS와 접속된 컨넥션들을 여러개 만들어 놓고(컨넥션풀) DB와 접속할때 
 *   사용중이지 않은  컨넥션객체를 얻어와 사용하고 작업이 끝나면
 *   컨넥션객체를 다시 반환한다.
 * - 성능향상을 위해 사용한다.
 */
public class ConnectionPoolBean{
	String url, usr, pwd;
	HashMap<Connection,Boolean> h; //pool장 너무 가고싶어
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
			//컨넥션 객체 얻어오기
			con = DriverManager.getConnection(url, usr, pwd);
			//컨넥션객체를 Map에 담기(사용중이 아닌 상태라는 표시로 false저장)
			h.put(con,false);
		}
		System.out.println("ConnectionPoolBean created ...");
	}
	//사용중인 아닌 컨넥션 반환
	public synchronized Connection getConnection()
		throws SQLException{
		Connection con = null;
		Boolean b = null;
		Set<Connection> e = h.keySet();
		Iterator<Connection> it=e.iterator();
		while(it.hasNext()){
			//Map에서 컨넥션 얻어오기
			con = it.next();
			//상태 얻어오기(사용중:true,사용중이 아님:false)
            b = h.get(con);
			if(!b){//사용중이지 않으면
				//사용중인 상태(true)로 바꾸고
				h.put(con,true);
				//컨넥션 리턴
				return con;
			}
		}
		//컨넥션이 모두 사용중일때 새로운 컨넥션을 3개 얻어와 Map에 저장
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
	//사용중이지 않은 컨넥션 객체를 su만큼 유지하는 메소드
	public void keepConSu(int su) throws SQLException{
        Connection con = null;
		Boolean b = null;
		int count = 0;
		Set<Connection> e = h.keySet();
		Iterator<Connection> it=e.iterator();
		while(it.hasNext()){
			con = it.next();
			b = h.get(con);
			if(!b){//컨넥션이 사용중이 아니면
				count++;//사용중이지 않은 컨넥션 갯수 세기
				if(count >su){//사용중이지 않은 컨넥션이 5보다 크면
					h.remove(con);//Map에서 제거하기
					con.close();//db접속 해제하기
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
