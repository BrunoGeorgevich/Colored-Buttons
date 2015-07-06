package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frame.Frame;

/*
 * Essa classe é usada em todos os botões do jogo.
 * Exceto nos botões do painel Central da GameWindow,
 * esses são GameButtons.
 */

public class CustomButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomButton(String text, int fontBtnSize, ActionListener act) {
		super(text);

		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		
		addActionListener(act);
		
		setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/fontBtnSize));
	}
	
}
