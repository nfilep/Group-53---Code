package Trade;

import java.util.Arrays;

import Trade.strategies.TradingStrategy;

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
	
	public String[] getCoinList() {
		return coinList;
	}
	
	public double[] getCoinPriceList() {
		return coinPriceList;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public String[] getData() {
		String[] data = {trader, strategy.toString(), action, "" + quantity, timeStamp};
		return data;
	}
	
}
