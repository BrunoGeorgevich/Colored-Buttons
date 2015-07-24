package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.brunogeorgevich.SQLConnection;

import components.CustomButton;
import components.MessageBox;
import frame.Frame;

public class LoginWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField loginField = null;
	private JPasswordField passwordField = null;

	private JProgressBar isConnectedBar = null;
	private JLabel statusLabel = null;

	private static SQLConnection db = null;

	public LoginWindow() {

		this.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel(new GridLayout(0,3));

		addBlankSpaces(4,mainPanel);
		mainPanel.add(getCentralPanel());
		addBlankSpaces(4,mainPanel);
		
		isConnectedBar = new JProgressBar(0, 100);
		
		isConnectedBar.setBackground(Color.RED);
		isConnectedBar.setForeground(Color.BLUE);
		
		statusLabel = new JLabel("Status : Disconectado");
		statusLabel.setForeground(Color.RED);
		
		String [] btns = {"Convidado",".","Registrar",".","Entrar"};
		
		this.add(mainPanel, "Center");
		this.add(getTopBar(), "North");
		this.add(getBottomBar(btns), "South");
		
		Thread connectWithServer = new Thread(() -> {
			try {

				db = new SQLConnection("brunogeorgevich.com/brunogeo_database", "brunogeo_user", "qSETCJ6Qe");
				db.setTableName("coloredbuttons");

			} catch (SQLException sqlE) {
				System.out.println("ERRO SETTINGS SQLCONNECTION!");
				sqlE.printStackTrace();
			}

			isConnectedBar.setValue(100);
			statusLabel.setText("Status : Conectado");
			statusLabel.setForeground(Color.BLUE);
		});

		if(db == null)
			connectWithServer.start();
		else {
			isConnectedBar.setValue(100);
			statusLabel.setText("Status : Conectado");
			statusLabel.setForeground(Color.BLUE);
		}
	}	

	private void addBlankSpaces(int num, JPanel panel) {
		for(int i = 0; i < num; i++) {
			panel.add(new JPanel());
		}
	}

	private JPanel getCentralPanel() {

		JPanel centralPanel = new JPanel(new GridLayout(0,1,20,2));

		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setFont(new Font("Arial",Font.BOLD,20));
		loginField = new JTextField();

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Arial",Font.BOLD,20));
		passwordField = new JPasswordField();

		centralPanel.add(loginLabel);
		centralPanel.add(loginField);
		centralPanel.add(passwordLabel);
		centralPanel.add(passwordField);

		return centralPanel;
	}


	private JPanel getBottomBar(String [] btns) {

		JPanel bottomBar = new JPanel(new GridLayout(1,0,5,0));

		for(String btn : btns) {

			if(btn.equals(".")) {
				bottomBar.add(new JPanel());
			} else {
				bottomBar.add(new CustomButton(btn, 35, new LoginWindowBtnActionListener()));
			}
		}

		return bottomBar;
	}

	private JPanel getTopBar() {

		JPanel topBar = new JPanel(new GridLayout(1,0,10,0));

		topBar.add(isConnectedBar);
		topBar.add(statusLabel);
		
		return topBar;
	}

	public static SQLConnection getDb() {
		return db;
	}

	private class LoginWindowBtnActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			CustomButton btn = (CustomButton)e.getSource();

			String login = null;
			String password = null;
			String isValid = null;

			if(btn.getText() == "Entrar") {

				try {

					login = loginField.getText();
					password = db.encrypt(new String(passwordField.getPassword()));

					isValid = db.getResultByAttr("username", login);

					if(isValid.equals(login)) {

						isValid = db.getResultByAttr("password", password);

						if(isValid.equals(password)) {

							Frame.frame.changeContentPanel(new MenuWindow());

						} else {
							
							String [] btns = {".",".",".",".","Ok"};
							MessageBox.messageBox.alertMessage("Usuário/Senha inválido(a)!", btns);

						}
					} else {

						String [] btns = {".",".",".",".","Ok"};
						MessageBox.messageBox.alertMessage("Usuário/Senha inválido(a)!", btns);

					}

				} catch (SQLException e1) {
					System.out.println("ERROR ACESSING DATABASE!");
					e1.printStackTrace();
				} catch (Exception e1) {
					System.out.println("ERROR ENCRYPITATION PASSWORD!!");
					e1.printStackTrace();
				}		

			} else if(btn.getText().equals("Registrar")) {
				Frame.frame.changeContentPanel(new RegisterWindow());
			} else if(btn.getText().equals("Convidado")) {
				Frame.frame.changeContentPanel(new MenuWindow());
			} else {
				System.out.println("ERROR SIGNIN BTN ACTIONLISTENER!!");
			}
		}

	}
}
