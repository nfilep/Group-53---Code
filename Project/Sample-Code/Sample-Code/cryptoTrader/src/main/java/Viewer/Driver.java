package Viewer;

import javax.swing.JFrame;

import MainUI.MainUI;
import Trade.TradeResult;

public class Driver {
	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
		
		TradeResult result = new TradeResult();
		TableViewer activityLog = new TableViewer();
		activityLog.draw(null);
	}
}
