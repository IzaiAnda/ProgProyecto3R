package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseJuego.BD;
import level.Enemy;
import level.LevelGame;
import level.Player;

public class LevelsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private JPanel buttonsLeves = new JPanel();
	private JLabel labelBackground = new JLabel();
	private JButton buttonBack = new JButton();
	private LinkedList<LevelGame> levels ;
	private LinkedList<String> names ;
	private Player p;
	
	public LevelsWindow(int altura, int anchura, String nom) {
		contentpane = new JPanel();
		buttonsLeves.setVisible(true);
		p = BD.selectJugadorClass(nom);
		
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		levels = BD.selectAllLevelsUnder(p.getLevel());
		
		for (LevelGame levelGame : levels) {
			names.add(levelGame.getName());
		}
		
		for (int i = 0; i < levels.size(); i++) {
			System.out.println(levels.size());
			JButton buttonCombat = new JButton();
//			buttonCombat.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_"+ i +".png")));
			buttonCombat.setText(Integer.toString(i));
			buttonCombat.setBounds(420, 320, 100, 20);
			buttonsLeves.add(buttonCombat);						
			buttonCombat.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CombatWindow combatWindow = new CombatWindow(750, 422,levels.get(Integer.parseInt(buttonCombat.getText())).getName(),nom);
					combatWindow.setVisible(true);
					LevelsWindow.this.dispose();

				}
			});
		}
		
		buttonsLeves.repaint();
		contentpane.add(buttonsLeves);
		
		buttonBack.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_salir.png")));
		buttonBack.setBounds(600, 300, 100, 45);
		contentpane.add(buttonBack);
	
//		
//		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/FightWall.jpg")));
//		labelBackground.setBounds(0, 0, altura, anchura);
//		contentpane.add(labelBackground);

	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionWindow selectionWindow = new SelectionWindow(750, 422, nom);
				selectionWindow.setVisible(true);
				LevelsWindow.this.dispose();

			}
		});
	}

}
