package Trade;

import java.util.ArrayList;
import java.util.Arrays;

import Trade.strategies.TradingStrategy;

public class TradeResult {
	private String trader;
	private TradingStrategy strategy;
	private String coinTraded;
	private Double coinPrice;
	private String action;
	private Double quantity;
	private String timeStamp;
	private boolean success;
	
	public TradeResult(TradingStrategy strategy, String coinTraded, Double coinPrice, String action, Double quantity, String timeStamp, boolean success) {
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
	
	public Double getCoinPrice() {
		return coinPrice;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public Object[] getData() {
		Object[] row = {trader, strategy.toString(), coinTraded, action, quantity, coinPrice, timeStamp}; 
		return row;
	}
	
}