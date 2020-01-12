package tests;

import static org.junit.Assert.*;

import org.junit.Before;

import baseJuego.BD;

import org.junit.Test;

public class BDTest {

	@Before
	private void setUp() {
		BD.startBD();
	}

	@Test
	public void test() {
		BD.create();

	}

}
