package monsters;


public abstract class Monster {

	public enum Type{
		FIRE,
		PLANT,
		WATER
	}

	private String name;
	private int lifePoints;
	private int attack;
	private int defense;
	private int speed;
	private Type type;

	public Monster(String name, int lifePoints, int attack, int defense, int speed, Type type) {
		super();
		this.name = name;
		this.lifePoints = lifePoints;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Type getType() {
		return type;
	}

	public String getTypeString() {
		return String.valueOf(this.getType());
	}


	@Override
	public String toString() {
		return "Monster [" + name + ", " + type + ", " + lifePoints + ", " + attack + ", " + defense + ", " + speed + "]";
	}

	public abstract boolean efectividad(Type type);

}
