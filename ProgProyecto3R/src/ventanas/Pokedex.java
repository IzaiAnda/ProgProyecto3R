package ventanas;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import baseJuego.BD;
import monsters.Monster;



public class Pokedex extends JFrame{

		static int count = 0;
		
	public Pokedex() {
		
		this.setSize(800,400);
		
		JPanel container = new JPanel();
		JTextArea monsters = new JTextArea();
		JScrollPane scrPane = new JScrollPane(monsters);
		JTextField searcher = new JTextField();
		JLabel image = new JLabel();
		JButton search = new JButton("Search");
		JButton back = new JButton("Back");
		ArrayList<Image> images = new ArrayList<Image>();
		
		
		
		LinkedList<Monster> monsterList = new LinkedList<Monster>();
		
		
		monsterList = BD.selectAllMonsters();
		PokedexWindow.addMonsters(monsterList);
		
		ArrayList<JButton> buttons = new ArrayList<JButton>();

		JLabel title = new JLabel("Monsters");
		JButton mons1 = new JButton();
		buttons.add(mons1);
		JButton mons2 = new JButton();
		buttons.add(mons2);
		JButton mons3 = new JButton();
		buttons.add(mons3);
		JButton mons4 = new JButton();
		buttons.add(mons4);
		JButton mons5 = new JButton();
		buttons.add(mons5);
		JButton mons6 = new JButton();
		buttons.add(mons6);
		JButton mons7 = new JButton();
		buttons.add(mons7);
		JButton mons8 = new JButton();
		buttons.add(mons8);
		JButton mons9 = new JButton();
		buttons.add(mons9);
		JButton mons10 = new JButton();
		buttons.add(mons10);
		JButton mons11 = new JButton();
		buttons.add(mons11);
		JButton mons12 = new JButton();
		buttons.add(mons12);
		JButton mons13 = new JButton();
		buttons.add(mons13);
		JButton mons14 = new JButton();
		buttons.add(mons14);
		JButton mons15 = new JButton();
		buttons.add(mons15);
		
		
		for (int i = 0; i < monsterList.size(); i++) {
			buttons.get(i).setText(monsterList.get(i).getName());
			
		}
		
		JTextArea monstersData = new JTextArea();
		
		
		container.setSize(100, 100);
		setLayout(null);
		scrPane.setBounds(10, 50, 400, 300);
		this.add(scrPane);
		
		image.setBounds(525, 15, 180, 180);
		
		this.add(image);
		
		title.setBounds(10, 15, 75, 25);
		this.add(title);
		
		monstersData.setBounds(450, 200, 300, 150);
		this.add(monstersData);
		
		searcher.setBounds(100, 15, 100, 25);
		this.add(searcher);
		
		search.setBounds(210, 15, 75, 25);
		this.add(search);
		
		monsters.setLineWrap(true);
        monsters.setWrapStyleWord(true);
        monsters.setEditable(false);
		
        back.setBounds(300, 15, 75, 25);
		this.add(back);
		
		
		for (int i = 0; i < monsterList.size(); i++) {
				
			count = i;
			String name = monsterList.get(count).getName();
			String type = monsterList.get(count).getTypeString();
			int hp = monsterList.get(count).getLifePoints();
			int attack = monsterList.get(count).getAttack();
			int defense = monsterList.get(count).getDefense();
			int speed = monsterList.get(count).getSpeed();
			
			buttons.get(i).addActionListener(new ActionListener() {
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					monstersData.setText(null);
					
					monstersData.setText(	"Name: " + 	name + 	"\n" + 
											"Type: " + 	type +	 "\n" + 
											"HP: " 	+	 hp + 	"\n" + 
											"Attack: " + attack + "\n" + 
											"Defense: " + defense + "\n" + 
											"Speed: " + speed + 	"\n");
					
					
					
				}
				
			});
			
		}
		
	
		monsters.add(mons1);
		mons1.setBounds(5, 5, 100, 25);
		
		monsters.add(mons2);
		mons2.setBounds(5, 35, 100, 25);
		
		monsters.add(mons3);
		mons3.setBounds(5, 65, 100, 25);
		
		monsters.add(mons4);
		mons4.setBounds(5, 95, 100, 25);
		
		monsters.add(mons5);
		mons5.setBounds(5, 125, 100, 25);
		
		monsters.add(mons6);
		mons6.setBounds(5, 155, 100, 25);
		
		monsters.add(mons7);
		mons7.setBounds(5, 185, 100, 25);
		
		monsters.add(mons8);
		mons8.setBounds(5, 215, 100, 25);
		
		monsters.add(mons9);
		mons9.setBounds(5, 245, 100, 25);
		
		monsters.add(mons10);
		mons10.setBounds(5, 275, 100, 25);
		
		monsters.add(mons11);
		mons11.setBounds(5, 305, 100, 25);
		
		monsters.add(mons12);
		mons12.setBounds(5, 335, 100, 25);
		
		monsters.add(mons13);
		mons13.setBounds(5, 365, 100, 25);
		
		monsters.add(mons14);
		mons14.setBounds(5, 395, 100, 25);
		
		monsters.add(mons15);
		mons15.setBounds(5, 425, 100, 25);
		
		
	}
	
	public static void main(String[] args) {
		Pokedex pokedex = new Pokedex();
		pokedex.setVisible(true);
	}

}
