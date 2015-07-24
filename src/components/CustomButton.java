package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

		addMouseListener(new CustomButtonMouseListener());
		addActionListener(act);
		
		setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/fontBtnSize));
	}

	private class CustomButtonMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			setBackground(Color.BLUE);
			setForeground(Color.WHITE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);			
		}
		
	}
	
}
