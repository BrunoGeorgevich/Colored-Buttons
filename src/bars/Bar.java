package bars;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.CommonButton;

/*
 * Classe responsavel pelas barras do programa,
 * sejam elas superiores, inferiores, dentre outras.
 */

public class Bar {

	//Referencia direta ao botão que salva as opções, necessária para a ativação e desativação do mesmo
	static public JButton saveBtn = null;

	//Referencias para os labels responsaveis pelo placar, tempo e score
	static public JLabel timeLabel = null;
	static public JLabel recordLabel = null;
	static public JLabel scoreLabel = null;
	
	//Método estático que retorna a barra com todos os botões e suas funções
	static public JPanel getBar(String [] components, ActionListener action) {
		JPanel bar = new JPanel(new GridLayout(1,0));

		for (String component : components) {
			if(component.equals(".")) {
				
				bar.add(new JPanel());
			
			}
			else {
			
				CommonButton btn = new CommonButton(component, action, 1);
				bar.add(btn);
			
				if(component.equals("Salvar"))
					saveBtn = btn;
			}
		}

		return bar;
	}
	
	//Método estático que retorna a barra de labels da GameWindow
	static public JPanel getGameWindowBar(String [] label) {
		JPanel bar = new JPanel(new GridLayout(1,0));

		timeLabel = new JLabel("00:00" , JLabel.CENTER);;
		scoreLabel = new JLabel("0" , JLabel.CENTER);;
		recordLabel = new JLabel("0" , JLabel.CENTER);;
		
		int switcher = 0;
		
		for (int i = 0; i < label.length; i++) {
			if(label[i].equals(".")) {
				
				bar.add(new JPanel());
			
			}
			else {
				
				BarCell cell = null;
				
				if(switcher == 0) 
					cell = new BarCell(label[i], timeLabel, 1.3);
				else if(switcher == 1) 
					cell = new BarCell(label[i], recordLabel, 1.3);
				else if(switcher == 2) 
					cell = new BarCell(label[i], scoreLabel, 1.3);
				else
					System.out.println("ERRO HEADERBAR DOS DADOS!!");				
				
				bar.add(cell);
				
				switcher++;
			}
		}
		
		System.out.println("HEADERBAR GAMEWINDOW CRIADA COM SUCESSO!!!");

		return bar;
	}
	
	/*
	 * Essa class é uma célula da barra, ela organiza cada espaço da barra com um label e seu conteudo
	 */
	static private class BarCell extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//Referencias aos labels de titulo e conteudo da celula
		public JLabel barCellLabel = null;
		public JLabel barCellContent = null;
				
		
		public BarCell(String label, JLabel content, double scale) {
			
			this.setLayout(new GridLayout(0,1));
			
			barCellLabel = new JLabel(label, JLabel.CENTER);
			barCellLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(20*scale)));
			barCellContent = content;
			barCellContent.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(20*scale)));
			
			this.add(barCellLabel);
			this.add(barCellContent);
			
		}
		
	}

}
