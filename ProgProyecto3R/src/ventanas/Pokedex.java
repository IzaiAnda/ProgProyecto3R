package ventanas;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.SwingUtilities;

import baseJuego.BD;
import monsters.Monster;

public class Pokedex extends JFrame{

	static ImageIcon icon;

	private static final long serialVersionUID = 1L;
	static int count = 0;



	public Pokedex() {

		this.setSize(800, 420);
		this.setLocation(500, 250);
		JTextArea monsters = new JTextArea("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n  ");
		JScrollPane scrPane = new JScrollPane(monsters);
		JTextField searcher = new JTextField();
		JLabel image = new JLabel();
		JButton search = new JButton("Search");
		JButton back = new JButton("Back");
		JLabel labelBackGround = new JLabel();
		JLabel backImage = new JLabel();

		LinkedList<Monster> monsterList = new LinkedList<Monster>();

		monsterList = BD.selectAllMonsters();

		System.out.println(monsterList.size());

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
		JButton mons16 = new JButton();
		buttons.add(mons16);
		JButton mons17 = new JButton();
		buttons.add(mons17);
		JButton mons18 = new JButton();
		buttons.add(mons18);
		JButton mons19 = new JButton();
		buttons.add(mons19);
		JButton mons20 = new JButton();
		buttons.add(mons20);


		for (int i = 0; i < monsterList.size(); i++) {
			buttons.get(i).setText(monsterList.get(i).getName());
		}

		JTextArea monstersData = new JTextArea();

		setLayout(null);
		scrPane.setBounds(10, 50, 150, 320);
		this.add(scrPane);

		image.setBounds(525, 15, 180, 180);
		this.add(image);

		backImage.setBounds(485, 15, 270, 330);
		this.add(backImage);

		title.setBounds(10, 15, 75, 25);
		this.add(title);

		monstersData.setBounds(500, 220, 240, 110);
		this.add(monstersData);

		searcher.setBounds(100, 15, 100, 25);
		this.add(searcher);

		search.setBounds(210, 15, 75, 25);
		this.add(search);

		back.setBounds(300, 15, 75, 25);
		this.add(back);

		labelBackGround.setBounds(0, 0, 800, 400);
		this.add(labelBackGround);

		labelBackGround.setIcon(new ImageIcon(Pokedex.class.getResource("/images/backPok.png")));

		backImage.setIcon(new ImageIcon(Pokedex.class.getResource("/images/backImg.jpg")));


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

					monstersData.setText("  Name: " + name + "\n" + "  Type: " + type + "\n" + "  HP: " + hp + "\n"
							+ "  Attack: " + attack + "\n" + "  Defense: " + defense + "\n" + "  Speed: " + speed + "\n");

					icon = new ImageIcon(Pokedex.class.getResource("/images/monsters/" + name + ".png"));
					image.setIcon(icon);

				}
			});

		}



		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String name = searcher.getText();

				for (int i = 0; i < buttons.size(); i++) {

					String nameb = buttons.get(i).getText();

					if (name.equals(nameb)) {
						buttons.get(i).doClick();
					}
				}

			}

		});




		monsters.add(mons1);
		mons1.setBounds(5, 5, 120, 25);

		monsters.add(mons2);
		mons2.setBounds(5, 35, 120, 25);

		monsters.add(mons3);
		mons3.setBounds(5, 65, 120, 25);

		monsters.add(mons4);
		mons4.setBounds(5, 95, 120, 25);

		monsters.add(mons5);
		mons5.setBounds(5, 125, 120, 25);

		monsters.add(mons6);
		mons6.setBounds(5, 155, 120, 25);

		monsters.add(mons7);
		mons7.setBounds(5, 185, 120, 25);

		monsters.add(mons8);
		mons8.setBounds(5, 215, 120, 25);

		monsters.add(mons9);
		mons9.setBounds(5, 245, 120, 25);

		monsters.add(mons10);
		mons10.setBounds(5, 275, 120, 25);

		monsters.add(mons11);
		mons11.setBounds(5, 305, 120, 25);

		monsters.add(mons12);
		mons12.setBounds(5, 335, 120, 25);

		monsters.add(mons13);
		mons13.setBounds(5, 365, 120, 25);

		monsters.add(mons14);
		mons14.setBounds(5, 395, 120, 25);

		monsters.add(mons15);
		mons15.setBounds(5, 425, 120, 25);

		monsters.add(mons16);
		mons16.setBounds(5, 455, 120, 25);

		monsters.add(mons17);
		mons17.setBounds(5, 485, 120, 25);

		monsters.add(mons18);
		mons18.setBounds(5, 515, 120, 25);

		monsters.add(mons19);
		mons19.setBounds(5, 545, 120, 25);

		monsters.add(mons20);
		mons20.setBounds(5, 575, 120, 25);




	}

}
