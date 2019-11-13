package monsters;

import monsters.Monster.Tipe;

public class MonsterPlant extends Monster {

	public MonsterPlant(String name, int lifePoints, int attack, int defense, int speed) {
		super(name, lifePoints, attack, defense, speed);
		this.tipe = Tipe.PLANT;
	}

	@Override
	public boolean efectividad(Tipe tipe) {
		if (tipe == Tipe.FIRE) {
			return true;
		}
		return false;
	}


}
