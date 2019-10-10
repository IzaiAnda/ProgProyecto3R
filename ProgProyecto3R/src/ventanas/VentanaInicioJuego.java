package ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaInicioJuego extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	
	JLabel labelfondo = new JLabel();
	JButton btnJugar = new JButton();
	JLabel labelPkm = new JLabel();
	

	public VentanaInicioJuego(int altura, int anchura) {
		contentpane = new JPanel();
		
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		labelPkm.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/pkm.png")));
		labelPkm.setBounds(25, -50, altura, anchura);
		contentpane.add(labelPkm);
		
		btnJugar.setText("Pulse para jugar");
		btnJugar.setBackground(new Color(255, 175, 175));
		btnJugar.setBounds(250, 300, 200, 20);
		contentpane.add(btnJugar);
		
		labelfondo.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/background.png")));
		labelfondo.setBounds(0, 0, altura, anchura);
		contentpane.add(labelfondo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");
	}

}
