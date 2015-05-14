package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/*
 * Essa classe foi feita no intuito de padronizar os botões do programa
 */

public class CommonButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonButton(String text, ActionListener action, double scale) {
		super(text);
		addActionListener(action);		

		setBackground(Color.BLACK);
		
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(20*scale)));
		setForeground(Color.WHITE);
	
	}	
}
