package LoginUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import MainUI.MainUI;
import Database.Database;

public class LoginUI extends JFrame implements ActionListener{
	private final int MAX_ATTEMPTS = 3; // The maximum incorrect login attempts allowed
	
	private static LoginUI instance;
	private static JFrame frame = null;
	private static Database db = Database.getInstance("users.txt");
	
	private JPanel mainPanel = new JPanel(new GridLayout(3,1,10,10));
	
	private JPanel loginPanel = new JPanel(new FlowLayout());
	private JButton loginButton = new JButton("Login");
	
	private JPanel usernamePanel = new JPanel(new FlowLayout());
	private JLabel usernameLabel = new JLabel("Username"); 
	private JTextField usernameInputField = new JTextField("");
	
	private JPanel passwordPanel = new JPanel(new FlowLayout());
	private JLabel passwordLabel = new JLabel("Password"); 
	private JPasswordField passwordInputField = new JPasswordField(""); 
	
	private int numAttempts = 0; // The number of times the user has attempted to log in
	
	private boolean proceed = false;
	
	public static LoginUI getInstance() {
		if (instance == null)
			instance = new LoginUI();
		return instance;
	}
	
	private LoginUI() {
		super("Bitconnect Login"); // Set window title
		
		// Create a greeting message
		mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Welcome to Bitconnect!",
				TitledBorder.CENTER, TitledBorder.TOP));
		
		// Set up the label and input field for username
		usernamePanel.add(usernameLabel);
		usernameInputField.setPreferredSize(new Dimension(100,20));
		usernamePanel.add(usernameInputField);
		
		// Set up the label and input field for password
		passwordPanel.add(passwordLabel);		
		passwordInputField.setEchoChar('*');
		passwordInputField.setPreferredSize(new Dimension(100,20));
		passwordPanel.add(passwordInputField);
		
		// Set up the login button
		loginButton.setActionCommand("refresh");
		loginButton.addActionListener(this);
		loginButton.setPreferredSize(new Dimension(100,20));
		loginPanel.add(loginButton, BorderLayout.CENTER);
		
		// Add the panels to the main panel
		mainPanel.add(usernamePanel);
		mainPanel.add(passwordPanel);
		mainPanel.add(loginPanel);
		
		getContentPane().add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
				if(numAttempts < MAX_ATTEMPTS) {
					//Boolean valid = authenticate(username, password);
					if(authenticate(username, password)) {
						frame.setVisible(false);
						//JFrame mainframe = MainUI.getInstance();
						//mainframe.setVisible(true);
						proceed = true;
						return;
					}else {
						JOptionPane.showMessageDialog(this, "Invalid username or password");
						numAttempts++;
						return;
					}
				}else {
					JOptionPane.showMessageDialog(this, "Maximum login attempts exceeded");
					frame.setVisible(false);
					return;
				}
				
			}
		}
	}
	
	private boolean authenticate(String username, String password) {
		if(db.lookupUsername(username)) {
			return db.verifyPassword(username, password);
		}
		return false;
	}
	
	public boolean getProceed() {
		return proceed;
	}
		
	public static void main(String[] args) {
		frame = LoginUI.getInstance();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(400, 225);
		frame.setVisible(true);
	}
}
