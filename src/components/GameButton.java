package components;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/*
 * Essa classe são os botões utilizados no 
 * painel central da classe GameWindow.
 */

public class GameButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int isRight = -1;
	
	public GameButton(int num, ActionListener act) {

		isRight = num;
	
		addActionListener(act);
	
	}
}
