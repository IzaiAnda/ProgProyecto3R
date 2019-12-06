package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import baseJuego.BD;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StartGameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private static int seguro = 0;  
	//al alcanzar 10 intentos de inicio de sesion, 
	//va a ddesaparecer el boton para que no pueda continuar buscando un usuario
	private JButton buttonPlay = new JButton();
	private JButton buttonAccept = new JButton();
	private JLabel labelMonster = new JLabel();
	private JLabel labelBackGround = new JLabel();
	private JTextField textLogin = new JTextField();
	private JTextField textPass = new JTextField();
	private JLabel labelUser = new JLabel();
	private JLabel labelPass = new JLabel();


	public StartGameWindow(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		labelMonster.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/pkm.png")));
		labelMonster.setBounds(25, -80, altura, anchura);
		contentpane.add(labelMonster);

		buttonPlay.setText("Pulse para jugar");
		buttonPlay.setBackground(new Color(255, 175, 175));
		buttonPlay.setBounds(250, 300, 200, 20);
		contentpane.add(buttonPlay);

		buttonAccept.setText("Aceptar");
		buttonAccept.setBackground(new Color(255, 175, 175));
		buttonAccept.setBounds(500, 300, 100, 20);
		contentpane.add(buttonAccept);
		buttonAccept.setVisible(false);

		labelUser.setText("Nombre de Usuario:");
		labelUser.setBounds(250, 280, 200, 20);
		contentpane.add(labelUser);
		labelUser.setVisible(false);

		labelPass.setText("ContraseÃ±a:");
		labelPass.setBounds(250, 330, 200, 20);
		contentpane.add(labelPass);
		labelPass.setVisible(false);

		labelBackGround.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/background.png")));
		labelBackGround.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackGround);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");


		//Eventos

		buttonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttonPlay.setVisible(false);
				contentpane.remove(buttonPlay);

				labelUser.setVisible(true);
				labelPass.setVisible(true);

				textLogin.setBounds(250, 300, 200, 20);
				contentpane.add(textLogin);

				textPass.setBounds(250,350,200,20);
				contentpane.add(textPass);

				repaint();

				buttonAccept.setVisible(true);

				/*labelLogin.setText("Introduzca usuario:");
				labelLogin.setBounds(0, 0, 100, 20);
				labelLogin.setBackground(new Color(255,175,175));
				contentpane.add(labelLogin);*/

			}
		});

		buttonAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nom = textLogin.getText();
				String pass = textPass.getText();
				if(BD.selectJugador(nom, pass)) {
					SelectionWindow ventana2 = new SelectionWindow(750,422);
					ventana2.setVisible(true);
					StartGameWindow.this.dispose();
				}else {
					//					NewUserWindow newUserWindow = new NewUserWindow(750, 422,nom,pass);
					//					newUserWindow.setVisible(true);

					//Por seguridad no especificamos si el nombre o la contraseña es incorrecta

					int x = JOptionPane.showConfirmDialog(null, "Usuario ó contraseña incorrecta. \nQuiere crear uno con los mismos datos?" );
					if (x == 0) {
						BD.createJugador(nom, pass);
						EventQueue.invokeLater(new Runnable() {

							@Override
							public void run() {

								JOptionPane.showInputDialog("DONE");

							}
						});

					}else if(x == 1) {
						seguro++;

					}
				}
				if(seguro == 10) {
					buttonAccept.setVisible(false);
					System.out.println("patata");
				}
			}

		});	


	}


}
