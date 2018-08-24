package jh;

import java.sql.Date;

public class PayVo {
	private int orderNum;
	private Date orderDate;
	private String state;
	private String method;
	private String addr;
	private String email;
	private int totalPrice;
	private int payMoney;
	
	public PayVo() {}
	
	public PayVo(int orderNum, Date orderDate, String state, String method, String addr, String email, int totalPrice,
			int payMoney) {
		super();
		this.orderNum = orderNum;
		this.orderDate = orderDate;
		this.state = state;
		this.method = method;
		this.addr = addr;
		this.email = email;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
