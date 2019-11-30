package moves;

public class Move {

	private String name;
	private int damage;
	//private int percent;

	//public Move(String name, int damage, int percent) {
	public Move(String name, int damage) {
		super();
		this.name = name;
		this.damage = damage;
		//this.percent = percent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

//	public int getPercent() {
//		return percent;
//	}
//
//	public void setPercent(int percent) {
//		this.percent = percent;
//	}

	@Override
	public String toString() {
		return name;
	}

}
