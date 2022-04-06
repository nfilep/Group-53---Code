package Trade;

import java.util.ArrayList;
import java.util.Arrays;

import Trade.strategies.TradingStrategy;

public class TradeResult {
	private String trader;
	private TradingStrategy strategy;
	private String coinTraded;
	private double coinPrice;
	private String action;
	private double quantity;
	private String timeStamp;
	private boolean success;
	
	public TradeResult(TradingStrategy strategy, String coinTraded, double coinPrice, String action, double quantity, String timeStamp, boolean success) {
		trader = "N/A";
		this.strategy = strategy;
		this.coinTraded = coinTraded;
		this.coinPrice = coinPrice;
		this.action = action;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
		this.success = success;
	}
	
	public String getTrader() {
		return trader;
	}
	
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	public String getCoinTraded() {
		return coinTraded;
	}
	
	public double getCoinPrice() {
		return coinPrice;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public String[] getData() {
		String[] row = {trader, strategy.toString(), coinTraded, action, "" + quantity, "" + coinPrice, timeStamp}; 
		return row;
	}
	
}
