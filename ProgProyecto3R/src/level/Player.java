package level;

public class Player {
	
	private String name;
	private int level;
	
	public Player(String name, int level) {
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	

}
