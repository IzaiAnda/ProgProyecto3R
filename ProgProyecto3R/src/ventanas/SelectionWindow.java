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

import supports.Player;

public class SelectionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private JLabel labelBackground = new JLabel();
	private JButton buttonCombat = new JButton();
	private JButton buttonPokedex = new JButton();
	private JButton buttonMovements = new JButton();
	private JButton buttonOptions = new JButton();
	private JButton buttonExit = new JButton();

	public SelectionWindow(int altura, int anchura, String nom) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		//buttonCombat.setText("COMBATE");
		buttonCombat.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_combate.png")));
		buttonCombat.setBounds(140, 100, 160, 44);
		contentpane.add(buttonCombat);

		//buttonPokedex.setText("DEUSTMON");
		buttonPokedex.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_deustmons.png")));
		buttonPokedex.setBounds(400, 100, 188, 45);
		contentpane.add(buttonPokedex);

		//buttonMovements.setText("MOVIMIENTOS");
		buttonMovements.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_movimientos.png")));
		buttonMovements.setBounds(110, 200, 212, 44);
		contentpane.add(buttonMovements);

		//buttonOptions.setText("OPCIONES");
		buttonOptions.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_opciones.png")));
		buttonOptions.setBounds(415, 200, 161, 45);
		contentpane.add(buttonOptions);

		//buttonExit.setText("SALIR");
		buttonExit.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_salir.png")));
		buttonExit.setBounds(600, 300, 100, 45);
		contentpane.add(buttonExit);

		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/back2.jpg")));
		labelBackground.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackground);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		// Eventos
		buttonCombat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LevelsWindow levelsWindow = new LevelsWindow(750, 422, nom);
				levelsWindow.setVisible(true);
				SelectionWindow.this.dispose();

			}
		});

		buttonPokedex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*PokedexWindow pokedexWindow = new PokedexWindow(750, 422);
				pokedexWindow.setVisible(true);*/
				
				Pokedex pokedex = new Pokedex();
				pokedex.setVisible(true);

			}
		});

		buttonMovements.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MovementsWindow movementsWindow = new MovementsWindow(750, 422);
				movementsWindow.setVisible(true);

			}
		});

		buttonOptions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionsWindow optionsWindow = new OptionsWindow(750, 422);
				optionsWindow.setVisible(true);

			}
		});

		buttonExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionWindow.this.dispose();
				System.exit(0);

			}
		});

	}
}
