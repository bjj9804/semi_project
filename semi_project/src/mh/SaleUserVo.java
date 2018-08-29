package mh;

public class SaleUserVo {
	private String email;
	private int tot;
	private int cnt;
	
	
	public SaleUserVo() {}
	
	public SaleUserVo(String email, int tot, int cnt) {
		super();
		this.email = email;
		this.tot = tot;
		this.cnt = cnt;
	}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public int getTot() {return tot;}
	public void setTot(int tot) {this.tot = tot;}
	public int getCnt() {return cnt;}
	public void setCnt(int cnt) {this.cnt = cnt;}
}
