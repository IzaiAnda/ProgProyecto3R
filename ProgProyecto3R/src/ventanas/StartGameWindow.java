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

public class StartGameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();

	private JButton buttonPlay = new JButton();
	private JButton buttonAccept = new JButton();
	private JLabel labelMonster = new JLabel();
	private JLabel labelBackground = new JLabel();
	private JLabel labelLogin = new JLabel();
	private JTextField testUser = new JTextField();
	private JTextField textPass = new JTextField();

	public StartGameWindow(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		labelMonster.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/pkm.png")));
		labelMonster.setBounds(25, -80, altura, anchura);
		contentpane.add(labelMonster);

		buttonPlay.setText("Pulse para jugar");
		buttonPlay.setBackground(new Color(255, 175, 175));
		buttonPlay.setBounds(250, 300, 200, 20);
		contentpane.add(buttonPlay);

		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/background.png")));
		labelBackground.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackground);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		// Eventos
		buttonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttonPlay.setVisible(false);
				contentpane.remove(buttonPlay);

				testUser.setBounds(250, 300, 200, 20);
				testUser.setText("Introduce usuario:");
				contentpane.add(testUser);

				textPass.setBounds(250, 350, 200, 20);
				textPass.setText("Introduce contraseña");
				contentpane.add(textPass);

				buttonAccept.setText("Aceptar");
				buttonAccept.setBackground(new Color(255, 175, 175));
				buttonAccept.setBounds(500, 300, 100, 20);

				buttonAccept.setVisible(true);
				contentpane.add(buttonAccept);
				// No consigo que se vea

				labelLogin.setText("Introduzca usuario:");
				labelLogin.setBounds(0, 0, 100, 20);
				labelLogin.setBackground(new Color(255, 175, 175));
				contentpane.add(labelLogin);

			}
		});
		buttonAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectionWindow ventana2 = new SelectionWindow(750, 422);
				ventana2.setVisible(true);
				StartGameWindow.this.dispose();
			}
		});

	}

}
