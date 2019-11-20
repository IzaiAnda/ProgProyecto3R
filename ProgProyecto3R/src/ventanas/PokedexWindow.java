package ventanas;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PokedexWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private JMenuBar barr;
	private JMenu menu1;
	private JMenuItem item1;
	
	public PokedexWindow(int altura, int anchura) {
		
		barr = new JMenuBar();
		setJMenuBar(barr);
		
		menu1 = new JMenu("Menu");
		barr.add(menu1);
		
		item1 = new JMenuItem("Item");
		menu1.add(item1);
		
		menu1.addSeparator();
		
		setSize(altura, anchura);
		setTitle("MonsterWiki");
	}
	public static void main(String[] args) {
		PokedexWindow pokedexWindow = new PokedexWindow(750, 422);
		pokedexWindow.setVisible(true);
	}
}
