package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import windows.GameWindow;
import windows.OptionsWindow;

/*
 * Essa classe é responsavel pelos botões do jogo
 * ela é quem gerencia se o botão será de uma cor ou não
 * ela é uma das classe fundamentais do programa
 */

public class GameButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int position = -1;
	
	public GameButton(boolean selectedBtn, int newPostion) {

		this.setBackground(OptionsWindow.optionsWindow.secondaryColorOption);
		
		if(selectedBtn) {
			this.setBackground(OptionsWindow.optionsWindow.mainColorOption);
			position = newPostion;
		}	
		
		this.addActionListener(new GameButtonActionListener());
		
	}
	
	//Retorna a posição do botão correto na linha
	public int getPosition() {
		return position;
	}
	
	/*
	 * Essa classe é responsavel pelo que acontece se você clicar no botão
	 */
	private class GameButtonActionListener implements ActionListener {	

		@Override
		public void actionPerformed(ActionEvent e) {
			
			GameButton btn = (GameButton) e.getSource();
			
			if(GameWindow.gameWindow.firstLine.firstBtn.equals(btn)) {
				GameWindow.gameWindow.updateLines();
			}
			else {
				GameWindow.gameWindow.lostGame();
			} 
		}		
	}
}
