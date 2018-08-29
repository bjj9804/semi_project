package jh;

public class LookDto {
	private String lookCode;
	private String lookFront;
	private String lookBack;
	private int num;
	private String code;
	
	public LookDto() {}
	public LookDto(String lookCode, String lookFront, String lookBack, int num, String code) {
		super();
		this.lookCode = lookCode;
		this.lookFront = lookFront;
		this.lookBack = lookBack;
		this.num = num;
		this.code = code;
	}
	public String getLookCode() {
		return lookCode;
	}
	public void setLookCode(String lookCode) {
		this.lookCode = lookCode;
	}
	public String getLookFront() {
		return lookFront;
	}
	public void setLookFront(String lookFront) {
		this.lookFront = lookFront;
	}
	public String getLookBack() {
		return lookBack;
	}
	public void setLookBack(String lookBack) {
		this.lookBack = lookBack;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
