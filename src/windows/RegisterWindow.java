package windows;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.CustomButton;
import components.MessageBox;
import frame.Frame;

public class RegisterWindow extends JPanel {

	JTextField nameField = null;
	JTextField lastnameField = null;
	JTextField usernameField = null;
	JPasswordField passwordField = null;
	JPasswordField passwordField2 = null;

	public RegisterWindow() {

		this.setLayout(new GridLayout(0,1,0,5));

		nameField = new JTextField();
		lastnameField = new JTextField();
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		passwordField2 = new JPasswordField();

		this.add(getCell("Nome",false,nameField));
		this.add(getCell("Sobrenome",false,lastnameField));
		this.add(getCell("Usuário",false,usernameField));
		this.add(getCell("Senha",true,passwordField));
		this.add(getCell("Reescreva a Senha",true,passwordField2));

		String [] btns = {"Voltar",".",".",".","Registrar"};

		this.add(getBottomBar(btns));

	}

	private JPanel getCell(String label, boolean isPassword, Object field) {

		JPanel cell = new JPanel(new GridLayout(0,1,10,5));

		JLabel lbl = new JLabel(label + ":");
		lbl.setFont(new Font("Arial", Font.BOLD, 15));

		cell.add(lbl);

		if(isPassword) {
			cell.add((JPasswordField)field);
		} else {
			cell.add((JTextField)field);
		}

		return cell;

	}

	private JPanel getBottomBar(String [] btns) {

		JPanel bottomBar = new JPanel(new GridLayout(1,0,5,0));

		for(String btn : btns) {

			if(btn.equals(".")) {
				bottomBar.add(new JPanel());
			} else {
				bottomBar.add(new CustomButton(btn, 30, new RegisterBtnActionListener()));
			}
		}

		return bottomBar;
	}
	
	private void clearFields() {
		
		nameField.setText("");
		lastnameField.setText("");
		usernameField.setText("");
		passwordField.setText("");
		passwordField2.setText("");
		
	}

	private class RegisterBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			CustomButton btn = (CustomButton)e.getSource();

			if(btn.getText().equals("Voltar")) {
				Frame.frame.changeContentPanel(new LoginWindow());
			} else if(btn.getText().equals("Registrar")) {								

				try {

					if(!(nameField.getText().isEmpty() 
							|| lastnameField.getText().isEmpty() 
							|| usernameField.getText().isEmpty() 
							|| new String(passwordField.getPassword()).isEmpty() 
							|| new String(passwordField2.getPassword()).isEmpty())) {
					
						if(new String(passwordField.getPassword()).equals(new String(passwordField2.getPassword()))) {
						
							String [] user = {
									"default",
									"\"" + nameField.getText() + "\"",
									"\"" + lastnameField.getText() + "\"",
									"\"" + usernameField.getText() + "\"",
									"\"" + LoginWindow.getDb().encrypt(new String(passwordField.getPassword())) + "\""};
						
							LoginWindow.getDb().insertInto(user);
							
						} else {
							String [] btns = {".",".",".",".","Ok"};								
							
							MessageBox.messageBox.alertMessage("<html>Por favor digite senhas<br><center> iguais nos dois campos!</center></html>", btns);
							passwordField.setText("");
							passwordField2.setText("");
							
						}
					
					} else {
						String [] btns = {".",".",".",".","Ok"};								
						
						MessageBox.messageBox.alertMessage("<html>Por favor preencha <br><center>todos os campos!</center></html>", btns);
						clearFields();
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}			
		}		
	}	
}
