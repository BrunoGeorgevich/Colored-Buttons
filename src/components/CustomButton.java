package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frame.Frame;

public class CustomButton extends JButton {

	public CustomButton(String text, int fontBtnSize, ActionListener act) {
		super(text);

		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		
		addActionListener(act);
		
		setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/fontBtnSize));
	}
	
}
