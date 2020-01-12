package supports;

public class Enemy {
	
	private String name;
	private String text;
	
	public Enemy(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}
	
	public Enemy() {
		super();
		this.name = "";
		this.text = "";
	}
	
	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

}
