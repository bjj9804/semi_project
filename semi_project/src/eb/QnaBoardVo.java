package eb;

import java.sql.Date;

public class QnaBoardVo {
	private int num;
	private String name;
	private String title;
	private String content;
	private String email;
	private int grp;
	private int lev;
	private int step;
	private int hit;
	private Date regdate;
	
	public QnaBoardVo() {}
	
	public QnaBoardVo(int num, String name, String email, String title, String content, int grp, int lev, int step,
			  int hit, Date regdate) {
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.email = email;
		this.grp = grp;
		this.lev = lev;
		this.step = step;
		this.hit = hit;
		this.regdate=regdate;
	}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getDate() {
		return regdate;
	}
	public void setDate(Date regdate) {
		this.regdate = regdate;
	}
	
}
	