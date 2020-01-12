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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import baseJuego.BD;
import baseJuego.Combate;
import level.LevelGame;
import level.Player;
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
	private Player player;
	private LevelGame levelGame;
	private LinkedList<Monster> monsters;
	private LinkedList<Monster> monstersEnemy;
	int y;
	
	public LinkedList<Monster> MonsterHashMapToList(HashMap<Monster, LinkedList<Move>> hp) {
		
		LinkedList<Monster> r = new LinkedList<Monster>();
		
		Iterator it = hp.entrySet().iterator();
		
		do {
			Map.Entry pair = (Map.Entry)it.next();	
			r.add((Monster) pair.getKey());
		} while (it.hasNext());
		
		return r;
	}
	
	public CombatWindow(int altura, int anchura, String slg, Player p) { 
		
		player = p;
		levelGame = BD.selectLevel(slg);
		
		monsters = MonsterHashMapToList(levelGame.getMonsters());
		monstersEnemy = MonsterHashMapToList(levelGame.getMonstersEnemy());
		
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
		// historial.append("Joven Chano: ï¿½Te desafio!\n");
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

		stop = true;
		historial.append("Estas luchando contra "+ levelGame.getEnemy().getName() +"\n");
		historial.append( levelGame.getEnemy().getName() + ": " + levelGame.getEnemy().getText() + "\n");

		historial.append(levelGame.getEnemy().getName() + " saca a " + monstersEnemy.get(0).getName() + "\n\n");
		
		sacarPokemon();

	}

	public void sacarPokemon() {
		historial.append("---------------------\n");
		historial.append("Que mounstruo quiere sacar?\n");
		botonesAtaques.setVisible(false);		
		botonesMonster.removeAll();
		
		for (Integer i = 0; i < monsters.size(); i++) {
			historial.append(i + ": " + monsters.get(i).getName() + "\n");
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

		historial.append("Has sacado a: " + monsters.get(i).getName() + "\n");
		historial.append("      \n");
		historial.append("¿Que quieres hacer? \n");

		botonesJugar.setVisible(true);

	}

	public void ataques() {
		
		int monsterActualInt = Integer.parseInt(monsterActual);
		
		botonesAtaques.setVisible(false);

		historial.append("Que ataque quiere usar?\n\n");

		LinkedList<Move> movesM = levelGame.getMonsters().get(monsters.get(monsterActualInt));
		LinkedList<Move> movesMIA = levelGame.getMonstersEnemy().get(monstersEnemy.get(0));

		botonesAtaques.removeAll();

		for (int i = 0; i < 4; i++) {
			JButton a =  new JButton(movesM.get(i).getName());
			a.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for (int j = 0; j < movesM.size(); j++) {
						if(movesM.get(j).getName().equals(a.getText())) {
							y=j;
							historial.append("Has usado: " + movesM.get(j).getName() + "\n");
						}				
					};
					
					Combate.combat(monsters.get(monsterActualInt), monstersEnemy.get(0), movesM.get(y), movesMIA.get(0));
					
					if (monsters.get(monsterActualInt).getLifePoints()<=0&&monstersEnemy.get(0).getLifePoints()<=0) {
						historial.append("Te han debilitado a tu mounstruo a la vez que has devilitado al mounstruo enemigo !!\n\n");
						monsters.remove(monsterActualInt);
						monstersEnemy.remove(0);
						if (monsters.isEmpty()) {
							youLose();
						}else if (monstersEnemy.isEmpty()) {
							youWin();
						}else {
							historial.append(levelGame.getEnemy().getName()+" a sacado a " + monstersEnemy.get(0).getName()+"\n\n");
							sacarPokemon();
						}
					}else if(monsters.get(monsterActualInt).getLifePoints()<=0) {
						historial.append("Te han debilitado a tu mounstruo!!\n\n");
						monsters.remove(monsterActualInt);
						if (monsters.isEmpty()) {
							youLose();
						}else {
							sacarPokemon();
						}
					}else if(monstersEnemy.get(0).getLifePoints()<=0) {
						historial.append("Has debilitado al mounstruo enemigo!!\n\n");
						monstersEnemy.remove(0);
						if (monstersEnemy.isEmpty()) {
							youWin();
						}else {
							historial.append(levelGame.getEnemy().getName()+" a sacado a " + monstersEnemy.get(0)+"\n");
							historial.append( monsters.get(monsterActualInt).getName() + " se ha quedado con " + monsters.get(monsterActualInt).getLifePoints() + " puntos de vida.\n\n");
							ataques();
						}
					}else {
						historial.append( monsters.get(monsterActualInt).getName() + " se ha quedado con " + monsters.get(monsterActualInt).getLifePoints() + " puntos de vida.\n");
						historial.append( monstersEnemy.get(0).getName() + " de "+levelGame.getEnemy().getName()+"  se ha quedado con " + monstersEnemy.get(0).getLifePoints() + " puntos de vida.\n\n");
						ataques();
					}

				}
			});
			botonesAtaques.add(a);
		}

		botonesAtaques.setVisible(true);

	}
	
	public void youLose(){
		historial.append("\n---------------------\n");
		JOptionPane.showConfirmDialog(null, levelGame.getEnemy().getName() + " ha debilitado a todos tus pokemons.\nHas perdido!!!", "Confirmar salida", JOptionPane.DEFAULT_OPTION);
		finish();
	}
	public void youWin() {
		historial.append("\n---------------------\n");
		JOptionPane.showConfirmDialog(null, "Has debilitado a todos sus pokemons.\nHas ganado!!!", "Confirmar salida", JOptionPane.DEFAULT_OPTION);
		if (player.getLevel()<levelGame.getDifficulty()) {
			player.setLevel(levelGame.getDifficulty());
			BD.updateJugador(player);
		}
		finish();
	}
	
	public void finish() {
		
		LevelsWindow levelsWindow = new LevelsWindow(750, 422, player);
		levelsWindow.setVisible(true);
		CombatWindow.this.dispose();
	}

}
