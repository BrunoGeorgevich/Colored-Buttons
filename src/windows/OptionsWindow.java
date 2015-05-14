package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.MainFrame;
import bars.Bar;
import components.Difficulty;
import components.GameBindingKeys;
import components.MessageBox;
import components.MusicPlayer;
import components.OptionsFile;

public class OptionsWindow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static public OptionsWindow optionsWindow = new OptionsWindow();
	public Color mainColorOption = translateColor(OptionsFile.file.readFileLine(1));
	public Color secondaryColorOption = translateColor(OptionsFile.file.readFileLine(2));
			
			
	private String [] colorWarningMessageBtn = {".",".",".",".","Ok"};
	private String [] bottomBarBtns = {"Voltar", "." , "Padrão", ".", "Salvar"};
	private String [] dificuldades = {"Fácil", "Médio", "Difícil"};
	private String [] coresMain = { "Preto", "Branco", "Azul", "Vermelho", "Amarelo", "Cinza", "Verde", "Rosa", "Laranja"};
	private String [] coresSecond = {"Branco", "Preto","Azul", "Vermelho", "Amarelo", "Cinza", "Verde", "Rosa", "Laranja"};

	String [] defaultSettings = {"Preto","Branco","Médio","zxcvb"};

	private JComboBox<String> difficultyComboBox = getComboBox(dificuldades);
	private JComboBox<String> mainColorComboBox = getComboBox(coresMain);
	private JComboBox<String> secondColorComboBox = getComboBox(coresSecond);

	private JPanel bottomBar = Bar.getBar(bottomBarBtns, new OptionsWindowButtonActionListener());	

	private OptionsWindow() {

		this.setLayout(new BorderLayout(10,30));

		GameBindingKeys.bindingKeys.setColumns(5);

		JPanel contentPanel = new JPanel(new GridLayout(0,1,10,10));

		mainColorComboBox.setSelectedItem(OptionsFile.file.readFileLine(1));
		secondColorComboBox.setSelectedItem(OptionsFile.file.readFileLine(2));
		difficultyComboBox.setSelectedItem(OptionsFile.file.readFileLine(3));

		contentPanel.add(new OptionCell<JComboBox<String>>("Selecione a dificuldade:", difficultyComboBox));
		contentPanel.add(new OptionCell<JComboBox<String>>("Selecione a cor Principal:", mainColorComboBox));
		contentPanel.add(new OptionCell<JComboBox<String>>("Selecione a cor Secundária:", secondColorComboBox));
		contentPanel.add(new OptionCell<JPanel>("Selecione os atalhos do teclado:", GameBindingKeys.bindingKeys));

		this.add(contentPanel, "Center");
		this.add(bottomBar, "South");

		Bar.saveBtn.setEnabled(false);
	}
	
	private static Color translateColor(String color) {
		switch (color) {
		case "Preto":
			return Color.BLACK;			
		case "Branco":
			return Color.WHITE;			
		case "Azul":
			return Color.BLUE;			
		case "Vermelho":
			return Color.RED;			
		case "Amarelo":
			return Color.YELLOW;			
		case "Cinza":
			return Color.GRAY;			
		case "Verde":
			return Color.GREEN;
		case "Rosa":
			return Color.PINK;			
		case "Laranja":
			return Color.ORANGE;			
		default:
			return Color.CYAN;			
		}
	}

	private JComboBox<String> getComboBox(String [] itens) {

		JComboBox<String> comboBox = new JComboBox<String>(itens);
		comboBox.addActionListener(new OptionsWindowComboBoxActionListener());
		return comboBox;

	}

	private class OptionCell<E extends Component>  extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OptionCell(String text, E component) {

			this.setLayout(new GridLayout(0,1));

			JLabel textLabel = new JLabel(text, JLabel.LEFT);
			textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

			component.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

			this.add(textLabel);
			this.add(component);

		}		
	}

	private class OptionsWindowChangeListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			Bar.saveBtn.setEnabled(true);
		}

	}

	private class OptionsWindowButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();

			if(btn.getText() == "Voltar") {
				MainFrame.mainFrame.setContentPanel(MenuWindow.menuWindow);
			}
			else if(btn.getText() == "Padrão") {

				mainColorComboBox.setSelectedItem(defaultSettings[0]);
				secondColorComboBox.setSelectedItem(defaultSettings[1]);
				difficultyComboBox.setSelectedItem(defaultSettings[2]);
				GameBindingKeys.bindingKeys.setKeys(defaultSettings[3]);

			}
			else if(btn.getText() == "Salvar") {

				String content = "";
				content += (String)mainColorComboBox.getSelectedItem() + "\n";
				content += (String)secondColorComboBox.getSelectedItem() + "\n";
				content += (String)difficultyComboBox.getSelectedItem() + "\n";
				content += (String)GameBindingKeys.bindingKeys.getKeys() + "\n";

				Difficulty.difficulty.setDifficulty((String)difficultyComboBox.getSelectedItem());
				mainColorOption = translateColor((String)mainColorComboBox.getSelectedItem());
				secondaryColorOption = translateColor((String)secondColorComboBox.getSelectedItem());
				
				OptionsFile.file.writeFile(content);
				Bar.saveBtn.setEnabled(false);
			}
			else{
				System.out.println("Erro nas ações do botão OptionsWindow");
			}			
		}		
	}

	private class OptionsWindowComboBoxActionListener implements ActionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {

			JComboBox<String> comboBox = (JComboBox<String>) e.getSource();

			MusicPlayer.effectsMusic.play("CorrectSound.wav", false);
			
			if(comboBox == difficultyComboBox) {

				difficultyComboBox.setSelectedItem(comboBox.getSelectedItem());

				Difficulty.difficulty.setDifficulty((String)comboBox.getSelectedItem());

				GameBindingKeys.bindingKeys.setColumns(Difficulty.difficulty.getColumns());

				Bar.saveBtn.setEnabled(true);			

			}

			else if(comboBox == mainColorComboBox) {	

				if(comboBox.getSelectedItem() == secondColorComboBox.getSelectedItem()) {

					MessageBox.messageBox.alertMessage("Cor já utilizada!", colorWarningMessageBtn);
					mainColorComboBox.setSelectedItem("Preto");
					secondColorComboBox.setSelectedItem("Branco");

				} 
				else {

					mainColorComboBox.setSelectedItem(comboBox.getSelectedItem());
					Bar.saveBtn.setEnabled(true);			

				}
			}

			else if(comboBox == secondColorComboBox) {

				if(comboBox.getSelectedItem() == mainColorComboBox.getSelectedItem()) {

					MessageBox.messageBox.alertMessage("Cor já utilizada!", colorWarningMessageBtn);
					mainColorComboBox.setSelectedItem("Preto");
					secondColorComboBox.setSelectedItem("Branco");

				} 

				else {

					secondColorComboBox.setSelectedItem(comboBox.getSelectedItem());
					Bar.saveBtn.setEnabled(true);

				}

			}
			else{
				System.out.println("Erro nas ações do ComboBox OptionsWindow");
			}			
		}		
	}
}
