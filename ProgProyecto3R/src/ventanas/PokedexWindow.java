package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import monsters.*;

public class PokedexWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private JMenuBar barr;
	private JMenu menuOrder;
	private JMenuItem itemName;
	private JMenuItem itemType;
	private JMenuItem itemLifePoints;
	private JMenuItem itemAttack;
	private JMenuItem itemDefense;
	private JMenuItem itemSpeed;
	private static JTable tabla;
	private static DefaultTableModel modelo= new DefaultTableModel();;
	
	public static void addMonsters(List<? extends Monster> list) {
		
		Vector<String> titles = new Vector<>();
		titles.add("Name");
		titles.add("Type");
		titles.add("LifePoints");
		titles.add("Attack");
		titles.add("Defense");
		titles.add("Speed");
		
		modelo.addRow(titles);
		
		for (Monster monster : list) {
			Vector<String> monsters = new Vector<>();
			monsters.add(monster.getName());
			monsters.add(monster.getTypeString());
			monsters.add(String.valueOf(monster.getLifePoints()));
			monsters.add(String.valueOf(monster.getAttack()));
			monsters.add(String.valueOf(monster.getDefense()));
			monsters.add(String.valueOf(monster.getSpeed()));

			modelo.addRow(monsters);
		}
		
	}
	
	private static void removeAllRows() {
		if (modelo.getRowCount() > 0) {
		    for (int i = modelo.getRowCount() - 1; i > -1; i--) {
		    	modelo.removeRow(i);
		    }
		}
	}
	
	public PokedexWindow(int altura, int anchura) {
		
		barr = new JMenuBar();
		setJMenuBar(barr);
		
		menuOrder = new JMenu("OrderBy");
		barr.add(menuOrder);	
		
		menuOrder.addSeparator();
		
		modelo.addColumn("Name");
		modelo.addColumn("Type");
		modelo.addColumn("Life Points");
		modelo.addColumn("Attack");
		modelo.addColumn("Defense");
		modelo.addColumn("Speed");
		
		List<Monster> list = new ArrayList<>();
		MonsterPlant mon = new MonsterPlant("Plantita",100,100,100,100);
		MonsterFire mon2 = new MonsterFire("Fuegillo",10,10,10,10);
		MonsterWater mon3 = new MonsterWater("Gotita",50,50,50,50);
		list.add(mon);
		list.add(mon2);
		list.add(mon3);
		
		addMonsters(list);
		
		itemName = new JMenuItem("Name");
		menuOrder.add(itemName);
		itemName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,0);//0 = ordered by name
			}
		});
		itemType = new JMenuItem("Type");
		menuOrder.add(itemType);
		itemType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,1);//1 = ordered by type
			}
		});
		itemLifePoints = new JMenuItem("Type");
		menuOrder.add(itemLifePoints);
		itemLifePoints.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,2);//2 = ordered by LifePoints
			}
		});
		itemAttack = new JMenuItem("Attack");
		menuOrder.add(itemAttack);
		itemAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,3);//3 = ordered by Attack
			}
		});
		itemDefense = new JMenuItem("Defense");
		menuOrder.add(itemDefense);
		itemDefense.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,4);//4 = ordered by Defense
			}
		});
		itemSpeed = new JMenuItem("Speed");
		menuOrder.add(itemSpeed);
		itemSpeed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,5);//5 = ordered by Speed
			}
		});
		tabla = new JTable(modelo);
		add(tabla);
		setSize(altura, anchura);
		setTitle("MonsterWiki");
	}
	
	public static void compareAdd(List<? extends Monster> list, int i){
		
		if (i == 0) {
			Collections.sort(list, new CompararMName());
		}else if(i == 1) {
			Collections.sort(list, new CompararMType());
		}else if(i == 2) {
			Collections.sort(list, new CompararMLifePoints());
		}else if(i == 3) {
			Collections.sort(list, new CompararMAttack());
		}else if(i == 4) {
			Collections.sort(list, new CompararMDefense());
		}else if(i == 5) {
			Collections.sort(list, new CompararMSpeed());
		}
		
		removeAllRows();
		addMonsters(list);
		tabla.repaint();
	}
	public static void main(String[] args) {
		PokedexWindow pokedexWindow = new PokedexWindow(750, 422);
		pokedexWindow.setVisible(true);
	}
}
