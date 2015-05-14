package components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import windows.GameWindow;
import windows.MenuWindow;
import main.MainFrame;
import bars.Bar;

/*
 * Essa classe é responsável por todas as janelas (Pop-ups) 
 * do programa.
 */

public class MessageBox extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Border margins = BorderFactory.createEmptyBorder(10, 10, 10, 10);	
	private final int BOX_WIDTH = 600;
	private final int BOX_HEIGHT = 300;

	public static MessageBox messageBox = new MessageBox();
	static private char c = '.';
	
	private MessageBox() {}
	
	//Método usado para dar uma mensagem e lhe apresentar botões
	public void alertMessage(String text, String [] btns) {
		
		this.setModal(true);
		this.setMinimumSize(new Dimension(BOX_WIDTH,BOX_HEIGHT));
		this.setMaximumSize(new Dimension(BOX_WIDTH,BOX_HEIGHT));
		
		JPanel messagePanel = new JPanel(new GridLayout(0,1));
		messagePanel.setBorder(margins);
		
		JLabel message = new JLabel(text,JLabel.CENTER);
		message.setFont(new Font("Arial", Font.BOLD, BOX_HEIGHT/10));

		messagePanel.add(new JPanel());
		messagePanel.add(message);
		messagePanel.add(new JPanel());
		messagePanel.add(Bar.getBar(btns, new MessageBoxActionListener()));
		
		this.setContentPane(messagePanel);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	//Método usado para coletar os atalhos de GameBindingsKeys
	public char getCharMessage(String text) {
		
		this.setModal(true);
		this.setMinimumSize(new Dimension(BOX_WIDTH,BOX_HEIGHT));
		this.setMaximumSize(new Dimension(BOX_WIDTH,BOX_HEIGHT));
		this.setFocusable(true);
		
		this.addKeyListener(new MessageBoxKeyListener());
		
		JPanel messagePanel = new JPanel(new GridLayout(0,1));
		messagePanel.setBorder(margins);

		JLabel message = new JLabel(text,JLabel.CENTER);
		message.setFont(new Font("Arial", Font.BOLD, BOX_HEIGHT/10));

		messagePanel.add(new JPanel());
		messagePanel.add(message);
		messagePanel.add(new JPanel());
		
		this.setContentPane(messagePanel);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		return c;
		
	}
	
	//Método usado para salvar o char do GameBindingKeys
	public void setChar(char a) {
		c = a;
	}
	
	/*
	 * Classe usada para ouvir o char de GameBindingKeys
	 */
	private class MessageBoxKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			setChar(e.getKeyChar());
			MessageBox.messageBox.dispose();
		}

		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}	
	}

	/*
	 * Classe usada para atribuir funções aos botões das janelas
	 */
	private class MessageBoxActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			
			if(btn.getText() == "Ok") {
				MessageBox.messageBox.dispose();
			} else if(btn.getText() == "Menu") {
				
				OptionsFile.file.saveScore(Bar.recordLabel.getText());
				
				MainFrame.mainFrame.setContentPanel(MenuWindow.menuWindow);
				MainFrame.mainFrame.setFocusable(false);
				MessageBox.messageBox.dispose();
			} else if(btn.getText() == "<html><center>Tente<br>De novo!</center></html>") {
				
				OptionsFile.file.saveScore(Bar.recordLabel.getText());
				
				GameWindow.gameWindow = new GameWindow();
				MainFrame.mainFrame.setContentPanel(GameWindow.gameWindow);
				MessageBox.messageBox.dispose();
			}		
		}		
	}
}
