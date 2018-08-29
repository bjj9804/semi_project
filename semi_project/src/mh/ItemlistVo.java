package mh;

public class ItemlistVo {
	private String code;
	private int cnt;
	private int tot;
	private String itemname;
	
	public ItemlistVo() {}
	
	public ItemlistVo(String code, int cnt, int tot, String itemname) {
		super();
		this.code = code;
		this.cnt = cnt;
		this.tot = tot;
		this.itemname = itemname;
	}

	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public int getCnt() {return cnt;}
	public void setCnt(int cnt) {this.cnt = cnt;}
	public int getTot() {return tot;}
	public void setTot(int tot) {this.tot = tot;}
	public String getItemname() {return itemname;}
	public void setItemname(String itemname) {this.itemname = itemname;}
}
