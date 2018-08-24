package jh;

public class LookVo {
	private int num;
	private String lookCode;
	private String code;
	private String lookFront;
	private String lookBack;
	
	public LookVo() {}
	
	public LookVo(int num, String lookCode, String code, String lookFront, String lookBack) {
		super();
		this.num = num;
		this.lookCode = lookCode;
		this.code = code;
		this.lookFront = lookFront;
		this.lookBack = lookBack;
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
	
	
	
	
}
