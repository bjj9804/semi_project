package eb;

import java.sql.Date;

public class DemandVo {
	private int ordernum;
	private Date orderdate;
	private int orderamount;
	private String state;
	private String email;
	private String code;
	private int num;
	private String addr;
	private String method;
	private int totalPrice;
	private int payMoney;
	
	public DemandVo() {}
	
	public DemandVo(int ordernum, Date orderdate, int orderamount, String state, String email, String code, int num,
			String addr, String method, int totalPrice, int payMoney) {
		super();
		this.ordernum = ordernum;
		this.orderdate = orderdate;
		this.orderamount = orderamount;
		this.state = state;
		this.email = email;
		this.code = code;
		this.num = num;
		this.addr = addr;
		this.method = method;
		this.totalPrice = totalPrice;
		this.payMoney = payMoney;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public int getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(int orderamount) {
		this.orderamount = orderamount;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
