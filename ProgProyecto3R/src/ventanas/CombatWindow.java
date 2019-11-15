package ventanas;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;

public class CombatWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MonsterPlant mon = new MonsterPlant("Plantita",100,100,100,100);
	MonsterFire mon2 = new MonsterFire("Fuegillo",10,10,10,10);
	MonsterWater mon3 = new MonsterWater("Gotita",50,50,50,50);
	ArrayList<Monster> monstruos= new ArrayList<Monster>();

	private JPanel contentpane = new JPanel();
	private JLabel labelBackground = new JLabel();
	private int seleccion;
	private int hacer;
	private Scanner teclado;

	public CombatWindow(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/images.jpg")));
		labelBackground.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackground);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");
		
		startCombat();
	}

	public void startCombat() {
		teclado = new Scanner(System.in);
		
		System.out.println("Estás luchando contra Santiago!");
		System.out.println("Santiago: Arriba Espa...perdón");
		monstruos.add(mon);
		monstruos.add(mon2);
		monstruos.add(mon3);
		System.out.println("Santiago saca a " + mon3.getName());
		System.out.println("---------------------");
		System.out.println("Que pokemon quiere sacar? (0/1/2)");
		for (int i = 0; i < monstruos.size(); i++) {
			System.out.println(i + ": " + monstruos.get(i).getName());
		}
		seleccion = teclado.nextInt();	
		System.out.println("---------------------");
		System.out.println("Has seleccionado a:" + monstruos.get(seleccion).getName());
		System.out.println("      ");
		System.out.println("¿Que quieres hacer? (1/2)");
		System.out.println("     1.Luchar");
		System.out.println("     2.Huir");
		hacer = teclado.nextInt();
		if(hacer==1) {
			menuAttack();
		}
		
	}
	
	public void menuAttack() {
		System.out.println("Atacar:");
		System.out.println("1.Placaje");
		System.out.println("2.Ascuas");
		System.out.println("3.Ataque rapido");
		System.out.println("4.Malicioso");
	}
}


