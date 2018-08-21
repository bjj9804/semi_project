package jh;

public class NoticeBoardVo {
	private int num;
	private String name;
	private String title;
	private String content;
	private String email;
	private int hit;
	
	
	public NoticeBoardVo() {}


	public NoticeBoardVo(int num, String name, String title, String content, String email, int hit) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.email = email;
		this.hit = hit;
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


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
}
