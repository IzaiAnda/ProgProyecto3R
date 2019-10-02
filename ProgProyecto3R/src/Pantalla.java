import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pantalla extends JFrame{
	
	public Pantalla(){
		
		Container cp = this.getContentPane();
		
		JPanel arriba = new JPanel();
		arriba.setBackground(Color.RED);
		arriba.setSize(750, 200);
		
		JPanel abajo = new JPanel();
		abajo.setBackground(Color.BLUE);
		
		cp.add(arriba, BorderLayout.NORTH);
		cp.add(abajo, BorderLayout.SOUTH);
		this.setSize(750, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ChalMonsters");
		this.setVisible(true);
	}

	
}
