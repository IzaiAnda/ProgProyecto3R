package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import baseJuego.JPanelBackground;
import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;

public class CombatWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MonsterPlant mon = new MonsterPlant("Plantita", 100, 100, 100, 100);
	MonsterFire mon2 = new MonsterFire("Fuegillo", 10, 10, 10, 10);
	MonsterWater mon3 = new MonsterWater("Gotita", 50, 50, 50, 50);
	ArrayList<Monster> monstruos = new ArrayList<Monster>();

	private JPanelBackground contentpane;
	private JTextArea historial = new JTextArea();
	private JButton option1 = new JButton();
	private JButton option2 = new JButton();
	private JButton option3 = new JButton();
	private JButton option4 = new JButton();
	private JButton fight = new JButton();
	private JButton run = new JButton();
	private JLabel labelBackGround = new JLabel();
	private int seleccion;
	private int hacer;
	private Scanner teclado;
	private JScrollPane scroll;

	public CombatWindow(int altura, int anchura) {
		contentpane = new JPanelBackground();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		scroll = new JScrollPane(historial);
		scroll.setBounds(new Rectangle(420, 0, 300, 300));
		contentpane.add(scroll);

		historial.setLineWrap(true);
		historial.setWrapStyleWord(true);
		historial.setEditable(false);

		option1.setBounds(420, 320, 100, 20);
		option1.setText("1");
		contentpane.add(option1);

		option2.setBounds(540, 320, 100, 20);
		option2.setText("2");
		contentpane.add(option2);

		option3.setBounds(420, 350, 100, 20);
		option3.setText("3");
		contentpane.add(option3);

		option4.setBounds(540, 350, 100, 20);
		option4.setText("4");
		contentpane.add(option4);

		fight.setBounds(100, 320, 100, 20);
		fight.setText("Luchar");
		contentpane.add(fight);

		run.setBounds(220, 320, 100, 20);
		run.setText("Huir");
		contentpane.add(run);

		labelBackGround.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/escenario.png")));
		labelBackGround.setBounds(0, -100, altura, anchura);
		contentpane.add(labelBackGround);

		String imagepath = ("/images/background.png");
		contentpane.setOpaque(false);
		contentpane.setBackgroundd(imagepath);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		// historial.append("Estas luchando contra Joven Chano!\n");
		// historial.append("Joven Chano: �Te desafio!\n");
		// testeando
		startCombat();

		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionWindow selectionWindow = new SelectionWindow(750, 422);
				selectionWindow.setVisible(true);
				CombatWindow.this.dispose();

			}
		});
		
		option1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("1\n");
				seleccion = 1;

			}
		});

		option2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("2\n");
				seleccion = 2;

			}
		});

		option3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("3\n");
				seleccion = 3;

			}
		});

		option4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("4\n");
				seleccion = 4;

			}
		});

	}

	public void startCombat() {
		monstruos.add(mon);
		monstruos.add(mon2);
		monstruos.add(mon3);

		seleccion=0;

		historial.append("Estás luchando contra Joven Chano!\n");
		historial.append("Joven Chano: Te desafio!\n");

		historial.append("Joven Chano saca a " + mon3.getName() + "\n");
		historial.append("---------------------\n");
		selectMonster();
		historial.append("---------------------\n");

		historial.append("Has seleccionado a: " + monstruos.get(seleccion).getName() + "\n");
		historial.append("      \n");
		historial.append("¿Que quieres hacer? (1/2)\n");
		historial.append("     Luchar\n");
		historial.append("     Huir\n");

		if (hacer == 1) {
			menuAttack();

		}

	}

	public void selectMonster() {
		historial.append("Que pokemon quiere sacar? (0/1/2)\n");
		for (int i = 0; i < monstruos.size(); i++) {
			historial.append(i + ": " + monstruos.get(i).getName() + "\n");
		}
		
	}

	public void menuAttack() {
		historial.append("Atacar:\n");
		historial.append("1.Placaje\n");
		historial.append("2.Ascuas\n");
		historial.append("3.Ataque rapido\n");
		historial.append("4.Malicioso\n");
	}

	public void selectOption() {

		

	}

}
