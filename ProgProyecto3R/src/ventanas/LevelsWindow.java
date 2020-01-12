package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseJuego.BD;
import supports.Enemy;
import supports.LevelGame;
import supports.Player;

public class LevelsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private JLabel labelBackground = new JLabel();
	private JButton buttonBack = new JButton();
	private LinkedList<LevelGame> levels ;
	private Player p;
	
	public LevelsWindow(int altura, int anchura, String nom) {
		contentpane = new JPanel();
		
		p = BD.selectJugadorClass(nom);
		
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		levels = BD.selectAllLevelsUnder(p.getLevel());
		
		JButton button1 = new JButton();
		button1.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_0.png")));
		button1.setBounds(150, 150, 60, 40);
		JButton button2 = new JButton();
		button2.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_1.png")));
		button2.setBounds(220, 150, 60, 40);
		JButton button3 = new JButton();
		button3.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_2.png")));
		button3.setBounds(290, 150, 60, 40);
		
		LinkedList<String> names = new LinkedList<String>();
		
		for (LevelGame levelGame : levels) {
            names.add(levelGame.getName());
        }
		
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		
		for (int i = 0; i < buttons.size(); i++) {
				
			contentpane.add(buttons.get(i));
			
			String name = names.get(i);
			
			buttons.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CombatWindow combatWindow = new CombatWindow(750, 422,name,nom);
					combatWindow.setVisible(true);
					LevelsWindow.this.dispose();
					
				}
			});}
	
			
		
		

		buttonBack.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/button_salir.png")));
		buttonBack.setBounds(600, 300, 100, 45);
		contentpane.add(buttonBack);
	
		
		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/FightWall.jpg")));
		labelBackground.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackground);

	
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