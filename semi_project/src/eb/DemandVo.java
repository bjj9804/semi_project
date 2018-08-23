package eb;

import java.sql.Date;

public class DemandVo {
	private int orderNum;
	private Date orderDate;
	private int orderAmount;
	private String state;
	private String email;
	private String code;
	private String isize;
	private String addr;
	private String method;
	private int totalPrice;
	private int payMoney;
	
	
	public DemandVo() {}


	public DemandVo(int orderNum, Date orderDate, int orderAmount, String state, String email, String code,
			String isize, String addr, String method, int totalPrice, int payMoney) {
		super();
		this.orderNum = orderNum;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
		this.state = state;
		this.email = email;
		this.code = code;
		this.isize = isize;
		this.addr = addr;
		this.method = method;
		this.totalPrice = totalPrice;
		this.payMoney = payMoney;
	}


	public int getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public int getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getPayMoney() {
		return payMoney;
	}


	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}	
	
}	
