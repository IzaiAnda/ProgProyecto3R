package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baseJuego.BD;
import monsters.CompareMAttack;
import monsters.CompareMDefense;
import monsters.CompareMLifePoints;
import monsters.CompareMName;
import monsters.CompareMSpeed;
import monsters.CompareMType;
import monsters.Monster;
import moves.Move;
import moves.CompareMoveName;
import moves.CompareMoveDamage;

public class MovementsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar barr;
	private JMenu menuOrder;
	private JMenuItem moveName;
	private JMenuItem moveDamage;
	private static JTable tabla;
	JScrollPane scroll = new JScrollPane();
	private static DefaultTableModel modelo= new DefaultTableModel();

	public static void createColums() {
		modelo.addColumn("Name");
		modelo.addColumn("Damage");
	}
	

	
	public static void addMoves(List<? extends Move> list) {
		
			removeAllRows();
		
			for (Move move : list) {
				Vector<String> moves = new Vector<>();
				moves.add(move.getName());
				moves.add(Integer.toString(move.getDamage()));
				
				modelo.addRow(moves);
			}
			
		}


	
	private static void removeAllRows() {
		if (modelo.getRowCount() > 0) {
		    for (int i = modelo.getRowCount() - 1; i > -1; i--) {
		    	modelo.removeRow(i);
		    }
		}
	}


	public MovementsWindow(int altura, int anchura) {
		
		barr = new JMenuBar();
		setJMenuBar(barr);
		
		menuOrder = new JMenu("OrderBy");
		barr.add(menuOrder);	
		
		menuOrder.addSeparator();
		
		if(!(modelo.getColumnCount() > 0)) {
			createColums();
		}
		
		
		LinkedList<Move> list = BD.selectAllMoves();
		
		addMoves(list);
		
		moveName = new JMenuItem("Name");
		menuOrder.add(moveName);
		moveName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,0);//0 = ordered by name
			}
		});
		moveDamage = new JMenuItem("Damage");
		menuOrder.add(moveDamage);
		moveDamage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compareAdd(list,1);//1 = ordered by type
			}
		});
		
		tabla = new JTable(modelo);
		tabla.setEnabled(false);
		scroll.setViewportView(tabla);
		scroll.setOpaque(false);
		scroll.setBackground(Color.BLUE);
		add(scroll);
		

		setSize(altura, anchura);
		setLocation(500, 250);
		setTitle("Moves");
	}
	
	public static void compareAdd(List<? extends Move> list, int i){
		
		if (i == 0) {
			Collections.sort(list, new CompareMoveName());
		}else if(i == 1) {
			Collections.sort(list, new CompareMoveDamage());
		}
		
		removeAllRows();
		addMoves(list);
		tabla.repaint();
	}
	
	public static void main(String[] args) {
		MovementsWindow movementsWindow = new MovementsWindow(750, 422);
		movementsWindow.setVisible(true);
		
	}
	
	

}
