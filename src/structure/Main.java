package structure;

import windows.LoginWindow;
import frame.Frame;


public class Main {

	public static void main(String[] args) throws Exception {
		
		Frame.frame.changeContentPanel(new LoginWindow());
		
	}
}
