package monsters;

public class MonsterFire extends Monster {
	
	public MonsterFire(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
		this.type = Type.FIRE;
	}

	@Override
	public boolean efectividad(Type type) {
		if (type == Type.WATER) {
			return true;
		}
		return false;
	}

}
