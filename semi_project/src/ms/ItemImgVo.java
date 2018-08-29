package ms;

public class ItemImgVo {
	private String imgType;
	private String code;
	private String imgScr;
	
	public ItemImgVo() {}

	public ItemImgVo(String imgType, String code, String imgScr) {
		super();
		this.imgType = imgType;
		this.code = code;
		this.imgScr = imgScr;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgScr() {
		return imgScr;
	}

	public void setImgScr(String imgScr) {
		this.imgScr = imgScr;
	}
	
	
}
