package windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MainFrame;
import components.CommonButton;
import components.MusicPlayer;

/*
 * Essa classe é o menu principal do jogo
 * Ela é basicamente Interface gráfica
 */

public class MenuWindow extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static public MenuWindow menuWindow = new MenuWindow(new BorderLayout());
	
	private MenuWindow(BorderLayout layoutMgr) {
		
		super(layoutMgr);
		
		JPanel btnPanel = new JPanel(new GridLayout(5,3,10,10));
		
		String [] buttons = {"Jogar", "Opções", "Sair"};

		setEmptySpaces(1, btnPanel);
		setButton(buttons[0], btnPanel);
		setEmptySpaces(2, btnPanel);
		setButton(buttons[1], btnPanel);
		setEmptySpaces(2, btnPanel);
		setButton(buttons[2], btnPanel);
		setEmptySpaces(6, btnPanel);
		
		this.setTitleLabel("Colors Game", this);
		this.add(btnPanel, "Center");
		
	}
	
	//Acrescenta um label ao painel responsavel pelo titulo do jogo
	private void setTitleLabel(String title, JPanel panel) {
		
			JPanel titlePanel = new JPanel(new GridLayout(0, 1));
		
			JLabel titleLabel = new JLabel(title, JLabel.CENTER);
			titleLabel.setFont(new Font("Arial", Font.BOLD, 100));

			setEmptySpaces(1, titlePanel);
			titlePanel.add(titleLabel);
			setEmptySpaces(1, titlePanel);
			
			panel.add(titlePanel, "North");
		
	}
	
	//Acrescenta labels vazios para ajustar o layout
	private void setEmptySpaces(int times, JPanel panel) {
		for(int i = 0; i < times; i++) {
			panel.add(new JPanel());
		}
	}

	//Acrescenta botões ao layotu
	private void setButton(String text, JPanel panel) {	
			CommonButton btn = new CommonButton(text, new MenuButtonActionListener(), 2.5);
			panel.add(btn);
	}
		
	/*
	 * Classe que contem as funções dos botões do MenuWindow
	 */
	private class MenuButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			MusicPlayer.effectsMusic.play("CorrectSound.wav", false);
			
			if(btn.getText() == "Jogar") {
				MainFrame.mainFrame.setContentPanel(new GameWindow());
			}
			else if(btn.getText() == "Opções") {
				MainFrame.mainFrame.setContentPanel(OptionsWindow.optionsWindow);
			}
			else if(btn.getText() == "Sair") {
				System.exit(0);
			}
			else{
				System.out.println("Erro nas ações do botão MenuWindow");
			}
			
		}
	}
}
