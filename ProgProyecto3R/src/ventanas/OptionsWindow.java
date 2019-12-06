package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OptionsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private JLabel labelBackground = new JLabel();
	private JButton buttonSave = new JButton();
	private JLabel labelMusic = new JLabel();
	private JButton buttonBack = new JButton();
	private JCheckBox checkMusic = new JCheckBox();
	
	public static void playMusic(String filepath) {

		try {
			
			File musicpath = new File(filepath);
			
			if (musicpath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
				Clip clip = AudioSystem.getClip();
				clip.start();
			}else {
				System.out.println("cant find file");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	}

	public OptionsWindow(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		labelMusic.setText("Musica");
		labelMusic.setBounds(250, 150, 100, 20);
		contentpane.add(labelMusic);
		
		checkMusic.setBounds(300, 150, 15, 15);
		contentpane.add(checkMusic);

		buttonBack.setText("Atras");
		buttonBack.setBackground(new Color(255, 175, 175));
		buttonBack.setBounds(200, 250, 100, 20);
		contentpane.add(buttonBack);
		
		buttonSave.setText("Guardar");
		buttonSave.setBackground(new Color(255, 175, 175));
		buttonSave.setBounds(400, 250, 100, 20);
		contentpane.add(buttonSave);
		
		labelBackground.setIcon(new ImageIcon(StartGameWindow.class.getResource("/images/back2.jpg")));
		labelBackground.setBounds(0, 0, altura, anchura);
		contentpane.add(labelBackground);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		// Eventos

		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionWindow selectionWindow = new SelectionWindow(750, 422);
				selectionWindow.setVisible(true);
				OptionsWindow.this.dispose();

			}
		});
		
		
	}
	
	public static void main(String[] args) {
		Path currentDir = Paths.get("/music/M1.mp3");
		
		playMusic("/music/M1.mp3");
		
	}
}
