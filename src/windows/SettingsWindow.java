package windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import structure.FileManager;
import structure.Settings;
import components.CustomButton;
import components.KeyBinding;
import components.MessageBox;
import frame.Frame;

/*
 * Essa classe é o painel da janela Settings
 */

public class SettingsWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attributes
	private ArrayList<JRadioButton> radioBtnList = new ArrayList<JRadioButton>(); 

	private JComboBox<String> primaryComboBox = null;
	private JComboBox<String> secondaryComboBox = null;
	private KeyBinding binding = null;

	//Methods

	public SettingsWindow() {

		setLayout(new BorderLayout(20,20));

		JPanel contentPanel = new JPanel(new GridLayout(0,1,20,20));
		contentPanel.add(generateDifficultyPanel(FileManager.file.readFileLine(1)));
		contentPanel.add(generateColorsPanel(FileManager.file.readFileLine(2),FileManager.file.readFileLine(3)));
		contentPanel.add(generateKeyBindingPanel(FileManager.file.readFileLine(4), getSelectedDifficulty()));

		add(contentPanel, "Center");
		add(generateBottomBar(), "South");

	}

	private JPanel generateDifficultyPanel(String selectedDiff) {
		
		JPanel mainPanel = new JPanel(new BorderLayout(20,20));

		//Criando o label Dificuldade
		JLabel difficultyLbl = new JLabel("Dificuldade:", JLabel.LEFT);
		difficultyLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		//Criando a box com as checkBox
		JPanel difficultyBox = new JPanel(new GridLayout(1,0,0,0));

		ButtonGroup btnGroup = new ButtonGroup();

		JRadioButton easy = new JRadioButton("Fácil");
		easy.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		easy.addActionListener(new RadioButtonActionListener());
		
		JRadioButton medium = new JRadioButton("Médio");
		medium.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		medium.addActionListener(new RadioButtonActionListener());
		
		JRadioButton hard = new JRadioButton("Difícil");
		hard.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		hard.addActionListener(new RadioButtonActionListener());
		
		switch(selectedDiff) {
		case "Fácil" :
			easy.setSelected(true);
			break;
		case "Médio" :
			medium.setSelected(true);
			break;
		case "Difícil":
			hard.setSelected(true);
			break;
		default:
			easy.setSelected(true);
			System.out.println("ERROR READ DIFFICULTY!!");
		}

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

	private JPanel generateColorsPanel(String primaryColor, String secondaryColor) {

		JPanel mainPanel = new JPanel(new GridLayout(0,1,20,20));

		//Criando os labels
		JLabel primaryLbl = new JLabel("Cor Primária:", JLabel.LEFT);
		primaryLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));
		JLabel secondaryLbl = new JLabel("Cor Secundária:", JLabel.LEFT);
		secondaryLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		
		//Criando as ComboBox
		String [] primaryColors = {"Preto", "Branco", "Azul", "Verde", "Vermelho", "Amarelo"};
		primaryComboBox = new JComboBox<String>(primaryColors);
		primaryComboBox.setSelectedItem(primaryColor);
		primaryComboBox.addActionListener(new ComboBoxActionListener());
		primaryComboBox.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		String [] secondaryColors = {"Branco", "Preto", "Azul", "Verde", "Vermelho", "Amarelo"};
		secondaryComboBox = new JComboBox<String>(secondaryColors);
		secondaryComboBox.setSelectedItem(secondaryColor);
		secondaryComboBox.addActionListener(new ComboBoxActionListener());
		secondaryComboBox.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		
		mainPanel.add(primaryLbl);
		mainPanel.add(primaryComboBox);
		mainPanel.add(secondaryLbl);
		mainPanel.add(secondaryComboBox);

		return mainPanel;

	}

	private JPanel generateKeyBindingPanel(String keys, String difficulty) {

		JPanel mainPanel = new JPanel(new BorderLayout(20,20));

		//Criando o label
		JLabel keysLbl = new JLabel("Atalhos do teclado:", JLabel.LEFT);
		keysLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/30));

		JPanel keysPanel = new JPanel(new GridLayout(1,0,0,20));

		binding = new components.KeyBinding(keysPanel, keys, Settings.translateDifficulty(difficulty));

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

	private class RadioButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JRadioButton btn = (JRadioButton)e.getSource();
			
			binding.getKeysPanel().removeAll();
			
			binding = new components.KeyBinding(binding.getKeysPanel(), FileManager.file.readFileLine(4), Settings.translateDifficulty(btn.getText()));
			binding.getKeysPanel().revalidate();
		}		
	}

	private class ComboBoxActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if(primaryComboBox.getSelectedItem().equals(secondaryComboBox.getSelectedItem())){
				if(primaryComboBox.getSelectedItem().equals("Preto")) {
					secondaryComboBox.setSelectedIndex(0);
					primaryComboBox.setSelectedIndex(0);					
				} else {
					primaryComboBox.setSelectedIndex(0);
					secondaryComboBox.setSelectedIndex(0);
				}

				String [] btns = {".",".",".",".","Ok"};
				MessageBox.messageBox.alertMessage("Cores primária e secundária iguais!", btns);
			}			
		}
	}

	private class BottomBarActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomButton btn = (CustomButton)e.getSource();

			if(btn.getText() == "Play") {

				Settings settings = new Settings(getSelectedDifficulty(), 
						(String)primaryComboBox.getSelectedItem(), 
						(String)secondaryComboBox.getSelectedItem(),
						binding.getKeys());

				FileManager.file.writeFile(settings);
				
				if(GameWindow.gameWindow != null) {
					
				}
				
				GameWindow.gameWindow = new GameWindow(settings);
				Frame.frame.changeContentPanel(GameWindow.gameWindow);

			} else if(btn.getText() == "Back")
				Frame.frame.changeContentPanel(new MenuWindow());
			else
				System.out.println("ERRO SETTINGS_BTN_ACTIONLISTENER!!");

		}		
	}
}
