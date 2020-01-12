package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import supports.Combate;

class CombateTest {

	@Test
	void testCalculo() {
		
		int	a = 20;
		int p = 40;
		int	d = 20;
		
		double	dmg = 0.196;
		
		int r = (int) dmg;
		assertEquals(r, Combate.calculo(false,a,p,d));
		
		
	}

}
