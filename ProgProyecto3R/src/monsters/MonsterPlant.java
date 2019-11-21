package monsters;

public class MonsterPlant extends Monster {

	public MonsterPlant(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed,Type.PLANT);
	}

	@Override
	public boolean efectividad(Type type) {
		if (type == Type.FIRE) {
			return true;
		}
		return false;
	}


}
