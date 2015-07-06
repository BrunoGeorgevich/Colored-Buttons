package components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import structure.Line;

/*
 * Essa classe cria as linhas do Painel central
 * da classe GameWindow
 */

public class LineUi extends JPanel {

	private GameButton rightButton = null;
	
	public LineUi(Line line, Color primaryColor, Color secondaryColor, ActionListener act) {
	
		setLayout(new GridLayout(1,0,5,5));
		
		for (int num : line.getNumbers()) {
			
			GameButton btn = new GameButton(num, act);
			
			if(num == 1) {
				rightButton = btn;
				btn.setBackground(primaryColor);
			} else if(num == 0) {
				btn.setBackground(secondaryColor);
			} else {
				System.out.println("ERRO LINEUI_BTN!!");
			}
			
			add(btn);
				
		}		
	}

	public GameButton getRightButton() {
		return rightButton;
	}
}
