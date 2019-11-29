package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import monsters.CompareMAttack;
import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;


public class CompareMAll {
	
	Monster a = new MonsterFire("a", 1, 2, 3, 4);
	Monster b = new MonsterPlant("b", 2, 3, 4, 1);
	Monster c = new MonsterWater("c", 3, 4, 1, 2);
	List list = new ArrayList<Monster>();
	List list2 = new ArrayList<Monster>();
	
	@Test
	public void test() {
		list.add(a);
		list.add(b);
		list.add(c);
		Collections.sort(list, new CompareMAttack());
		list2.add(c);
		list2.add(b);
		list2.add(a);
		
		assertEquals(list, list2);
		
	}
	
}
