package monsters;

public class MonsterWater extends Monster {

	public MonsterWater(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed,Type.WATER);

	}

	@Override
	public boolean efectividad(Type type) {
		if (type == Type.PLANT) {
			return true;
		}
		return false;
	}


}
