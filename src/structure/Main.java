package structure;

import windows.GameWindow;
import windows.MenuWindow;
import frame.Frame;

public class Main {

	public static void main(String[] args) {
		
		Frame.frame.changeContentPanel(new MenuWindow());
		
	}
}
