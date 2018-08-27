package jh;

public class ItemsizeVo {
	private String isize;
	private String code;
	private int amount;
	public ItemsizeVo() {}
	public ItemsizeVo(String isize, String code, int amount) {
		super();
		this.isize = isize;
		this.code = code;
		this.amount = amount;
	}
	public String getIsize() {
		return isize;
	}
	public void setIsize(String isize) {
		this.isize = isize;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
}
