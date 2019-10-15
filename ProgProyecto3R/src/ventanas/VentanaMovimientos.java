package ventanas;

import javax.swing.JFrame;

public class VentanaMovimientos extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaMovimientos(int altura, int anchura) {
		

		setSize(altura, anchura);
		setLocation(400, 250);
		setTitle("Moves");
	}

}
