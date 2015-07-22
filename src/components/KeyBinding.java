package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.Frame;

/*
 * Essa classe cuida dos atalhos do teclado.
 */

public class KeyBinding {

	private final String defaultKeys = "zxcvbnm";
	private JPanel keysPanel = null;
	private String keys = "";
	
	public KeyBinding(JPanel panel, String newKeys, int difficulty) {
			
		keysPanel = panel;
		
		for(int i = 0; i < difficulty; i++) {
			
			char c = 'a';
			
			if(i < newKeys.length()){
				c = newKeys.charAt(i);
			} else {
				c = defaultKeys.charAt(i);
			}

			keysPanel.add(new KeyBindingCell(i + 1, c));
			keys += c;
		}
		
	}
	
	//Getters and Setters
	
	public String getKeys() {
		return keys;
	}
	
	//Inner Class
	
	public JPanel getKeysPanel() {
		return keysPanel;
	}

	private class KeyBindingCell extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private char currentChar;
		private JLabel contentLbl = null;

		public KeyBindingCell(int index, char content) {
			super(new GridLayout(2,1,0,0));
			
			currentChar = content;
			
			addMouseListener(new KeyBindingCellMouseListener());

			JLabel indexLbl = new JLabel(index + "°", JLabel.CENTER);
			indexLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			indexLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/25));
			
			contentLbl = new JLabel(currentChar + "", JLabel.CENTER);
			contentLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			contentLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/25));

			this.add(indexLbl);
			this.add(contentLbl);
		}		
		
		public void update(char c) {
			currentChar = c;
			contentLbl.setText(c + "");
			revalidate();
		}
		
		public char getCurrentChar() {
			return currentChar;
		}
	}
	
	private class KeyBindingCellMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			KeyBindingCell cell = (KeyBindingCell)e.getSource();
			
			char c = MessageBox.messageBox.getCharMessage("Digite o caractér que deseja associar");
			
			if(keys.contains(c + "")) {
				String [] btns = {".",".",".",".","Ok"};
				MessageBox.messageBox.alertMessage("O caractér já está em uso!", btns);
			} else {
				keys = keys.replace(cell.getCurrentChar(), c);
				System.out.println(keys);
				cell.update(c);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}
