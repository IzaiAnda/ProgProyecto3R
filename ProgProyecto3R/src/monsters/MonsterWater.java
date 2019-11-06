package monsters;

public class MonsterWater extends Monster {

	public String type = "Water";

	public MonsterWater(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean efectividad(String type) {
		if (type == "Plant") {
			return true;
		}
		return false;
	}

}
