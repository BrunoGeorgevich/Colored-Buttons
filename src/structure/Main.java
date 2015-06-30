package structure;

import windows.MenuWindow;
import windows.SettingsWindow;
import frame.Frame;

public class Main {

	public static void main(String[] args) {
		
		Frame.frame.changeContentPanel(new MenuWindow());
		
//		Settings settings = new Settings("Médio", "Branco", "Preto", "zxc");
//		
//		Game game = new Game(settings);
//		
//		game.printLines();
//
//		System.out.println("###################");
//		game.rightAnswer();
//		game.printLines();
	}
}
