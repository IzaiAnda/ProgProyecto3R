import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pantalla extends JFrame{
	
	public Pantalla(){
		
		Container cp = this.getContentPane();
		
		JPanel arriba = new JPanel();
		arriba.setBackground(Color.RED);
		
		
		
		JButton jugar = new JButton("Jugar");
		jugar.setPreferredSize(new Dimension(200,100));
		
		arriba.add(jugar );
		cp.add(arriba);
		this.setSize(750, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ChalMonsters");
		this.setVisible(true);
	}
	


	
}
