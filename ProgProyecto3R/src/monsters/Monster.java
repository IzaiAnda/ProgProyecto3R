package monsters;

public abstract class Monster {

	private String name;
	private int lifePoints;
	private int attack;
	private int defense;
	private int speed;

	public Monster(String name, int lifePoints, int attack, int defense, int speed) {
		super();
		this.name = name;
		this.lifePoints = lifePoints;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
	}

	public Monster() {
		super();
		this.name = "";
		this.lifePoints = 0;
		this.attack = 0;
		this.defense = 0;
		this.speed = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getlifePoints() {
		return lifePoints;
	}

	public void setlifePoints(int lifePoints) {
		lifePoints = lifePoints;
	}

	public int getattack() {
		return attack;
	}

	public void setattack(int attack) {
		this.attack = attack;
	}

	public int getdefense() {
		return defense;
	}

	public void setdefense(int defense) {
		this.defense = defense;
	}

	public int getspeed() {
		return speed;
	}

	public void setspeed(int speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Monster [" + name + ", " + lifePoints + ", " + attack + ", " + defense + ", " + speed + "]";
	}

	public abstract boolean efectividad(String type);

}
