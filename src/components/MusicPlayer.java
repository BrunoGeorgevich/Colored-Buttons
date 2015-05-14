package components;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


/*
 * Essa classe é responsável pelos sons do programa
 * 
 */

public class MusicPlayer {

	//Responsavel pelo fundo musical
	static public MusicPlayer backgroudMusic = new MusicPlayer();
	//Responsavel pelos efeitos musicais
	static public MusicPlayer effectsMusic = new MusicPlayer();

	//Responsavel por dar play e pause na musica
	private AudioClip clip = null;

	//Método que executa todos os arquivos de audio do programa
	public void play(String sound, boolean loopToggle) {

		URL musicURL = MusicPlayer.class.getClassLoader().getResource("sounds/" + sound);
		
		if (musicURL != null && sound != null) clip = Applet.newAudioClip(musicURL); 

		//Selecionará se aquele áudio entrará em loop ou não
		if(loopToggle)
			clip.loop();
		else
			clip.play();
	}

	public void stop() {
		if(clip != null)
			clip.stop();
	}
}
