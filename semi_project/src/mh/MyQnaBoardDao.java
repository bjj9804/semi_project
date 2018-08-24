package mh;

public class MyQnaBoardDao {
private static MyQnaBoardDao instance = new MyQnaBoardDao();
	
	private MyQnaBoardDao() {}

	public static MyQnaBoardDao getInstance() {
		return instance;
	}
}
