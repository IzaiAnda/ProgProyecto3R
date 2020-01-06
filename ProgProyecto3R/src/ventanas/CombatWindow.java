package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;
import moves.Move;

public class CombatWindow<V> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MonsterPlant mon = new MonsterPlant("Plantita", 100, 100, 100, 100);
	MonsterFire mon2 = new MonsterFire("Fuegillo", 10, 10, 10, 10);
	MonsterWater mon3 = new MonsterWater("Gotita", 50, 50, 50, 50);
	
	ArrayList<Monster> monstersIA = new ArrayList<Monster>();
	HashMap<Monster, Move[]> movesIA = new HashMap<Monster, Move[]>();
	
	ArrayList<Monster> monstersP = new ArrayList<Monster>();
	HashMap<Monster, Move[]> moves = new HashMap<Monster, Move[]>();
	
	Move mp1= new Move("Hoja Afilada", 50);
	Move mp2= new Move("Llueve Hojas", 50);
	Move mp3= new Move("Canto Amor", 50);
	Move mp4= new Move("Tamborrilero", 50);
	Move[] mp = {mp1,mp2,mp3,mp4};
	
	Move mf1= new Move("Lanza llamas", 50);
	Move mf2= new Move("Bola de fuego", 50);
	Move mf3= new Move("Dia soleado", 50);
	Move mf4= new Move("Mechero", 50);
	Move[] mf = {mf1,mf2,mf3,mf4};
	
	Move mw1= new Move("Pistola Agua", 50);
	Move mw2= new Move("Burbuja", 50);
	Move mw3= new Move("LLovizna", 50);
	Move mw4= new Move("Cascada", 50);
	Move[] mw = {mw1,mw2,mw3,mw4};
	
	private GridLayout grid = new GridLayout(2, 2);
	private JPanel arriba = new JPanel();
	private JPanel abajo = new JPanel();
	private JPanel arribaIzquierda = new JPanel();
	private JPanel arribaDerecha = new JPanel();
	private JPanel abajoIzquierda = new JPanel();
	private JPanel abajoDerecha = new JPanel();
	private JPanel botonesMonster = new JPanel();
	private JPanel botonesJugar = new JPanel();
	private JPanel botonesAtaques = new JPanel();
	private JTextArea historial = new JTextArea();
	private JButton option1 = new JButton();
	private JButton option2 = new JButton();
	private JButton option3 = new JButton();
	private JButton option4 = new JButton();
	private JButton fight = new JButton();
	private JButton run = new JButton();
	private JLabel labelBackGround = new JLabel();
	private Scanner teclado;
	private JScrollPane scroll;
	private boolean stop;
	private int pokemonActual;

	public CombatWindow(int altura, int anchura) {
		
		monstersIA.add(mon);
		monstersIA.add(mon2);
		monstersIA.add(mon3);
		
		moves.put(mon, mp);
		moves.put(mon2, mf);
		moves.put(mon3, mw);
		
		movesIA.put(mon, mp);
		movesIA.put(mon2, mf);
		movesIA.put(mon3, mw);
		
		add(arriba,BorderLayout.CENTER);
		add(abajo,BorderLayout.SOUTH);
		
		arriba.add(arribaIzquierda);
		arriba.add(arribaDerecha);
		
		abajo.add(abajoIzquierda);
		abajo.add(abajoDerecha);
		
		abajoDerecha.add(botonesMonster);
		botonesMonster.setVisible(false);
		abajoIzquierda.add(botonesAtaques);
		botonesAtaques.setVisible(false);
		botonesAtaques.setLayout(new GridLayout(2,2));
		abajoIzquierda.add(botonesJugar);
		botonesJugar.setVisible(false);
		
		scroll = new JScrollPane(historial);
		scroll.setBounds(new Rectangle(420, 0, 300, 300));
		arribaDerecha.add(scroll);
		scroll.setPreferredSize(new Dimension(300, 200));

		historial.setLineWrap(true);
		historial.setWrapStyleWord(true);
		historial.setEditable(false);

		option1.setBounds(420, 320, 100, 20);
		option1.setText("1");
		botonesMonster.add(option1);

		option2.setBounds(540, 320, 100, 20);
		option2.setText("2");
		botonesMonster.add(option2);

		option3.setBounds(420, 350, 100, 20);
		option3.setText("3");
		botonesMonster.add(option3);

		option4.setBounds(540, 350, 100, 20);
		option4.setText("4");
		botonesMonster.add(option4);

		fight.setBounds(100, 320, 100, 20);
		fight.setText("Luchar");
		botonesJugar.add(fight);

		run.setBounds(220, 320, 100, 20);
		run.setText("Huir");
		botonesJugar.add(run);

		labelBackGround.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/escenario.png")));
		labelBackGround.setBounds(0, -100, altura, anchura);
		arribaIzquierda.add(labelBackGround);

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
				pokemonActual = 0;
				botonesMonster.setVisible(false);
				post(0);

			}
		});

		option2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("2\n");
				pokemonActual = 1;
				botonesMonster.setVisible(false);
				post(1);

			}
		});

		option3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("3\n");
				pokemonActual = 2;
				botonesMonster.setVisible(false);
				post(2);

			}
		});

		option4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historial.append("4\n");
				pokemonActual = 3;
				botonesMonster.setVisible(false);
				post(3);

			}
		});
		
		fight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botonesJugar.setVisible(false);
				botonesAtaques.setVisible(true);
				ataques();
			}
		});

	}
	/* Orden del combate
	 * startCombat
	 * selectMonster
	 * post
	 * ataques
	 * 
	 */
	public void startCombat() {
		monstersP.add(mon);
		monstersP.add(mon2);
		monstersP.add(mon3);

		stop = true;

		historial.append("Estás luchando contra Joven Chano!\n");
		historial.append("Joven Chano: Te desafio!\n");

		historial.append("Joven Chano saca a " + monstersIA.get(0).getName() + "\n");
		historial.append("---------------------\n");
		selectMonster();

	}
	
	public void post(int i){
		historial.append("---------------------\n");

		historial.append("Has ado a: " + monstersP.get(i).getName() + "\n");
		historial.append("      \n");
		historial.append("¿Que quieres hacer? (1/2)\n");
		historial.append("     Luchar\n");
		historial.append("     Huir\n");
		
		botonesJugar.setVisible(true);
		
	}
	
	public void ataques() {
		historial.append("Que ataque quiere sacar?\n");
		
		Move[] movesM = moves.get(monstersP.get(pokemonActual));
		
		for (int i = 0; i < 4; i++) {
			JButton a =  new JButton(movesM[i].getName());
			botonesAtaques.add(a);
		}
		
		botonesAtaques.setVisible(true);
		
	}

	public void selectMonster() {
		historial.append("Que pokemon quiere sacar? (1/2/3)\n");
		for (int i = 0; i < moves.size(); i++) {
			historial.append(i+1 + ": " + monstersP.get(i).getName() + "\n");
		}
		
		botonesMonster.setVisible(true);
		
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
