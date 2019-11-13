package monsters;

import monsters.Monster.Tipe;

public class MonsterWater extends Monster {

	public MonsterWater(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
		this.tipe = Tipe.WATER;
	}

	@Override
	public boolean efectividad(Tipe tipe) {
		if (tipe == Tipe.PLANT) {
			return true;
		}
		return false;
	}


}
