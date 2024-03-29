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

import Database.Database;
import User.User;

/**
 * this class is a user interface to login in using user credential
 * @author Nataliefilep
 */
public class LoginUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private final int MAX_ATTEMPTS = 3; // The maximum incorrect login attempts allowed
	
	private static LoginUI instance;
	
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
	
	private boolean proceed;
	private User user;
	
	public static LoginUI getInstance() {
		if (instance == null)
			instance = new LoginUI();
		return instance;
	}
	
	/**
	 * descriptions are inside
	 */
	private LoginUI() {
		super("Bitconnect Login"); // Set window title
		
		//instance.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//instance.setSize(400, 225);
		
		proceed = false;
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
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 225);
		this.setVisible(true);
	}

	@Override
	/** a built in method to check if the button is clicke or pressed
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		String password = passwordInputField.getText(); // Get the inputed password
		
		if ("refresh".equals(command)) {
			String username = usernameInputField.getText();
			if(username.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter a username");
				return;
			}else if(password.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter a password");
				return;
			}else {
				if(numAttempts < MAX_ATTEMPTS) {
					if(authenticate(username, password)) {
						//JFrame mainframe = MainUI.getInstance();
						//mainframe.setVisible(true);
						System.out.println("Set proceed to true");
						proceed = true;
						instance.setVisible(false);
						return;
					}else {
						JOptionPane.showMessageDialog(this, "Invalid username or password");
						numAttempts++;
						return;
					}
				}else {
					JOptionPane.showMessageDialog(this, "Maximum login attempts exceeded");
					instance.setVisible(false);
					return;
				}
				
			}
		}
	}
	
	/**
	 * this method checks if the username is in the database 
	 * and password matches that username
	 * @param username user's name
	 * @param password password for the user
	 */
	private boolean authenticate(String username, String password) {
		Database db = Database.getInstance("users.txt");
		if(Database.lookupUsername(username)) {
			user = new User(username, password);
			return Database.verifyPassword(username, password);
		}
		return false;
	}
	
	/**
	 * @return proceed
	 */
	public boolean getProceed() {
		return proceed;
	}
	
	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * creates a LoginUI object
	 */
	public static void main(String[]args) {
		LoginUI login = LoginUI.getInstance();
	}
}
