package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * Essa classe é o frame da UI
 * Ela é a moldura de todos os panels
 */

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static MainFrame mainFrame = null;
	private Border margins = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	
	protected MainFrame(String title) {
		
		setLayout(new BorderLayout(30, 30));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1000, 768));
		
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(margins);
		setContentPane(contentPanel);

        this.setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void setContentPanel(JPanel pane) {
		
		pane.setBorder(margins);
		this.setContentPane(pane);
		this.revalidate();
		
	}
	
}
