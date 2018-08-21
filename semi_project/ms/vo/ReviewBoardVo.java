package vo;

public class ReviewBoardVo {
	private int num;
	private String title;
	private String content;
	private int height;
	private int weight;
	private String email;
	private String name;
	private int hit;
	
	public ReviewBoardVo() {}

	public ReviewBoardVo(int num, String title, String content, int height, int weight, String email, String name,
			int hit) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.height = height;
		this.weight = weight;
		this.email = email;
		this.name = name;
		this.hit = hit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
}
