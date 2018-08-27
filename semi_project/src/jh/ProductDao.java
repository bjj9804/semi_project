package jh;

import java.util.ArrayList;

import jj.ItemVo;

public class ProductDao {
	private static ProductDao instance=new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() {
		return instance;
	}
	
	
	
}
