package level;

import java.util.ArrayList;

import monsters.Monster;

public class LevelGame {
	
	private String name;
	private Enemy enemy;
	private ArrayList<Monster> monsters;
	private int difficulty;
	
	public LevelGame(String name,Enemy enemy, ArrayList<Monster> monsters, int difficulty) {
		super();
		this.name = name;
		this.enemy = enemy;
		this.monsters = monsters;
		this.difficulty = difficulty;
	}
	public String getName() {
		return name;
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	public int getDifficulty() {
		return difficulty;
	}
	
	public void addMonster(Monster m) {
		 this.monsters.add(m);
	}
	
	
	
}
