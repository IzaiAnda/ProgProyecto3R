package baseJuego;

import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;
import moves.Move;

public class Combate{

	MonsterPlant mon = new MonsterPlant("Plantita",100,100,100,100);
	MonsterFire mon2 = new MonsterFire("Fuegillo",10,10,10,10);
	MonsterWater mon3 = new MonsterWater("Gotita",50,50,50,50);

	Move move = new Move("Llamarada", 10);
	
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
	
	public static int damageTaken(Monster attacker, Monster defender, Move action) {
		
		boolean advantage = defender.efectividad(attacker.getTipe());
		System.out.println(advantage);
		
		calculo(advantage, attacker.getattack(), action.getDamage(), defender.getdefense());
		
		return 0;
	}
	
	public static void main(String[] args) {
		
	}

}
