package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaNiveles extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentpane = new JPanel();
	private JLabel labelfondo = new JLabel();
	private JButton botonCombate = new JButton();
	private JButton botonAtras = new JButton();

	public VentanaNiveles(int altura, int anchura) {
		contentpane = new JPanel();
		
		
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		botonCombate.setText("¡LUCHA!");
		botonCombate.setBackground(new Color(255, 175, 175));
		botonCombate.setBounds(150, 200, 100, 20);
		contentpane.add(botonCombate);
		
		labelfondo.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/back2.jpg")));
		labelfondo.setBounds(0, 0, altura, anchura);
		contentpane.add(labelfondo);
		
		botonAtras.setText("Atrás");
		botonAtras.setBackground(new Color(255, 175, 175));
		botonAtras.setBounds(150,250,100,20);
		contentpane.add(botonAtras);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");
		
		//Eventos
		botonCombate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCombate ventanac = new VentanaCombate(750, 422);
				ventanac.setVisible(true);
				VentanaNiveles.this.dispose();
				
			}
		});
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccion ventanac = new VentanaSeleccion(750, 422);
				ventanac.setVisible(true);
				VentanaNiveles.this.dispose();
				
			}
		});
	}

}
