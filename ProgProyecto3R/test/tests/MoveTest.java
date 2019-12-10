package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import moves.Move;

public class MoveTest {

	@Test
	public void testMove() {
		
		Move a = new Move("move", 20);
		
		int resultado = a.getDamage();
		assertEquals(20, resultado);
		
		String resultado2 = a.getName();
		assertEquals("move", resultado2);
		
	}

}
