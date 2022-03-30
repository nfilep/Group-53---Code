package Viewer;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import MainUI.MainUI;
import Trade.TradeResult;

public class TableViewer implements iViewer {
	@Override
	public void draw(TradeResult result) {
		// TODO Auto-generated method stub
		createTableOutput(result);
	}
	
	private void createTableOutput(TradeResult result) {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		Object[][] data = new Object[result.getLength()][columnNames.length];
		// Dummy data for demo purposes. These should come from actual fetcher
		for(int i = 0; i < result.getLength(); i++) {
			//for(int j = 0; j < columnNames.length; i++) {
				if(result[i].getSuccess()) {
					data[i][0] = result.getTraderName();
					data[i][1] = result.getStrategy();
					data[i][2] = result.getCoinType();
					data[i][3] = result.getAction();
					data[i][4] = result.getQuantityTraded();
					data[i][5] = result.getPrice();
					data[i][6] = result.getTimeStamp();
				}
			//}
		}
		/*
		Object[][] data = {
				{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3","13-January-2022"},
				{"Trader-2", "Strategy-B", "BTC", "Sell", "200", "50.2","13-January-2022"},
				{"Trader-3", "Strategy-C", "USDT", "Buy", "1000", "2.59","15-January-2022"},
				{"Trader-1", "Strategy-A", "USDC", "Buy", "500", "150.3","16-January-2022"},
				{"Trader-2", "Strategy-B", "ADA", "Sell", "200", "50.2","16-January-2022"},
				{"Trader-3", "Strategy-C", "SOL", "Buy", "1000", "2.59","17-January-2022"},
				{"Trader-1", "Strategy-A", "ONE", "Buy", "500", "150.3","17-January-2022"},
				{"Trader-2", "Strategy-B", "MANA", "Sell", "200", "50.2","17-January-2022"},
				{"Trader-3", "Strategy-C", "AVAX", "Buy", "1000", "2.59","19-January-2022"},
				{"Trader-1", "Strategy-A", "LUNA", "Buy", "500", "150.3","19-January-2022"},
				{"Trader-2", "Strategy-B", "FTM", "Sell", "200", "50.2","19-January-2022"},
				{"Trader-3", "Strategy-C", "HNT", "Buy", "1000", "2.59","20-January-2022"}
		};*/
		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}
}
