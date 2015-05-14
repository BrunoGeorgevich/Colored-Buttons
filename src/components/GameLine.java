package components;

import java.awt.GridLayout;

import javax.swing.JPanel;

/*
 * Essa classe é responsável por administrar os GameButtons de forma ordenada e correta
 * Sem dúvidas ela é umas das classes principais do programa
 */

public class GameLine extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean selectedBtn = false;
	public GameButton firstBtn = null;
	
	public GameLine(){
		
		this.setLayout(new GridLayout(1,0,10,10));
		int selectedBtnIndex = (int)(Math.random()*Difficulty.difficulty.getColumns());
				
		for(int i = 0; i < Difficulty.difficulty.getColumns(); i++) {
			
			if ( i == selectedBtnIndex) {
				selectedBtn = true;
				firstBtn = addButton(selectedBtnIndex);
			}
			else {
				addButton(-1);
			}
		}
	}
	
	//Método responsavel pela adição de botões à linha
	private GameButton addButton(int selectedBtnIndex) {
		
		GameButton btn = new GameButton(selectedBtn, selectedBtnIndex);
//		System.out.println(GameWindow);
		
		this.add(btn);
		selectedBtn = false;
	
		return btn;
	}
}
