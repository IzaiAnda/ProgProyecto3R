package baseJuego;

import ventanas.Pokedex;
import ventanas.StartGameWindow;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import baseJuego.BD;

public class Main {

	public static void main(String[] args) {
		
		BD.startBD();
		
		StartGameWindow starWindow = new StartGameWindow(750, 422);

		starWindow.setVisible(true);
		
		


	}
	
	public void Sonido() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
		BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream("/sounds/prueba.mp3"));
		AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
		
		Clip sonido = AudioSystem.getClip();
		sonido.open(ais);
		
		sonido.start();
	}


}

