package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import monsters.CompareMAttack;
import monsters.CompareMDefense;
import monsters.CompareMLifePoints;
import monsters.CompareMName;
import monsters.CompareMSpeed;
import monsters.CompareMType;
import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;

public class CompareTest {

	@Test
	public void compareTestAttack() {

		MonsterPlant mon = new MonsterPlant("name", 10, 10, 10, 10);
		MonsterPlant monMin = new MonsterPlant("nameMin", 5, 5, 5, 5);
		MonsterPlant monMax = new MonsterPlant("nameMax", 15, 15, 15, 15);

		CompareMAttack a = new CompareMAttack();

		int resultado1 = a.compare(mon, monMin);
		assertEquals(resultado1, -1);

		int resultado2 = a.compare(mon, monMax);
		assertEquals(resultado2, 1);

		int resultado3 = a.compare(mon, mon);
		assertEquals(resultado3, 0);

	}

	@Test
	public void compareTestDefense() {

		MonsterPlant mon = new MonsterPlant("name", 10, 10, 10, 10);
		MonsterPlant monMin = new MonsterPlant("nameMin", 5, 5, 5, 5);
		MonsterPlant monMax = new MonsterPlant("nameMax", 15, 15, 15, 15);

		CompareMDefense a = new CompareMDefense();

		int resultado1 = a.compare(mon, monMin);
		assertEquals(resultado1, -1);

		int resultado2 = a.compare(mon, monMax);
		assertEquals(resultado2, 1);

		int resultado3 = a.compare(mon, mon);
		assertEquals(resultado3, 0);

	}

	@Test
	public void compareTestLifePoints() {

		MonsterPlant mon = new MonsterPlant("name", 10, 10, 10, 10);
		MonsterPlant monMin = new MonsterPlant("nameMin", 5, 5, 5, 5);
		MonsterPlant monMax = new MonsterPlant("nameMax", 15, 15, 15, 15);

		CompareMLifePoints a = new CompareMLifePoints();

		int resultado1 = a.compare(mon, monMin);
		assertEquals(resultado1, -1);

		int resultado2 = a.compare(mon, monMax);
		assertEquals(resultado2, 1);

		int resultado3 = a.compare(mon, mon);
		assertEquals(resultado3, 0);

	}

	@Test
	public void compareTestName() {

		MonsterPlant mon = new MonsterPlant("name", 10, 10, 10, 10);
		MonsterPlant monMin = new MonsterPlant("nameMin", 5, 5, 5, 5);
		MonsterPlant monMax = new MonsterPlant("nameMax", 15, 15, 15, 15);

		CompareMName a = new CompareMName();

		int resultado1 = a.compare(mon, monMin);
		assertEquals(resultado1, -3);

		int resultado2 = a.compare(monMax, mon);
		assertEquals(resultado2, 3);

		int resultado3 = a.compare(mon, mon);
		assertEquals(resultado3, 0);


	}

	@Test
	public void compareTestSpeed() {

		MonsterPlant mon = new MonsterPlant("name", 10, 10, 10, 10);
		MonsterPlant monMin = new MonsterPlant("nameMin", 5, 5, 5, 5);
		MonsterPlant monMax = new MonsterPlant("nameMax", 15, 15, 15, 15);

		CompareMSpeed a = new CompareMSpeed();

		int resultado1 = a.compare(mon, monMin);
		assertEquals(resultado1, -1);

		int resultado2 = a.compare(mon, monMax);
		assertEquals(resultado2, 1);

		int resultado3 = a.compare(mon, mon);
		assertEquals(resultado3, 0);

	}

	@Test
	public void compareTestType() {

		MonsterPlant mon = new MonsterPlant("name", 10, 10, 10, 10);
		MonsterFire monMin = new MonsterFire("nameMin", 5, 5, 5, 5);
		MonsterWater monMax = new MonsterWater("nameMax", 15, 15, 15, 15);

		CompareMType a = new CompareMType();

		int resultado1 = a.compare(mon, monMin);
		assertEquals(-1, resultado1);


		int resultado2 = a.compare(mon, monMax);
		assertEquals(resultado2, 1);


		int resultado3 = a.compare(mon, mon);
		assertEquals(resultado3, 0);


	}



}
