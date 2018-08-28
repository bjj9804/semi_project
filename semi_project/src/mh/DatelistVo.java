package mh;

public class DatelistVo {
	private int tot;
	private int cnt;
	
	public DatelistVo() {}
	
	public DatelistVo(int tot, int cnt) {
		super();
		this.tot = tot;
		this.cnt = cnt;
	}

	public int getTot() {return tot;}

	public void setTot(int tot) {this.tot = tot;}

	public int getCnt() {return cnt;}

	public void setCnt(int cnt) {this.cnt = cnt;}
}
