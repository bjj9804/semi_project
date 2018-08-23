package jj;

public class ItemVo {
	private String code;//상품코드
	private int price;//가격
	private String itemName;//상품명
	private String description;//상품설명
	private String imgType;//이미지타입
	private String imgSrc;//이미직경로
	private int isize;//아이템사이즈
	private int amount;//수량
	private int num; //룩번호
	private String lookCode;//룩코드
	private String lookFront;//룩앞면사진
	private String lookBack;//룩뒷면사진
	
	public ItemVo() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public int getIsize() {
		return isize;
	}

	public void setIsize(int isize) {
		this.isize = isize;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
