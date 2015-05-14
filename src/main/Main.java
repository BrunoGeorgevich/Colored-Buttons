package main;

import components.MusicPlayer;

import windows.MenuWindow;

/*
 * Essa é a classe que contem o metodo main
 */

public class Main {

	static boolean a = true;

	public static void main(String[] args) {

		MusicPlayer.backgroudMusic.play("NovemberRain.wav", true);
		MainFrame.mainFrame = new MainFrame("Colored Buttons");
		MainFrame.mainFrame.setContentPanel(MenuWindow.menuWindow);
		
	}
}
