package ventanas;

import javax.swing.JFrame;

public class VentanaMovimientos extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaMovimientos(int altura, int anchura) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setLocation(400, 250);
		setTitle("DeustMon");
	}

}
