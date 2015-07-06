package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.CustomButton;
import components.GameButton;
import components.LineUi;
import components.MessageBox;
import frame.Frame;
import structure.Game;
import structure.Settings;

/*
 * Essa classe é o Painel da janela Game
 */

public class GameWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<LineUi> btnLines = new ArrayList<LineUi>();
	private LineUi rightLine = null;
	private Game gameManager = null;

	private TopBarCell scoreCell = null;
	private TopBarCell recordCell = null;
	private TopBarCell timeCell = null;
	
	public static GameWindow gameWindow = null;
	
	public GameWindow(Settings s) {
		
		gameManager = new Game(s);
		gameWindow = this;
		
		Frame.frame.addKeyListener(new GameWindowKeyListener());
		
		setLayout(new BorderLayout(20,20));
		
		JPanel southArea = new JPanel(new GridLayout(0,1,10,10));

		southArea.add(generateShortcutBar());
		southArea.add(generateBottomBar());

		add(generateTopBar(), "North");
		add(generateButtonsPanel(), "Center");
		add(southArea, "South");
		
	}

	private JPanel generateTopBar() {
		
		JPanel topBar = new JPanel(new GridLayout(1,0,20,20));
		
		String [] titles = {"Score:", "Record:", "Time:"};		

		scoreCell = new TopBarCell(titles[0], "0");
		recordCell = new TopBarCell(titles[1], "0");
		timeCell = new TopBarCell(titles[2], "0");

		topBar.add(scoreCell);
		topBar.add(recordCell);
		topBar.add(timeCell);
		
		
		return topBar;
		
	}
	
	public LineUi getRightLine() {
		return rightLine;
	}

	public TopBarCell getScoreCell() {
		return scoreCell;
	}

	public TopBarCell getRecordCell() {
		return recordCell;
	}

	public TopBarCell getTimeCell() {
		return timeCell;
	}

	private JPanel generateButtonsPanel() {
		
		JPanel btnsPanel = new JPanel(new GridLayout(0,1,5,5));
		
		for(int i = 0; i < gameManager.getNumOfLines(); i++) {
			
			LineUi line = new LineUi(gameManager.getLines().get(i), 
					gameManager.getSettings().getPrimaryColor(),
					gameManager.getSettings().getSecondaryColor(),
					new GameButtonActionListener());
			
			rightLine = line;
			btnsPanel.add(line);
		}
		
		return btnsPanel;
		
	}

	private JPanel generateShortcutBar() {
		
		JPanel shortcutBar = new JPanel(new GridLayout(1,0,20,20));
		String str = gameManager.getSettings().getKeyBindings();
		
		for(int i = 0; i < str.length(); i++) {
			JLabel shortcutLbl = new JLabel(str.charAt(i) + "", JLabel.CENTER);
			shortcutLbl.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/20));
			shortcutBar.add(shortcutLbl);
		}
		
		return shortcutBar;
		
	}
	
	private JPanel generateBottomBar() {
		
		JPanel bottomBar = new JPanel(new GridLayout(1,0,20,20));
		
		bottomBar.add(new CustomButton("Back", 30, new BottomBarActionListener()));
		bottomBar.add(new JPanel());
		bottomBar.add(new JPanel());
		bottomBar.add(new JPanel());
		bottomBar.add(new CustomButton("Pause", 30, new BottomBarActionListener()));
		
		return bottomBar;
		
	}
	
	public void updateRightAnswer() {
		
		gameManager.rightAnswer();
		
		BorderLayout layout = (BorderLayout)gameWindow.getLayout();
		gameWindow.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		
		gameWindow.add(generateButtonsPanel(), "Center");	
		gameWindow.revalidate();
		
	}
	
	public void updateWrongAnswer() {

		Frame.frame.removeKeyListener(Frame.frame.getKeyListeners()[0]);
		
		String [] btns = {"Back", ".", ".", ".", "Replay"};
		MessageBox.messageBox.alertMessage("Você perdeu!", btns);
	}
	
	public Game getGameManager() {
		return gameManager;
	}
	
	//Inner Classes

	public class TopBarCell extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JLabel titleLabel = null;
		private JLabel contentLabel = null;

		public TopBarCell(String title, String content) {
	
			setLayout(new GridLayout(2,1,0,0));

			titleLabel = new JLabel(title, JLabel.CENTER);
			titleLabel.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/25));
			titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
			contentLabel = new JLabel(content, JLabel.CENTER);
			contentLabel.setFont(new Font("Arial", Font.BOLD, Frame.frame.FRAME_HEIGHT/25));
			contentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

			add(titleLabel);
			add(contentLabel);
			
		}

		public JLabel getTitleLabel() {
			return titleLabel;
		}

		public JLabel getContentLabel() {
			return contentLabel;
		}		
	}
	
	private class BottomBarActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomButton btn = (CustomButton)e.getSource();
			
			if(btn.getText() == "Pause")
				System.out.println("Pause!");
			else if(btn.getText() == "Back")
				Frame.frame.changeContentPanel(new SettingsWindow());
			else
				System.out.println("ERRO GAME_BTN_ACTIONLISTENER!!");
			
		}		
	}
	
	private class GameButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			GameButton btn = (GameButton)e.getSource();
			
			if(btn.equals(rightLine.getRightButton())) {
				
				updateRightAnswer();
				
			} else {

				updateWrongAnswer();
				
			}
		}		
	}
	
	private class GameWindowKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			
			String keys = gameManager.getSettings().getKeyBindings();
			int rightBtnIndex = gameManager.getRightLine().getRightNumber();
			
			if(keys.contains(e.getKeyChar() + "")) {
				if(keys.charAt(rightBtnIndex) == e.getKeyChar()) {
				
					updateRightAnswer();

				} else {

					updateWrongAnswer();
					
				}
			}			
		}

		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
}
