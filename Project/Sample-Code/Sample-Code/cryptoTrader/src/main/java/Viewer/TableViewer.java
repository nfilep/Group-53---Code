package Viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Trade.TradeResult;
import Trade.strategies.*;

public class TableViewer extends JPanel implements iViewer{
	private final Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
	private JTable table;
	private DefaultTableModel model;
	
	public TableViewer() {
		//frame = new JFrame();
		//panel = new JPanel();
		table = new JTable();
		table.setEnabled(false);
		model = new DefaultTableModel();
		model.addRow(columnNames);
		
		for(int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		
		model.removeRow(0);
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
			
		scrollPane.setPreferredSize(new Dimension(800, 300));
		this.add(scrollPane);
		this.setSize(500, 200);
        this.setVisible(true);
	}
	
	@Override
	public void draw(TradeResult result) {
		if(result.getSuccess()) {
			Object[][] displayData = result.getData();
			for(int i = 0; i < displayData.length; i++) {
				model.addRow(displayData[i]);
			}			
		} else {
			Object[] displayData = {result.getTrader(),result.getStrategy().toString(),"Null", "Fail","Null","Null",result.getTimeStamp()};
			model.addRow(displayData);
		}
		table.setModel(model);
	}
	
	public static void main(String[] args) {
		TableViewer t = new TableViewer();
		JFrame frame = new JFrame();
		//this.getContentPane.add(t);
		frame.add(t);
		
		TradingStrategy stratA = new StrategyA();
		String[] coins = {"BTC", "ETH"};
		double[] prices = {1.00, 2.50};
		boolean[] passFail = {true,true,false,true,false};
		
		TradeResult testResult;
		for(int i = 0; i < 5; i++) {
			testResult = new TradeResult("Natalie", stratA, coins, prices, "buy", i*10, "2022/04/02", passFail[i]);
			t.draw(testResult);
		}
	}

}
