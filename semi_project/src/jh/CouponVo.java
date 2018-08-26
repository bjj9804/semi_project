package jh;

import java.sql.Date;

public class CouponVo {
	private String couponName;
	private String email;
	private String couponState;
	private Date offerDate;
	private Date endDate;
	
	public CouponVo() {}
	public CouponVo(String couponName, String email, String couponState, Date offerDate, Date endDate) {
		super();
		this.couponName = couponName;
		this.email = email;
		this.couponState = couponState;
		this.offerDate = offerDate;
		this.endDate = endDate;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCouponState() {
		return couponState;
	}
	public void setCouponState(String couponState) {
		this.couponState = couponState;
	}
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
