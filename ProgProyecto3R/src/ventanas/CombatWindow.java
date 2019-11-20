package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.Rectangle;
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
	private JTextField consola = new JTextField();
	private JButton ingresar = new JButton();
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
		scroll.setBounds(new Rectangle(400,0,300,300));
		contentpane.add(scroll);
		
		consola.setBounds(400,320, 100, 20);
		contentpane.add(consola);
		
		ingresar.setBounds(520, 320, 100, 20);
		ingresar.setText("Ingresar");
		contentpane.add(ingresar);
		
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

		historial.append("Estas luchando contra Joven Chano!\n");
		historial.append("Joven Chano: ¡Te desafio!\n");
		//testeando

	}

	public void startCombat() {
		teclado = new Scanner(System.in);

		historial.append("EstÃ¡s luchando contra Joven Chano!");
		historial.append("Joven Chano: ¡Te desafio!");
		
		monstruos.add(mon);
		monstruos.add(mon2);
		monstruos.add(mon3);
		historial.append("Joven Chano saca a " + mon3.getName());
		historial.append("---------------------");
		selectMonster();
		historial.append("---------------------");
		while (seleccion > monstruos.size()) {
			selectMonster();
		}
		historial.append("Has seleccionado a: " + monstruos.get(seleccion).getName());
		historial.append("      ");
		historial.append("Â¿Que quieres hacer? (1/2)");
		historial.append("     1.Luchar");
		historial.append("     2.Huir");
		hacer = teclado.nextInt();
		if (hacer == 1) {
			menuAttack();

		}

	}

	public void selectMonster() {
		historial.append("Que pokemon quiere sacar? (0/1/2)");
		for (int i = 0; i < monstruos.size(); i++) {
			historial.append(i + ": " + monstruos.get(i).getName());
		}
		seleccion = teclado.nextInt();
	}

	public void menuAttack() {
		historial.append("Atacar:");
		historial.append("1.Placaje");
		historial.append("2.Ascuas");
		historial.append("3.Ataque rapido");
		historial.append("4.Malicioso");
	}
}
