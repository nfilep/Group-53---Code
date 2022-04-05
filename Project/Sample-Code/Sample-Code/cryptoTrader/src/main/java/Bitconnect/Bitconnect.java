package Bitconnect;

import MainUI.MainUI;
import LoginUI.LoginUI;

import javax.swing.JFrame;

public class Bitconnect {	
	public static void main(String[] args) {
		//int i = 0;
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
		System.out.println("Test");
		MainUI.run();
	}
}
