package eb;

public class BuyVo {
	private int buyNum;
	private int orderNum;
	private String code;
	private String isize;
	private int orderAmount;
	private int price;
	private String state;
	
	public BuyVo() {}
	
	public BuyVo(int buyNum, int orderNum, String code, String isize, int orderAmount, int price, String state) {
		super();
		this.buyNum = buyNum;
		this.orderNum = orderNum;
		this.code = code;
		this.isize = isize;
		this.orderAmount = orderAmount;
		this.price = price;
		this.state = state;
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
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
