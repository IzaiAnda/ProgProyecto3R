package level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import monsters.Monster;
import monsters.MonsterFire;
import moves.Move;

public class LevelGame {
	
	private String name;
	private Enemy enemy;
	private HashMap<Monster, LinkedList<Move>> monstersEnemy;
	private HashMap<Monster, LinkedList<Move>> monsters;
	
	private int difficulty;
	
	public LevelGame(String name, Enemy enemy, HashMap<Monster, LinkedList<Move>> monstersEnemy,
			HashMap<Monster, LinkedList<Move>> monsters, int difficulty) {
		super();
		this.name = name;
		this.enemy = enemy;
		this.monstersEnemy = monstersEnemy;
		this.monsters = monsters;
		this.difficulty = difficulty;
	}
	
	public String getName() {
		return name;
	}
	public Enemy getEnemy() {
		return enemy;
	}

	public int getDifficulty() {
		return difficulty;
	}
	
	public HashMap<Monster, LinkedList<Move>> getMonsters() {
		 return this.monsters;
	}
	
	public HashMap<Monster, LinkedList<Move>> getMonstersEnemy() {
		 return this.monstersEnemy;
	}
	
	public void addMonsters(Monster m, LinkedList<Move> mov) {
		 this.monsters.put(m, mov);
	}
	
	public void addMonstersEnemy(Monster m, LinkedList<Move> mov) {
		 this.monstersEnemy.put(m, mov);
	}

	public String HashtoString(HashMap<Monster, LinkedList<Move>> hm) {
		
		String r = "";
		
		Iterator it = hm.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			
			Monster m = (Monster) pair.getKey();
			LinkedList<Move> movs = (LinkedList<Move>) pair.getValue();
			
			String s = "";
			
			for (Move move : movs) {
				s += move.getName() + ", ";
				
			}
			
			r += r +"{"+ pair.getKey() + " ("+ s + ")" + "}, ";
			
			it.remove(); // avoids a ConcurrentModificationException
		}
		System.out.println(r);
		return r;
	}
	
	@Override
	public String toString() {
		return "LevelGame [name=" + name + ", enemy=" + enemy +  ", monstersEnemy="
				+ HashtoString(monstersEnemy) + ", monsters=" + HashtoString(monsters)
				+ ", difficulty=" + difficulty + "]";
	}
	
	public static void main(String[] args) {
		LevelGame lg = new LevelGame("popeye", new Enemy("popoyeye", "hi"), new HashMap<Monster, LinkedList<Move>>(), new HashMap<Monster, LinkedList<Move>>(), 0);
		
		LinkedList<Move> mos = new LinkedList<Move>();
		mos.add(new Move("tutu", 0));
		mos.add(new Move("jasjas", 0));
		
		lg.addMonsters(new MonsterFire("ourtuño", 0, 0, 0, 0), mos);
		
		System.out.println(lg);
		
	}
	
	
	
}
