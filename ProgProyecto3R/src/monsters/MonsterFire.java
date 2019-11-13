package monsters;

public class MonsterFire extends Monster {
	
	public MonsterFire(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
		this.tipe = Tipe.FIRE;
	}

	@Override
	public boolean efectividad(Tipe tipe) {
		if (tipe == Tipe.WATER) {
			return true;
		}
		return false;
	}

}
