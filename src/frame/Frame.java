package frame;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * Essa classe cria o Frame da interface gráfica (ui)
 */

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int FRAME_WIDTH = -1;
	public int FRAME_HEIGHT = -1;
	
	private Border borders = BorderFactory.createEmptyBorder(20, 20, 20, 20);
	
	public static Frame frame = new Frame(800,600);
	
	private Frame(int width, int height) {

		FRAME_WIDTH = width;
		FRAME_HEIGHT = height;
		
		setMinimumSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setFocusable(true);
		setVisible(true);
	}
	
	public void changeContentPanel(JPanel panel) {
		panel.setBorder(borders);
		setContentPane(panel);
		revalidate();
	}
}
