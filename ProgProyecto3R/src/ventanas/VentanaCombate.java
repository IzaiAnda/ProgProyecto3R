package ventanas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaCombate extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentpane = new JPanel();
	private JLabel labelfondo = new JLabel();

	public VentanaCombate(int altura, int anchura) {
		contentpane = new JPanel();
		
		
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		labelfondo.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/images.jpg")));
		labelfondo.setBounds(0, 0, altura, anchura);
		contentpane.add(labelfondo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");
	}

}
