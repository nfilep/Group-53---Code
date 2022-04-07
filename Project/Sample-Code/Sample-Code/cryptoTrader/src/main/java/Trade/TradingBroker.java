package Trade;

import java.util.ArrayList;

import Trade.strategies.*;

public class TradingBroker {
	/**
	 * 
	 */
	private String name;
	private ArrayList<String> coinList;
	private ArrayList<Double> coinPriceList;
	private TradingStrategy strategy;
	private int numSuccessfulTrades;
	
	public TradingBroker(String name, ArrayList<String> coinList, TradingStrategy strategy) {
		this.name = name;
		this.coinList = coinList;
		this.strategy = strategy;
		coinPriceList = new ArrayList<Double>();
		numSuccessfulTrades = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getCoinList() {
		return coinList;
	}
	
	public void addCoin(String coin) {
		coinList.add(coin);
	}
	
	public ArrayList<Double> getCoinPriceList() {
		return coinPriceList;
	}
	
	public void addPrice(Double price) {
		coinPriceList.add(price);
	}
	
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	public TradeResult performTrade() {
		TradeResult result = strategy.trade(coinList, coinPriceList);
		result.setTrader(name);
		if(result.getSuccess()) {
			numSuccessfulTrades++;
		}
		return result;
	}
	
	public int getNumSuccessfulTrades() {
		return numSuccessfulTrades;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((TradingBroker)o).getName());
	}
}