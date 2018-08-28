package jh;

public class LookItemVo {
	private int num;
	private String lookCode;
	private String code;
	
	
	public LookItemVo() {}
	public LookItemVo(int num, String lookCode, String code) {
		super();
		this.num = num;
		this.lookCode = lookCode;
		this.code = code;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLookCode() {
		return lookCode;
	}
	public void setLookCode(String lookCode) {
		this.lookCode = lookCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
