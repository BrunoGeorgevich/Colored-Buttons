package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bars.Bar;

/*
 * Essa classe é responsavel pelos atalhos
 * do programa no teclado, principalmente
 * na tela GameWindow.
 * É ela que gerencia os atalhos.
 */

public class GameBindingKeys extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Referencias a propria Classe a aos atalhos salvos
	public static GameBindingKeys bindingKeys = new GameBindingKeys();
	private String keys = OptionsFile.file.readFileLine(4);
	
	//String com os atalhos padrão
	public static String defaultKeys = "zxcvbnm";

	private GameBindingKeys() {

		this.setLayout(new GridLayout(1,0));

	}

	//Essa classe é responsavel por montar a tabela de 
	//configuração de atalhos, encontrada na tela OptionsWindow
	public void setColumns(int columns) {

		bindingKeys.removeAll();
		String aux = "";
		
		for (int i = 0; i < columns; i++) {
			
			char c = 'c';
			
			if(keys.length() > i)
				c = keys.charAt(i);
			else
				c = defaultKeys.charAt(i);
			
			GameBindingCell cell = new GameBindingCell(" " + (i + 1) + "°", c);
			aux += c;
			bindingKeys.add(cell);
		}
		
		keys = aux;

		bindingKeys.revalidate();

	}

	//Retorna os atalhos salvos
	public String getKeys() {
		return keys;
	}
	
	//Substitui os atalhos salvos por novos
	public void setKeys(String newKeys) {
		
		this.keys = newKeys;
		this.setColumns(this.getKeys().length());
		this.revalidate();
		
	}

	/*
	 * Essa classe é a célula da tabela de seleção de atalhos
	 */
	private static class GameBindingCell extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Border border = BorderFactory.createLineBorder(Color.black);
		
		public GameBindingCell(String text, char c) {

			this.setLayout(new GridLayout(0,1));
			
			JLabel textLabel = new JLabel(text, JLabel.CENTER);
			textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
			textLabel.setBorder(border);

			JLabel characterLabel = new JLabel(c + "",JLabel.CENTER);
			characterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
			characterLabel.setBorder(border);
			
			characterLabel.addMouseListener(new LabelBindingMouseListener());

			this.add(textLabel);
			this.add(characterLabel);

		}

		/*
		 * Essa classe é responsável pelos eventos relacionados ao mouse na GameBindingCell
		 */
		private class LabelBindingMouseListener implements MouseListener {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				JLabel label = (JLabel) e.getSource();

				char c = MessageBox.messageBox.getCharMessage("Digite o caracter desejado:");

				if(bindingKeys.getKeys().contains(c + "") && label.getText().charAt(0) != c) {
					String [] btns = {".",".",".",".","Ok"};
					MessageBox.messageBox.alertMessage("Esse botão já está sendo usado!", btns);
				}
				else {
					char oldC = label.getText().charAt(0);
					label.setText("" + c);
					bindingKeys.setKeys(bindingKeys.getKeys().replace(oldC, c));
					Bar.saveBtn.setEnabled(true);
				}
			}
			public void mousePressed(java.awt.event.MouseEvent e) {}
			public void mouseReleased(java.awt.event.MouseEvent e) {}
			public void mouseEntered(java.awt.event.MouseEvent e) {}
			public void mouseExited(java.awt.event.MouseEvent e) {}

		}

	}
}
