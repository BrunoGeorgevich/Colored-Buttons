package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.Frame;

public class KeyBinding {

	private final String defaultKeys = "zxcvbnm";
	
	public KeyBinding(JPanel keysPanel, int difficulty) {
			
		for(int i = 0; i < difficulty; i++) {
			keysPanel.add(new KeyBindingCell(i + 1, defaultKeys.charAt(i)));
		}
		
	}
	
	private class KeyBindingCell extends JPanel{
		
		public KeyBindingCell(int index, char content) {
			super(new GridLayout(2,1,0,0));
			

			JLabel indexLbl = new JLabel(index + "°", JLabel.CENTER);
			indexLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			indexLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/25));
			JLabel contentLbl = new JLabel(content + "", JLabel.CENTER);
			contentLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			contentLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/25));

			this.add(indexLbl);
			this.add(contentLbl);
		}		
	}	
}
