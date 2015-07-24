package windows;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import components.CustomButton;
import frame.Frame;

/*
 * Essa classe é o painel da janela Menu
 */

public class MenuWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuWindow() {
	
		setLayout(new GridLayout(4,3,20,20));

		addBlankSpaces(4);
		add(new CustomButton("Jogar", 10,new MenuBtnActionListener()));
		addBlankSpaces(2);
		add(new CustomButton("Sair", 10,new MenuBtnActionListener()));
		addBlankSpaces(4);
		
	}
	
	private void addBlankSpaces(int num) {
		for(int i = 0; i < num; i++) {
			this.add(new JPanel());
		}
	}
	
	private class MenuBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			CustomButton btn = (CustomButton)e.getSource();
			
			if(btn.getText() == "Jogar")
				Frame.frame.changeContentPanel(new SettingsWindow());
			else if(btn.getText() == "Sair")
				Frame.frame.changeContentPanel(new LoginWindow());
			else
				System.out.println("ERRO MENU_BTN_ACTIONLISTENER!!");
			
		}		
	}
}
