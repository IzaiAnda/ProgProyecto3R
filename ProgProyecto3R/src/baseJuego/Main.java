package baseJuego;

import ventanas.Pokedex;
import ventanas.StartGameWindow;
import baseJuego.BD;

public class Main {

	public static void main(String[] args) {
		
		BD.startBD();
		
		StartGameWindow starWindow = new StartGameWindow(750, 422);

		starWindow.setVisible(true);
		
		


	}

}
