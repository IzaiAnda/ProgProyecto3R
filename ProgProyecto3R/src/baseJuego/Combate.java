package baseJuego;

import monsters.Monster;

public class Combate extends Thread  {
	
//	Monster mon1 = new Monster("Uno",200,20,20,20);
//	Monster mon2 = new Monster("Dos",200,20,20,20);
	
	//efectividad(1 normal, 2 ventaja), ataque del pokemon, poder del ataque, defensa del enemigo
	
	public static int calculo(boolean e, int a, int p, int d) {
		
		int salida = 0;
		
		double dmg = 0.44 * a * p / d;
		
		if (e) {
			dmg *= 2;
		}
		
		salida =(int) dmg; 
		
		return salida;	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
	
}
