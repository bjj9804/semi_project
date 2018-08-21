package jh;

import java.sql.Date;

public class NoticeBoardVo {
	private int num;
	private String name;
	private String email;
	private String title;
	private String content;	
	private int hit;
	private Date regdate;
	
	
	public NoticeBoardVo() {}
	
	public NoticeBoardVo(int num, String name, String email, String title, String content, int hit, Date regdate) {
		super();
		this.num = num;
		this.name = name;
		this.email = email;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	
	
}
