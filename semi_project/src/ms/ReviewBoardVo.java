package ms;

import java.sql.Date;

public class ReviewBoardVo {
	private int num;
	private String name;
	private String email;
	private String title;
	private String content;
	private int height;
	private int weight;
	private int hit;
	private Date regdate;
	private String img;
	
	public ReviewBoardVo() {}
	public ReviewBoardVo(int num, String name, String email, String title, String content, int height, int weight,
			int hit, Date regdate, String img) {
		super();
		this.num = num;
		this.name = name;
		this.email = email;
		this.title = title;
		this.content = content;
		this.height = height;
		this.weight = weight;
		this.hit = hit;
		this.regdate = regdate;
		this.img = img;
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
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
