package jh;

public class ItemDto {
	private String code;
	private int price;
	private String itemName;
	private String description;
	private String imgType;
	private String imgScr;
	private String isize;
	private int amount;
	
	public ItemDto() {}
	public ItemDto(String code, int price, String itemName, String description, String imgType, String imgScr,
			String isize, int amount) {
		super();
		this.code = code;
		this.price = price;
		this.itemName = itemName;
		this.description = description;
		this.imgType = imgType;
		this.imgScr = imgScr;
		this.isize = isize;
		this.amount = amount;
		}
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
	public String getImgScr() {
		return imgScr;
	}
	public void setImgScr(String imgScr) {
		this.imgScr = imgScr;
	}
	public String getIsize() {
		return isize;
	}
	public void setIsize(String isize) {
		this.isize = isize;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
