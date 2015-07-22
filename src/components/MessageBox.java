package components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;

import windows.GameWindow;
import windows.SettingsWindow;
import frame.Frame;

/*
 * Essa classe é responsável por todas as janelas (Popups) 
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
		messagePanel.add(getButtonsBar(btns, new MessageBoxActionListener()));

		this.setContentPane(messagePanel);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JPanel getButtonsBar(String [] btns, ActionListener act) {
		
		JPanel barPanel = new JPanel(new GridLayout(1,0,5,5));
		
		for (String str : btns) {
			if(str != ".")
				barPanel.add(new CustomButton(str, 30, act));
			else
				barPanel.add(new JPanel());
		}
		
		return barPanel;
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
	private void setChar(char a) {
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
	 * Essa classe é usada para determinar as funçẽos dos botões
	 * do método alertMessage
	 */

	private class MessageBoxActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomButton btn = (CustomButton)e.getSource();

			messageBox.dispose();
			
			if(btn.getText() == "Ok") {}
			else if(btn.getText() == "Back") {
				Frame.frame.changeContentPanel(new SettingsWindow());
			}
			else if(btn.getText() == "Replay") {
				GameWindow.gameWindow = new GameWindow(GameWindow.gameWindow.getGameManager().getSettings());
				Frame.frame.changeContentPanel(GameWindow.gameWindow);
			}
		}
	}
}