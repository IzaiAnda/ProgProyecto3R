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

import baseJuego.Combate;
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

	MonsterPlant mon4 = new MonsterPlant("Plantita", 100, 100, 100, 100);
	MonsterFire mon5 = new MonsterFire("Fuegillo", 10, 10, 10, 10);
	MonsterWater mon6 = new MonsterWater("Gotita", 50, 50, 50, 50);

	ArrayList<Monster> monstersIA = new ArrayList<Monster>();
	HashMap<Monster, Move[]> movesIA = new HashMap<Monster, Move[]>();

	ArrayList<Monster> monstersP = new ArrayList<Monster>();
	HashMap<Monster, Move[]> moves = new HashMap<Monster, Move[]>();

	Move mp1= new Move("Hoja Afilada", 10);
	Move mp2= new Move("Llueve Hojas", 0);
	Move mp3= new Move("Canto Amor", 0);
	Move mp4= new Move("Tamborrilero", 50);
	Move[] mp = {mp1,mp2,mp3,mp4};

	Move mf1= new Move("Lanza llamas", 0);
	Move mf2= new Move("Bola de fuego", 10);
	Move mf3= new Move("Dia soleado", 20);
	Move mf4= new Move("Mechero", 30);
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
	private JButton fight = new JButton();
	private JButton run = new JButton();
	private JLabel labelBackGround = new JLabel();
	private JScrollPane scroll;
	private boolean stop;
	private String monsterActual;
	int y;

	public CombatWindow(int altura, int anchura) {

		monstersIA.add(mon6);
		monstersIA.add(mon5);
		monstersIA.add(mon4);

		moves.put(mon, mp);
		moves.put(mon2, mf);
		moves.put(mon3, mw);

		movesIA.put(mon4, mp);
		movesIA.put(mon5, mf);
		movesIA.put(mon6, mw);

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
	 * sacarPokemon
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

		sacarPokemon();

	}

	public void sacarPokemon() {
		historial.append("---------------------\n");
		historial.append("Que mounstruo quiere sacar?\n");
		botonesAtaques.setVisible(false);		
		botonesMonster.removeAll();
		
		for (Integer i = 0; i < monstersP.size(); i++) {
			historial.append(i + ": " + monstersP.get(i).getName() + "\n");
			JButton option1 = new JButton();
			option1.setBounds(420, 320, 100, 20);
			option1.setText(i.toString());
			botonesMonster.add(option1);
			option1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					historial.append("1\n");
					monsterActual = option1.getText();
					botonesMonster.setVisible(false);
					post(Integer.parseInt(monsterActual));

				}
			});
		}

		botonesMonster.setVisible(true);
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
		
		int monsterActualInt = Integer.parseInt(monsterActual);
		
		botonesAtaques.setVisible(false);

		historial.append("Que ataque quiere usar?\n");

		Move[] movesM = moves.get(monstersP.get(monsterActualInt));
		Move[] movesMIA = movesIA.get(monstersIA.get(0));

		botonesAtaques.removeAll();

		for (int i = 0; i < 4; i++) {
			JButton a =  new JButton(movesM[i].getName());
			System.out.println(a.getText());
			a.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for (int j = 0; j < movesM.length; j++) {
						if(movesM[j].getName().equals(a.getText())) {
							y=j;
						}				
					};
					
					Combate.combat(monstersP.get(monsterActualInt), monstersIA.get(0), movesM[new Integer(y)], movesMIA[0]);

					if(monstersP.get(monsterActualInt).getLifePoints()<=0) {
						historial.append("Te han devilitado a tu mounstruo!!\n");
						monstersP.remove(monsterActualInt);
						if (monstersP.isEmpty()) {
							historial.append("\n---------------------\n");
							historial.append("Has Perdido!!!\\n");
							finish();
						}else {
							sacarPokemon();
						}
					}else if(monstersIA.get(0).getLifePoints()<=0) {
						historial.append("Has devilitado al mounstruo enemigo!!\n");
						monstersIA.remove(0);
						if (monstersIA.isEmpty()) {
							historial.append("\n---------------------\n");
							historial.append("Has ganado!!!\\n");
							finish();
						}else {
							historial.append("tu rival a sacado a " + monstersIA.get(0)+"\n");
							historial.append( monstersP.get(monsterActualInt).getName() + " se ha quedado con " + monstersP.get(monsterActualInt).getLifePoints() + " puntos de vida.\n");
							ataques();
						}
					}else {
						historial.append( monstersP.get(monsterActualInt).getName() + " se ha quedado con " + monstersP.get(monsterActualInt).getLifePoints() + " puntos de vida.\n");
						historial.append( monstersIA.get(0).getName() + " se ha quedado con " + monstersIA.get(0).getLifePoints() + " puntos de vida.\n");
						ataques();
					}

				}
			});
			botonesAtaques.add(a);
		}

		botonesAtaques.setVisible(true);

	}
	
	public void finish() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//Actualizar BD
		LevelsWindow levelsWindow = new LevelsWindow(750, 422);
		levelsWindow.setVisible(true);
		CombatWindow.this.dispose();
	}

}
