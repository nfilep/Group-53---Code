package LoginUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LoginUI extends JFrame implements ActionListener{
	private static LoginUI instance;
	private JPanel north = new JPanel(); // Set top bar
	private JButton loginButton = new JButton("Login");
	private JLabel usernameLabel = new JLabel("Username"); 
	private JTextField usernameInputField = new JTextField("");
	private JLabel passwordLabel = new JLabel("Password"); 
	private JPasswordField passwordInputField = new JPasswordField(""); 
	private JPanel south = new JPanel();
	private JPanel usernamePanel = new JPanel(new BorderLayout());
	private JPanel passwordPanel = new JPanel(new BorderLayout());
	
	public static LoginUI getInstance() {
		if (instance == null)
			instance = new LoginUI();
		return instance;
	}
	
	private LoginUI() {
		super("Bitconnect Login"); // Set window title
		
		loginButton.setActionCommand("refresh");
		loginButton.addActionListener(this);
		
		north.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Welcome to Bitconnect!",
				TitledBorder.CENTER, TitledBorder.TOP));
		
		usernamePanel.add(usernameLabel, BorderLayout.LINE_START);
		usernameInputField.setPreferredSize(new Dimension(150,20));
		usernamePanel.add(usernameInputField, BorderLayout.LINE_END);
		
		passwordPanel.add(passwordLabel, BorderLayout.LINE_START);		
		passwordInputField.setEchoChar('*');
		passwordInputField.setPreferredSize(new Dimension(150,20));
		passwordPanel.add(passwordInputField, BorderLayout.LINE_END);
		south.add(loginButton);
		
		north.add(usernamePanel, BorderLayout.NORTH);
		north.add(passwordPanel, BorderLayout.CENTER);
		getContentPane().add(north, BorderLayout.NORTH);
		//getContentPane().add(usernamePanel, BorderLayout.NORTH);
		//getContentPane().add(passwordPanel, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		String password = passwordInputField.getText();
		if ("refresh".equals(command)) {
			String username = usernameInputField.getText();
			if(username.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter a username");
				return;
			}else if(password.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter a password");
				return;
			}else {
				// Enter code to authenticate the user
				System.out.println(username);
				System.out.println(password);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = LoginUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
}
