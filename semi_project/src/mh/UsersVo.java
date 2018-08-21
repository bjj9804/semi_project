package mh;

import java.sql.Date;

public class UsersVo {
	private String email;
	private String password;
	private String phone;
	private String addr;
	private String name;
	private Date regdate;
	private String coupon;
	private int flag;
	
	public UsersVo() {}
	public UsersVo(String email, String password, String phone, String addr, String name, Date regdate, String coupon,
			int flag) {
		super();
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.addr = addr;
		this.name = name;
		this.regdate = regdate;
		this.coupon = coupon;
		this.flag = flag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
