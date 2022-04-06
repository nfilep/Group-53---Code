package Bitconnect;

import MainUI.MainUI;
import User.User;
import LoginUI.LoginUI;

import javax.swing.JFrame;

public class Bitconnect {	
	public static User systemUser;
	
	public static void main(String[] args) {
		LoginUI login = LoginUI.getInstance();
		//instance.setDefaultCloseOperation(EXIT_ON_CLOSE);
		login.setSize(400, 225);
		login.setVisible(true);
		while(!login.getProceed()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		systemUser = login.getUser();
		System.out.println(systemUser.getBrokerList().size());
		System.out.println("Test");
		MainUI.run();
		
	}
}
