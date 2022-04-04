package Trade;

import java.util.Arrays;

import Strategies.TradingStrategy;

public class TradeResult {
	private String trader;
	private TradingStrategy strategy;
	private String[] coinList;
	private double[] coinPriceList;
	private String action;
	private int quantity;
	private String timeStamp;
	private boolean success;
	
	public TradeResult(String trader, TradingStrategy strategy, String[] coinList, double[] coinPriceList, String action, int quantity, String timeStamp, boolean success) {
		this.trader = trader;
		this.strategy = strategy;
		this.coinList = coinList;
		this.coinPriceList = coinPriceList;
		this.action = action;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
		this.success = success;
	}
	
	public String getTrader() {
		return trader;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	public String[] getCoinList() {
		return coinList;
	}
	
	public double[] getCoinPriceList() {
		return coinPriceList;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public String[][] getData() {
		String[][] data = new String[coinList.length][];
		for(int i = 0; i < coinList.length; i++) {
			String[] row = {trader, strategy.toString(), coinList[i].toString(), action, "" + quantity, ((Double)coinPriceList[i]).toString(),timeStamp}; 
			data[i] = row; 
		}
		
		//String[] data = {trader, strategy.toString(), coins, action, "" + quantity, prices,timeStamp};
		return data;
	}
	
}