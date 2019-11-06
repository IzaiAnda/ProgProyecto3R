package monsters;

public class MonsterFire extends Monster {

	public String type = "Fire";

	
	public MonsterFire(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
		this.type = "Fire";
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean efectividad(String type) {
		if (type == "Water") {
			return true;
		}
		return false;
	}

}
