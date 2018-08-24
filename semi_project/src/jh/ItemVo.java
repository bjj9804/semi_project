package jh;

public class ItemVo {
	private String code;
	private int price;
	private String itemName;
	private String description;
	
	
	public ItemVo() {}
	
	public ItemVo(String code, int price, String itemName, String description) {
		super();
		this.code = code;
		this.price = price;
		this.itemName = itemName;
		this.description = description;
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
	
	
	
	
	
}
