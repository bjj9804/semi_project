package jh;

public class LookVo {
	private String lookCode;
	private String lookFront;
	private String lookBack;
	
	public LookVo() {}
	
	public LookVo(String lookCode, String lookFront, String lookBack) {
		super();
		this.lookCode = lookCode;
		this.lookFront = lookFront;
		this.lookBack = lookBack;
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
	
	
	
	
}
