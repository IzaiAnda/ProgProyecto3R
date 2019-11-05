package monsters;

public class MonsterPlant extends Monster {

	public String type = "Plant";

	public MonsterPlant(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean efectividad(String type) {
		if (type == "Fire") {
			return true;
		}
		return false;
	}

}
