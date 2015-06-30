package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import structure.Settings;
import components.CustomButton;
import components.KeyBinding;
import frame.Frame;

public class SettingsWindow extends JPanel {

	//Attributes
	private ArrayList<JRadioButton> radioBtnList = new ArrayList<JRadioButton>(); 

	//Methods

	public SettingsWindow() {

		setLayout(new BorderLayout(20,20));
		
		JPanel contentPanel = new JPanel(new GridLayout(0,1,20,20));
		contentPanel.add(generateDifficultyPanel());
		contentPanel.add(generateColorsPanel());
		contentPanel.add(generateKeyBindingPanel(getSelectedDifficulty()));

		add(contentPanel, "Center");
		add(generateBottomBar(), "South");
		
	}

	private JPanel generateDifficultyPanel() {

		JPanel mainPanel = new JPanel(new BorderLayout(20,20));

		//Criando o label Dificuldade
		JLabel difficultyLbl = new JLabel("Dificuldade:", JLabel.LEFT);
		difficultyLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		//Criando a box com as checkBox
		JPanel difficultyBox = new JPanel(new GridLayout(1,0,0,0));

		ButtonGroup btnGroup = new ButtonGroup();

		JRadioButton easy = new JRadioButton("Fácil");
		easy.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		JRadioButton medium = new JRadioButton("Médio");
		medium.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		JRadioButton hard = new JRadioButton("Difícil");
		hard.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		easy.setSelected(true);

		radioBtnList.add(easy);		
		radioBtnList.add(medium);		
		radioBtnList.add(hard);

		btnGroup.add(easy);		
		btnGroup.add(medium);		
		btnGroup.add(hard);

		difficultyBox.add(easy);		
		difficultyBox.add(medium);		
		difficultyBox.add(hard);

		mainPanel.add(difficultyLbl, "North");
		mainPanel.add(difficultyBox, "Center");

		return mainPanel;

	}

	private JPanel generateColorsPanel() {

		JPanel mainPanel = new JPanel(new GridLayout(0,1,20,20));

		//Criando os labels
		JLabel primaryLbl = new JLabel("Cor Primária:", JLabel.LEFT);
		primaryLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		JLabel secondaryLbl = new JLabel("Cor Secundária:", JLabel.LEFT);
		secondaryLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		//Criando as ComboBox
		String [] primaryColors = {"Preto", "Branco", "Azul"};
		JComboBox<String> primaryComboBox = new JComboBox<String>(primaryColors);
		primaryComboBox.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		String [] secondaryColors = {"Branco", "Preto", "Azul"};
		JComboBox<String> secondaryComboBox = new JComboBox<String>(secondaryColors);
		secondaryComboBox.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		mainPanel.add(primaryLbl);
		mainPanel.add(primaryComboBox);
		mainPanel.add(secondaryLbl);
		mainPanel.add(secondaryComboBox);

		return mainPanel;

	}

	private JPanel generateKeyBindingPanel(String difficulty) {

		JPanel mainPanel = new JPanel(new BorderLayout(20,20));

		//Criando o label
		JLabel keysLbl = new JLabel("Atalhos do teclado:", JLabel.LEFT);
		keysLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		JPanel keysPanel = new JPanel(new GridLayout(1,0,0,20));
		
		KeyBinding binding = new components.KeyBinding(keysPanel, 
				Settings.translateDifficulty(difficulty));

		mainPanel.add(keysLbl, "North");
		mainPanel.add(keysPanel, "Center");

		return mainPanel;

	}
	
	private JPanel generateBottomBar() {

		JPanel bottomBar = new JPanel(new GridLayout(1,0,20,20));

		bottomBar.add(new CustomButton("Back", 30, new BottomBarActionListener()));
		
		bottomBar.add(new JPanel());
		bottomBar.add(new JPanel());
		bottomBar.add(new JPanel());
		
		bottomBar.add(new CustomButton("Play", 30, new BottomBarActionListener()));
		

		return bottomBar;

	}

	private String getSelectedDifficulty() {
		for (JRadioButton btn : radioBtnList) {
			if(btn.isSelected())
				return btn.getText();
		}

		return null;
	}
	
	private class BottomBarActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomButton btn = (CustomButton)e.getSource();
			
			if(btn.getText() == "Play")
				Frame.frame.changeContentPanel(new GameWindow());
			else if(btn.getText() == "Back")
				Frame.frame.changeContentPanel(new MenuWindow());
			else
				System.out.println("ERRO MENU_BTN_ACTIONLISTENER!!");
			
		}		
	}
}
