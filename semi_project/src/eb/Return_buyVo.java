package eb;

public class Return_buyVo {
	private int buyNum;
	private String reason;
	
	public Return_buyVo() {}

	public Return_buyVo(int buyNum, String reason) {
		super();
		this.buyNum = buyNum;
		this.reason = reason;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
