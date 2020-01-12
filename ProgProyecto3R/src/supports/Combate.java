package supports;

import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;
import moves.Move;

public class Combate{



	// efectividad(1 normal, 2 ventaja), ataque del pokemon, poder del ataque,
	// defensa del enemigo

	public static int calculo(boolean advantage, int attack, int power, int defense) {

		int exit = 0;

		double damage = 0.44 * attack * power / defense;

		if (advantage) {
			damage *= 2;
		}

		exit = (int) damage;

		return exit;
	}

	public static void damageTaken(Monster attacker, Monster defender, Move action) {

		boolean advantage = defender.efectividad(attacker.getType());

		int damageDone = calculo(advantage, attacker.getAttack(), action.getDamage(), defender.getDefense());
		int defenderLifePoints = defender.getLifePoints() - damageDone ;

		defender.setLifePoints(defenderLifePoints);

	}

	public static void combat(Monster a, Monster b, Move aM, Move bM) {
		if (a.getSpeed()>b.getSpeed()) {
			damageTaken(a, b, aM);
			if (b.getLifePoints()>0) {
				damageTaken(b, a, bM);
			}
		}else if (a.getSpeed()<b.getSpeed()){
			damageTaken(b, a, bM);
			if (a.getLifePoints()>0) {
				damageTaken(a, b, aM);
			}
		}else {
			damageTaken(a, b, aM);
			damageTaken(b, a, bM);
		}
	}

}
