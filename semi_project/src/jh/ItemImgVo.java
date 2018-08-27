package jh;

public class ItemImgVo {
	private String imgType;
	private String code;
	private String imgSrc;
	
	public ItemImgVo() {}
	public ItemImgVo(String imgType, String code, String imgSrc) {
		super();
		this.imgType = imgType;
		this.code = code;
		this.imgSrc = imgSrc;
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
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
}
