package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import monsters.Monster;
import monsters.Monster.Type;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;

public class MonsterTest {

	@Test
	public void test() {

		Monster a = new Monster("name", 10, 10, 10, 10, Type.FIRE) {

			@Override
			public boolean efectividad(Type type) {
				return false;
			}

		};

		String resultado = a.getName();
		assertEquals("name", resultado);

		int resultado2 = a.getLifePoints();
		assertEquals(10, resultado2);

		resultado2 = a.getAttack();
		assertEquals(10, resultado2);

		resultado2 = a.getDefense();
		assertEquals(10, resultado2);

		resultado2 = a.getSpeed();
		assertEquals(10, resultado2);

		Type resultado3 = a.getType();
		assertEquals(Type.FIRE, resultado3);

	}

	@Test
	public void testFire() {

		MonsterFire a = new MonsterFire("name", 10, 10, 10, 10);

		boolean resultado = a.efectividad(Type.WATER);
		assertEquals(true, resultado);



		boolean resultado2 = a.efectividad(Type.PLANT);
		assertEquals(false, resultado2);

	}

	@Test
	public void testWater() {

		MonsterWater a = new MonsterWater("name", 10, 10, 10, 10);

		boolean resultado = a.efectividad(Type.PLANT);
		assertEquals(true, resultado);



		boolean resultado2 = a.efectividad(Type.FIRE);
		assertEquals(false, resultado2);

	}

	@Test
	public void testPlant() {

		MonsterPlant a = new MonsterPlant("name", 10, 10, 10, 10);

		boolean resultado = a.efectividad(Type.FIRE);
		assertEquals(true, resultado);



		boolean resultado2 = a.efectividad(Type.WATER);
		assertEquals(false, resultado2);

	}



}
