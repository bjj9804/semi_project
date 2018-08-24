package jj;

public class ItemVo {
	private String code;//��ǰ�ڵ�
	private int price;//����
	private String itemName;//��ǰ��
	private String description;//��ǰ����
	private String imgType;//�̹���Ÿ��
	private String imgSrc;//�̹������
	private String isize;//�����ۻ�����
	private int amount;//����
	private int num; //���ȣ
	private String lookCode;//���ڵ�
	private String lookFront;//��ո����
	private String lookBack;//��޸����
	
	public ItemVo() {}

	
	
	public ItemVo(String code, int price, String itemName, String description, String imgType, String imgSrc,
			String isize, int amount, int num, String lookCode, String lookFront, String lookBack) {
		super();
		this.code = code;
		this.price = price;
		this.itemName = itemName;
		this.description = description;
		this.imgType = imgType;
		this.imgSrc = imgSrc;
		this.isize = isize;
		this.amount = amount;
		this.num = num;
		this.lookCode = lookCode;
		this.lookFront = lookFront;
		this.lookBack = lookBack;
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

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
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
