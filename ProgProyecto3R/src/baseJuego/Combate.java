package baseJuego;

import monsters.Monster;

public class Combate extends Thread {

//	Monster mon1 = new Monster("Uno",200,20,20,20);
//	Monster mon2 = new Monster("Dos",200,20,20,20);

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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
