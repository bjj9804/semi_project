package jh;

public class BuyVo {
	private int buyNum;
	private int orderNum;
	private String code;
	private String isize;
	private int orderAmount;
	private int Price;
	
	public BuyVo() {}
	
	public BuyVo(int buyNum, int orderNum, String code, String isize, int orderAmount, int price) {
		super();
		this.buyNum = buyNum;
		this.orderNum = orderNum;
		this.code = code;
		this.isize = isize;
		this.orderAmount = orderAmount;
		Price = price;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsize() {
		return isize;
	}

	public void setIsize(String isize) {
		this.isize = isize;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}
	
	
	
	
	
}
