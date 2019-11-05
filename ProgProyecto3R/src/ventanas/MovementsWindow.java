package ventanas;

import javax.swing.JFrame;

public class MovementsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovementsWindow(int altura, int anchura) {

		setSize(altura, anchura);
		setLocation(400, 250);
		setTitle("Moves");
	}

}
