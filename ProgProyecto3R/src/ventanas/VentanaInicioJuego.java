package ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	
	JButton btnJugar = new JButton();
	JLabel labelPkm = new JLabel();
	JLabel labelfondo = new JLabel();
	JLabel labelLogin = new JLabel();
	JTextField textLogin = new JTextField();
	

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
				
				//No consigo que se vea
				
				/*labelLogin.setText("Introduzca usuario:");
				labelLogin.setBounds(0, 0, 200, 20);
				labelLogin.setBackground(new Color(255,175,175));
				contentpane.add(labelLogin);*/
				
			}
		});
		textLogin.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						//Abrir ventana seleccion
						
						break;
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		labelfondo.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/background.png")));
		labelfondo.setBounds(0, 0, altura, anchura);
		contentpane.add(labelfondo);
	}

}
