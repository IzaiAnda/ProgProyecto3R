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

	public SelectionWindow(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		buttonCombat.setText("COMBATE");
		buttonCombat.setBackground(new Color(255, 175, 175));
		buttonCombat.setBounds(200, 100, 100, 20);
		contentpane.add(buttonCombat);

		buttonPokedex.setText("DEUSTMON");
		buttonPokedex.setBackground(new Color(255, 175, 175));
		buttonPokedex.setBounds(350, 100, 100, 20);
		contentpane.add(buttonPokedex);

		buttonMovements.setText("MOVIMIENTOS");
		buttonMovements.setBackground(new Color(255, 175, 175));
		buttonMovements.setBounds(200, 200, 100, 20);
		contentpane.add(buttonMovements);

		buttonOptions.setText("OPCIONES");
		buttonOptions.setBackground(new Color(255, 175, 175));
		buttonOptions.setBounds(350, 200, 100, 20);
		contentpane.add(buttonOptions);

		buttonExit.setText("SALIR");
		buttonExit.setBackground(new Color(255, 175, 175));
		buttonExit.setBounds(400, 300, 100, 20);
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
				LevelsWindow levelsWindow = new LevelsWindow(750, 422);
				levelsWindow.setVisible(true);
				SelectionWindow.this.dispose();

			}
		});

		buttonPokedex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PokedexWindow pokedexWindow = new PokedexWindow(750, 422);
				pokedexWindow.setVisible(true);

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
				;
				SelectionWindow.this.dispose();
				System.exit(0);

			}
		});

	}
}
