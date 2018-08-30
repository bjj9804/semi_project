package mh;

public class ItemlistVo {
	private String code;
	private String itemname;
	private int cnt;
	private int tot;
	
	public ItemlistVo() {}
	
	public ItemlistVo(String code, String itemname, int cnt, int tot) {
		super();
		this.code = code;
		this.itemname = itemname;
		this.cnt = cnt;
		this.tot = tot;
	}

	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getItemname() {return itemname;}
	public void setItemname(String itemname) {this.itemname = itemname;}
	public int getCnt() {return cnt;}
	public void setCnt(int cnt) {this.cnt = cnt;}
	public int getTot() {return tot;}
	public void setTot(int tot) {this.tot = tot;}
}
