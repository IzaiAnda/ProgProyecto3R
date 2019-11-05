package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaInicioJuego extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	
	private JButton btnJugar = new JButton();
	private JButton btnAceptar = new JButton();
	private JLabel labelPkm = new JLabel();
	private JLabel labelfondo = new JLabel();
	private JLabel labelLogin = new JLabel();
	private JTextField textLogin = new JTextField();
	private JTextField textPass = new JTextField();
	

	public VentanaInicioJuego(int altura, int anchura) {
		contentpane = new JPanel();
		
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		labelPkm.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/pkm.png")));
		labelPkm.setBounds(25, -80, altura, anchura);
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
		
		
		//Eventos
		btnJugar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				btnJugar.setVisible(false);
				contentpane.remove(btnJugar);
				
				textLogin.setBounds(250, 300, 200, 20);
				textLogin.setText("Introduce usuario:");
				contentpane.add(textLogin);
				
				textPass.setBounds(250,350,200,20);
				textPass.setText("Introduce contraseña");
				contentpane.add(textPass);
				
				btnAceptar.setText("Aceptar");
				btnAceptar.setBackground(new Color(255, 175, 175));
				btnAceptar.setBounds(500, 300, 100, 20);

				btnAceptar.setVisible(true);
				contentpane.add(btnAceptar);
				//No consigo que se vea
				
				labelLogin.setText("Introduzca usuario:");
				labelLogin.setBounds(0, 0, 100, 20);
				labelLogin.setBackground(new Color(255,175,175));
				contentpane.add(labelLogin);
				
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaSeleccion ventana2 = new VentanaSeleccion(750,422);
				ventana2.setVisible(true);
				VentanaInicioJuego.this.dispose();
			}
		});
	
		
	}

}
