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

public class LevelsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private JLabel labelBackground = new JLabel();
	private JButton buttonCombat = new JButton();
	private JButton buttonBack = new JButton();

	public LevelsWindow(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		buttonCombat.setText("A LUCHAR!");
		buttonCombat.setBackground(new Color(255, 175, 175));
		buttonCombat.setBounds(150, 200, 100, 20);
		contentpane.add(buttonCombat);

		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/back2.jpg")));
		labelBackground.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackground);

		buttonBack.setText("Atrás");
		buttonBack.setBackground(new Color(255, 175, 175));
		buttonBack.setBounds(150, 250, 100, 20);
		contentpane.add(buttonBack);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		// Eventos
		buttonCombat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CombatWindow combatWindow = new CombatWindow(750, 422);
				combatWindow.setVisible(true);
				LevelsWindow.this.dispose();

			}
		});

		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionWindow selectionWindow = new SelectionWindow(750, 422);
				selectionWindow.setVisible(true);
				LevelsWindow.this.dispose();

			}
		});
	}

}
