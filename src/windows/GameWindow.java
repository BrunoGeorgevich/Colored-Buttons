package windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainFrame;
import bars.Bar;
import components.Difficulty;
import components.GameBindingKeys;
import components.GameLine;
import components.GameTimer;
import components.MessageBox;
import components.MusicPlayer;
import components.OptionsFile;

/*
 * Essa é a classe fundamental do programa
 * É nela que ocorrerá todas as interações usuário jogo,
 * todas relacionadas a jogabilidade.
 * Ela gerencia todas as linhas e botões do jogo
 */

public class GameWindow extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static GameWindow gameWindow = null;
	private static boolean firstTime = true;

	private ArrayList<GameLine> lines = new ArrayList<GameLine>();
	public GameLine firstLine = null;

	private String [] bottomLostGameBtns = {"Menu", ".", ".", "<html><center>Tente<br>De novo!</center></html>"};
	private String [] headerBarLabels = {".","Tempo","Recorde","Pontos","."};
	private String [] bottomBarBtns = {"Voltar",".",".",".","."};

	private int recordNumber = -1;
	private int scoreNumber = -1;
	
	private boolean firstClick = false;
	private boolean switcherPlayer = false;
	private GameTimer timer = null;

	private JPanel bottomBar = Bar.getBar(bottomBarBtns, new GameWindowBottomBarButtonActionListener());	
	public JPanel contentPanel = new JPanel(new GridLayout(0,1,10,10));	
	private JPanel headerBar = Bar.getGameWindowBar(headerBarLabels);

	public GameWindow() {

		/*
		 * Definindo o layout como BorderLayout 
		 * e dando um espaçamento horizontal/vertical de 30
		 */
		this.setLayout(new BorderLayout(30,30));

		//Definindo que o mainFrame receberá comandos da tela
		MainFrame.mainFrame.setFocusable(true);

		//Definindo os eventos de teclado do mainFrame, necessitando apenas de uma vez
		if(firstTime) {

			MainFrame.mainFrame.addKeyListener(new GameWindowKeyListener());
			GameWindow.firstTime = false;

		}

		//Definindo o número de linhas e colunas a partir da dificuldade selecionada
		for(int i = 0; i < Difficulty.difficulty.getColumns(); i++) {

			GameLine line = addLines();
			this.lines.add(line);

		}	

		//Definindo o Valor inicial do timer
		Bar.timeLabel.setText(Integer.toString(Difficulty.difficulty.getMaxTime()));

		//Recebendo o valor do record e definindo o texto do recordLabel
		recordNumber = Integer.parseInt(OptionsFile.file.readFileLine(5));
		Bar.recordLabel.setText(Integer.toString(recordNumber));

		updateLines();
		firstClick = true;

		//Adicionando os painéis ao layout principal
		this.add(contentPanel, "Center");
		this.add(headerBar, "North");
		this.add(bottomBar, "South");

		
		GameWindow.gameWindow = this;

	}

	//Esse método incrementa o score a cada acerto
	public void incrementScore() {

		scoreNumber++;

		if(scoreNumber > recordNumber) {
			recordNumber = scoreNumber;
			Bar.recordLabel.setText(Integer.toString(recordNumber));
		}

		if(timer != null)
			timer.incrementTime();
		
		Bar.scoreLabel.setText(Integer.toString(scoreNumber));
	}

	//Esse método adiciona linhas facilmente
	private GameLine addLines() {
		GameLine line = new GameLine();		
		return line;
	}

	//Esse método faz todo o gerenciamento de linhas do jogo
	public void updateLines() {

		if(firstClick) {
			timer = new GameTimer(1 , Difficulty.difficulty.getMaxTime());
			firstClick = false;
		}
		
		//Remove a linha que você acertou o botão correto
		lines.remove(lines.size() - 1);

		//Adiciona uma linha ao fim
		lines.add(0, new GameLine());

		//Incrementa o score
		this.incrementScore();
		//Atualiza o painel principal
		refreshContentPanel();
	}

	//Esse método atua quando você perde o jogo
	public void lostGame() {

		//Essa linha de código diz que o mainFrame não terá mais acesso aos atalhos do teclado
		MainFrame.mainFrame.setFocusable(false);
		//Emite o som de derrota
		MusicPlayer.effectsMusic.play("WrongNote.wav", false);
		if(timer != null)
			timer.stop();
		//Mostra uma mensagem de pop-up dizendo que você perdeu
		MessageBox.messageBox.alertMessage("Você Perdeu!!", bottomLostGameBtns);

	}

	//Atualiza o painel principal do gameWindow
	private void refreshContentPanel() {

		contentPanel.removeAll();

		for (GameLine line : lines) {
			contentPanel.add(line);
		}

		firstLine = lines.get(lines.size() - 1);
		contentPanel.revalidate();
	}
	
	public void refreshHeaderBar() {
		
		Bar.timeLabel.setText(Integer.toString(timer.getCurrentTime()));
		
	}

	/*
	 * Essa classe representa as ações dos botões de baixo da gameWindow
	 */
	private class GameWindowBottomBarButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();

			MusicPlayer.effectsMusic.play("CorrectSound.wav", false);

			if(btn.getText() == "Voltar") {

				MainFrame.mainFrame.setFocusable(false);
				
				if(timer != null)
					timer.stop();
				
				MainFrame.mainFrame.setContentPanel(MenuWindow.menuWindow);
				
			}			
		}		
	}

	/*
	 * Essa classe representa as ações dos atalhos do teclado
	 */
	private class GameWindowKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

			if(GameBindingKeys.bindingKeys.getKeys().contains("" + e.getKeyChar())) {

				if(GameBindingKeys.bindingKeys.getKeys().indexOf("" + e.getKeyChar()) == GameWindow.gameWindow.firstLine.firstBtn.getPosition()) {	
					GameWindow.gameWindow.updateLines();					
				}				
				else {
					GameWindow.gameWindow.lostGame();
				}
			}			
		}

		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}

	}
}
